package com.demo.util;

/**
 * @Author:abc
 * @Description:名字+身份证号数据实体类
 * @params:$
 * @return: $
 * @Date: $ $
 */

public class NameData {
    private String name;
    private String dateStr;
    private String idCardNumber;


    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }


}
