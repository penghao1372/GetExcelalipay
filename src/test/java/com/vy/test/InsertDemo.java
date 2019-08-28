package com.vy.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@SpringBootTest
@RunWith(SpringRunner.class)
public class InsertDemo {
    static Connection conn=null;
    static PreparedStatement ps=null;
    @Test
    public void insertTest(){

        String url="jdbc:mysql://localhost:3306/db40?user=root&password=root&useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=UTC";


        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("驱动加载成功");
           conn= DriverManager.getConnection(url);
        }catch (Exception e){
            e.printStackTrace();
        }
        //开始时间
        long begin = System.currentTimeMillis();
        System.out.println("开始插入数据");
        //sql前缀
        String prefix="INSERT INTO tb_data (id, user_name, create_time, random) VALUES";
        try {
            //保存sql后缀
            StringBuffer suffix = new StringBuffer();
            conn.setAutoCommit(false);
          for (int j = 0; j < 1000; j++) {
              suffix.append("(" +null+ ",'" + randomStr(8) + "', SYSDATE(), " + j * Math.random() + "),");
          }
          String sql = prefix + suffix.substring(0, suffix.length() - 1);
          System.out.println(sql);
          //添加执行sql
          ps= conn.prepareStatement(" ");
          ps.addBatch(sql);
          ps.executeBatch();
          conn.commit();
            ps.close();
            conn.close();
            //结束时间
            long end = System.currentTimeMillis();
           System.out.println("耗时"+(end-begin));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String randomStr(int size) {
        //定义一个空字符串
        String result = "";
        for (int i = 0; i < size; ++i) {
            //生成一个97~122之间的int类型整数
            int intVal = (int) (Math.random() * 26 + 97);
            //强制转换（char）intVal 将对应的数值转换为对应的字符，并将字符进行拼接
            result = result + (char) intVal;
        }
        //输出字符串
        return result;
    }


}
