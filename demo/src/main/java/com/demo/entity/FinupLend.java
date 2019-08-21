package com.demo.entity;

import lombok.Data;

@Data
public class FinupLend {
    private long id;
    private String status;
    private long appLendRequestId ;
    private long lendCustomerId;
    private String subStatus;

}
