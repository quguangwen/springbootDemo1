package com.demo.entity;

public class FinupLend {
    private long id;
    private String status;
    private long app_lend_request_id ;
    private long lend_customer_id;
    private String sub_status;


    public long getLend_customer_id() {
        return lend_customer_id;
    }

    public void setLend_customer_id(long lend_customer_id) {
        this.lend_customer_id = lend_customer_id;
    }

    public long getAppLendRequestID() {
        return app_lend_request_id;
    }

    public void setAppLendRequestID(long appLendRequestID) {
        this.app_lend_request_id = appLendRequestID;
    }

    public long getApp_lend_requestID() {
        return app_lend_request_id;
    }

    public void setApp_lend_requestID(long app_lend_requestID) {
        this.app_lend_request_id = app_lend_requestID;
    }

    public String getSub_status() {
        return sub_status;
    }

    public void setSub_status(String sub_status) {
        this.sub_status = sub_status;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}
