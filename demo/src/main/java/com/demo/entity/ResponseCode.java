package com.demo.entity;

public enum ResponseCode {

    OK("200", "OK"),
    ERROR("400","Bad Request");

    private final String value;
    private final String message;

    ResponseCode(String value, String reasonPhrase) {
        this.value = value;
        this.message = reasonPhrase;
    }
    @Override
    public String toString() {
        return this.value;
    }

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }




}
