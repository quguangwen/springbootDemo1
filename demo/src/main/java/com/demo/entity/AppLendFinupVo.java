package com.demo.entity;


import lombok.Data;

@Data
public class AppLendFinupVo {

    private long logInId;

    private long appCustomerId;

    private long id;

    private String status;

    private long appLendRequestId ;

    private String stateType;

    private long lendCustomerId;


}
