package com.vy.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class jasonMapperUtil {
    private static final ObjectMapper mapper=new ObjectMapper();
    /**将对象转化为json格式的字符串*/
    public  static  String toJson(Object target){
        String result=null;
        try {
            result = mapper.writeValueAsString(target);
        }catch (Exception e){
        e.printStackTrace();
        throw new RuntimeException(e);
        }
        return  result;
    }
    /**将json对象转化为字符串*/
    public  static <T> T toObject(String json,Class<T> target){
        T t=null;
        try {
        t=mapper.readValue(json,target);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return t;
    }
}
