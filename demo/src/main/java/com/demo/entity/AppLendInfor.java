package com.demo.entity;

public class AppLendInfor {

    private long id;
    private String mobile;
    private String state_type;
    private String product_type;
    private String magic_data_center_id;


    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getState_type() {
        return state_type;
    }

    public void setState_type(String state_type) {
        this.state_type = state_type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMagic_data_center_id() {
        return magic_data_center_id;
    }

    public void setMagic_data_center_id(String magic_data_center_id) {
        this.magic_data_center_id = magic_data_center_id;
    }

}

