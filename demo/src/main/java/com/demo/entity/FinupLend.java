package com.demo.entity;

public class FinupLend {
    private long id;
    private String status;
    private long app_lend_requestID ;
    private long lend_customer_id;


    public long getLend_customer_id() {
        return lend_customer_id;
    }

    public void setLend_customer_id(long lend_customer_id) {
        this.lend_customer_id = lend_customer_id;
    }

    public long getAppLendRequestID() {
        return app_lend_requestID;
    }

    public void setAppLendRequestID(long appLendRequestID) {
        this.app_lend_requestID = appLendRequestID;
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
