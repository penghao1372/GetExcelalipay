package com.vy;

import com.vy.common.pojo.Emp;
import com.vy.common.pojo.TbData;
import com.vy.mapper.TbDataMapper;
import com.vy.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemeossmApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void findAll() {
        List<Emp> all = userMapper.findAll();
        System.out.println(all);
    }
    @Autowired
    TbDataMapper tbDataMapper;
    @Test
    public void addUser(){

            List<TbData> tbData = tbDataMapper.selectList(null);
            System.out.println(tbData);
    }
}
