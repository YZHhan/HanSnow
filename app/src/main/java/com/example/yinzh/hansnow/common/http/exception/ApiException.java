package com.example.yinzh.hansnow.common.http.exception;

/*
 * @Author yinzh
 * @Date   2018/7/1 11:16
 * @Description: Api  异常管理
 */
public class ApiException extends Exception {

    private final int code;
    private String message;

    public ApiException(Throwable throwable, int code) {
        super();
        this.code = code;
        this.message = throwable.getMessage();
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ApiException setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getDisplayMessage(){
        return message + "(code:" + code + ")";
    }


}
