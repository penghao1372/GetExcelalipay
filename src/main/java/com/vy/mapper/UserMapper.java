package com.vy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vy.common.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;


public interface UserMapper extends BaseMapper<Emp> {


    List<Emp> findAll();
}
