package com.demo.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "APP系统进件信息")
public class AppLendFinupVo {
    @ApiModelProperty(value = "APP用户注册ID")
    private long logInId;
    @ApiModelProperty(value = "APP客户ID")
    private long appCustomerId;
    @ApiModelProperty(value = "APP进件ID")
    private long id;
    @ApiModelProperty(value = "个贷系统进件状态")
    private String status;
    @ApiModelProperty(value = "APP进件ID")
    private long appLendRequestId ;
    @ApiModelProperty(value = "APP进件状态")
    private String stateType;
    @ApiModelProperty(value = "个贷系统客户ID")
    private long lendCustomerId;
    @ApiModelProperty(value = "个贷系统子状态")
    private String subStatus;


}
