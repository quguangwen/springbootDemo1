package com.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@ApiModel(description = "百川推单信息")
public class BChanelBean {
    @ApiModelProperty(value = "百川推单表ID")
    private long id;
    @ApiModelProperty(value = "百川订单号")
    private long orderNo;
    @ApiModelProperty(value = "订单生成时间")
    private String orderTime;
    @ApiModelProperty(value = "推单是否有效")
    private String validFlag;
    @ApiModelProperty(value = "百川客户姓名")
    private String Name;
    @ApiModelProperty(value = "百川客户身份证号")
    private String idNo;
    @ApiModelProperty(value = "百川客户手机号")
    private String mobile;
    @ApiModelProperty(value = "百川客户职业")
    private String occupationNature;
    @ApiModelProperty(value = "百川推单产品")
    private String productCode;
    @ApiModelProperty(value = "百川绑定销售工号")
    private String salesNo;
    @ApiModelProperty(value = "百川城市代码")
    private String cityCode;
    @ApiModelProperty(value = "渠道一")
    private String channelLevelOne;
    @ApiModelProperty(value = "渠道二")
    private String channelLevelTwo;
    @ApiModelProperty(value = "APP进件号")
    private String appLendRequestId;
    @ApiModelProperty(value = "进件生成时间")
    private String createTime;
    @ApiModelProperty(value = "推单更新时间")
    private String updateTime;
    @ApiModelProperty(value = "经度")
    private String longitude;
    @ApiModelProperty(value = "纬度")
    private String latitude;


}
