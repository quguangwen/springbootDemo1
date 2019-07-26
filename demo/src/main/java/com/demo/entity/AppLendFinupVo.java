package com.demo.entity;

public class AppLendFinupVo {

    private long id;
    private String status;
    private long app_lend_request_id ;
    private String state_type;
    private long lend_customer_id;


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

    public long getApp_lend_request_id() {
        return app_lend_request_id;
    }

    public void setApp_lend_request_id(long app_lend_request_id) {
        this.app_lend_request_id = app_lend_request_id;
    }

    public long getLend_customer_id() {
        return lend_customer_id;
    }

    public void setLend_customer_id(long lend_customer_id) {
        this.lend_customer_id = lend_customer_id;
    }

    public String getState_type() {
        return state_type;
    }

    public void setState_type(String state_type) {
        this.state_type = state_type;
    }

}
