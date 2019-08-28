package com.vy.ServiceImpl;

import com.vy.Service.TbDataService;
import com.vy.common.pojo.TbData;
import com.vy.mapper.TbDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbDataServiceImpl implements TbDataService {
    @Autowired
    TbDataMapper tbDataMapper;

    @Override
    public List<TbData> getData() {
            List<TbData> tbData = tbDataMapper.selectList(null);
            return tbData;
    }
}
