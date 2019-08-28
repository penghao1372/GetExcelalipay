package com.vy.ServiceImpl;

import com.vy.Service.UserService;
import com.vy.common.pojo.Emp;
import com.vy.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements  UserService{
    @Autowired
    private UserMapper userMapper;


    @Override
    public List<Emp> findAll() {
        List<Emp> all = userMapper.findAll();
        System.out.println(all);
        return all;
    }
}
