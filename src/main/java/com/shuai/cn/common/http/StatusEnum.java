package com.shuai.cn.common.http;

public enum StatusEnum {
    ERROR(11111,"操作失败"),
    SUCCESS(20000,"操作成功");

    private int code;
    private String message;


    StatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
