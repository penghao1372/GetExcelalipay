package com.vy.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
@ToString
@Data
@Accessors(chain = true)
@TableName("tb_data")
public class TbData {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String userName;
    private Date createTime;
    private Double random;
}
