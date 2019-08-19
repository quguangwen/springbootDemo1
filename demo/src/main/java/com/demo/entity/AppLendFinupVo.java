package com.demo.entity;

import lombok.Getter;
import lombok.Setter;

public class AppLendFinupVo {

    @Getter @Setter
    private long id;
    @Getter @Setter
    private String status;
    @Getter @Setter
    private long app_lend_request_id ;
    @Getter @Setter
    private String state_type;
    @Getter @Setter
    private long lend_customer_id;


}
