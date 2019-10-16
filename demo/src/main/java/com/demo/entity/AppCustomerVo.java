package com.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(description = "客户进件信息")
public class AppCustomerVo {

    @ApiModelProperty(value = "APP进件ID")
    private long id;
    @ApiModelProperty(value = "APP客户姓名")
    private String customerName;
    @ApiModelProperty(value = "APP客户手机号")
    private String mobile;
    @ApiModelProperty(value = "APP客户身份证号")
    private String idNo;
    @ApiModelProperty(value = "绑定销售工号")
    private String salesNo;
    @ApiModelProperty(value = "产品名称")
    private String productName;
    @ApiModelProperty(value = "进件状态")
    private String stateType;

}
