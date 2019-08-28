package com.vy.controller;

import com.vy.Service.TbDataService;
import com.vy.Service.UserService;
import com.vy.common.pojo.Emp;
import com.vy.common.pojo.TbData;
import com.vy.util.jasonMapperUtil;
import net.sf.jsqlparser.statement.select.First;
import org.apache.poi.hssf.record.ArrayRecord;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Controller
public class UserController {

    @RequestMapping("/testIndex")
    public String shouExcel(){
        return "01";
    }

    @Autowired
    private TbDataService tbDataService;

    //to Excel
    @RequestMapping(value = "/test/download", produces = { "application/json;charset=UTF-8" },method = RequestMethod.GET)
    @ResponseBody
    public String downLoadExecl(HttpServletResponse response){
        String fileName ="用户信息表";
        List<TbData> list= tbDataService.getData();
        ExprotExcrel<TbData> excrel = new ExprotExcrel<>();
        String[] headers =excrel.getHeaders(list);
        excrel.exportExcel(headers,list,fileName,response);
        ArrayList<Object> objects = new ArrayList<>();

        return "ok";
    }

}

class ExprotExcrel<T>{
    public  String[] getHeaders (List<T> list){
        String[] headers=new String[list.get(0).getClass().getDeclaredFields().length];
        Field[] fields = list.get(0).getClass().getDeclaredFields();
        for (int i=0;i<headers.length;i++){
            String name = fields[i].getName();
            headers[i]=name;
        }
        return headers;
    }
    public void exportExcel(String[] headers, Collection<T> dataset, String fileName,HttpServletResponse response){
        //声明一个工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        //生成一个表格
        XSSFSheet sheet = workbook.createSheet(fileName);
        //s设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short)20);
        //产生表格标题行
        XSSFRow row = sheet.createRow(0);
        for (int i=0;i<headers.length;i++){
            XSSFCell cell = row.createCell(i);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        try {
        //遍历集合数据,产生数据行
            Iterator<T> it = dataset.iterator();
            int index=0;
            while (it.hasNext()){
                index++;
                row = sheet.createRow(index);
                T t = (T)it.next();
                //利用反射,根据javabean属性的先后顺序,动态调用getxx()方法得到属性值
                Field[] fields = t.getClass().getDeclaredFields();
                for (int i=0;i<headers.length;i++){
                    XSSFCell cell = row.createCell(i);
                    Field field = fields[i];
                    String fieldName = field.getName();
                        String getMethodName="get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
                        Class tCls=t.getClass();
                    Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
                    Object value = getMethod.invoke(t, new Object[]{});
                    //判断值的类型后进行强制类型转换
                    String textValue=null;
                    //其它数据类型都当作字符串简单处理
                    if (value !=null&& value!=""){
                        textValue =value.toString();
                    }
                    if (textValue!=null){
                        XSSFRichTextString richString = new XSSFRichTextString(textValue);
                        cell.setCellValue(richString);
                    }
                }
            }
            getExportedFile(workbook,fileName,response);
        }catch (Exception e){

        }
    }

    private void getExportedFile(XSSFWorkbook workbook, String name,HttpServletResponse response) {
        BufferedOutputStream fos=null;
        String fileName=name+"-"+SimpleDateFormat.getDateInstance().format(new Date())+".xlsx";
        try {
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String( fileName.getBytes("gb2312"), "ISO8859-1" ));
            fos =new BufferedOutputStream(response.getOutputStream());
            workbook.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    fos=null;
                }
            }
        }
    }
}
