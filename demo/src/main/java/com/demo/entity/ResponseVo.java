package com.demo.entity;

import javax.xml.ws.Response;
import java.io.Serializable;

public class ResponseVo<T> implements Serializable {

    private String code;
    private String message;
    private T result;

    public ResponseVo(){

    }

    public ResponseVo(String code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public static <T> ResponseVo<T> ofSuccess() {
        return new ResponseVo(ResponseCode.OK.getValue(), ResponseCode.OK.getMessage(), null);
    }

//    public static <T> ResponseVo<T> ofSuccess(T result) {
//        return new ResponseVo(ResponseCode.OK.getValue(), ResponseCode.OK.getMessage(), result);
//    }

    public static <T> ResponseVo<T> ofSuccess(Object object){
        return new ResponseVo(ResponseCode.OK.getValue(), ResponseCode.OK.getMessage(), object);
    }
    public static <T> ResponseVo<T> ofError(String code, String message) {
        return new ResponseVo(code, message, null);
    }

    public static <T> ResponseVo<T> ofError(String message) {
        return new ResponseVo("500", message, null);
    }

    public static <T> ResponseVo<T> ofError(ResponseCode responseCode) {
        return new ResponseVo<>(responseCode.getValue(), responseCode.getMessage(), null);
    }


}
