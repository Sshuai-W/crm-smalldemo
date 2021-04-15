package com.shangma.cn.common.http;

public class AxiosResult<T>{

    private T data;
    private int code;
    private String message;

    private AxiosResult() {
    }

    private AxiosResult(T data, StatusEnum e) {
        this.data = data;
        this.code = e.getCode();
        this.message = e.getMessage();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
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

    private static <T> AxiosResult<T> getAxiosResult(T data, StatusEnum e){
        return new AxiosResult<T>(data,e);
    }

    public static <T> AxiosResult<T> success(){
        return new AxiosResult<>(null,StatusEnum.SUCCESS);
    }
    public static <T> AxiosResult<T> success(T data){
        return new AxiosResult<>(data,StatusEnum.SUCCESS);
    }
    public static <T> AxiosResult<T> success(T data, StatusEnum e){
        return new AxiosResult<>(data,e);
    }
    public static <T> AxiosResult<T> error(){
        return new AxiosResult<>(null, StatusEnum.ERROR);
    }
    public static <T> AxiosResult<T> error(StatusEnum e){
        return new AxiosResult<>(null, e);
    }
}
