package com.shangma.cn.utils.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String obj2Str(Object o){
        try {
            String string = objectMapper.writeValueAsString(o);
            return string;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static <T> T str2Obj(String str, Class<T> tClass){
        try {
            T t = objectMapper.readValue(str, tClass);
            return t;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
