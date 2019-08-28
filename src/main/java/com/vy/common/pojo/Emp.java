package com.vy.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data                   //生成get set 方法
@NoArgsConstructor      //开启无 参构造
@Accessors(chain = true) //开启链式加载
@TableName
public class Emp {
      @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String job;
    private Integer topid;
    private Date hdate;
    private Integer sal;
    private Integer bonus;
    private  Integer deptId;
}
