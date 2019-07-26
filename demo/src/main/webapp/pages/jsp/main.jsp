<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>修改进件状态</title>
    <link rel="stylesheet" href="../static/layui/css/layui.css">
    <script type="text/javascript" src="../static/layui/layui.js"></script>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
</head>
<body>

<div class="layui-collapse">
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">附件状态修改</h2>
        <div class="layui-colla-content layui-show">
            <!--<form id="attachform" class="layui-form" action="/updateAsset" method="get">-->
            <form id="formAttachment" class="layui-form" onsubmit="return false" action="##" method="post">
                <div class="layui-form-item">
                    <label class="layui-form-label">输入手机号:</label>
                    <div class="layui-input-block">
                        <input type="text" name="mobile" required  lay-verify="required" placeholder="请输入手机号" autocomplete="off" class="layui-input" style="width:200px">
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-form-item">
                        <label class="layui-form-label">进件附件项:</label>
                        <div class="layui-input-block" style="width:200px">
                            <select name="type" lay-verify="required">
                                <option value ="">请选择附件项</option>
                                <option value ="CREDIT_REPORT">征信报告</option>
                                <option value ="OPERATOR_IDENTITY">运营商身份</option>
                                <option value ="TAOBAO_IDENTITY">淘宝身份</option>
                                <option value ="JD">京东</option>
                                <option value ="ALIPAY">支付宝</option>
                                <option value ="MOXIE_SOCIAL">魔蝎社保</option>
                                <option value ="ID_PHOTO">身份证明</option>
                                <option value ="LOAN_DEMAND">借款申请</option>
                                <option value ="OCCUPATION_INFO">职业信息</option>
                                <option value ="CONTACT_INFO">联系人信息</option>
                                <option value ="PERSONAL_INFO">基本信息</option>

                            </select>
                        </div>
                    </div>
                    <!--<div class="layui-input-block">-->
                        <!--<input type="text" name="type" required  lay-verify="required" placeholder="请输入附件名称" autocomplete="off" class="layui-input" style="width:200px">-->
                    <!--</div>-->
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">附件项状态:</label>
                    <div class="layui-input-block" style="width:200px">
                        <select name="status" lay-verify="required">
                            <option value ="">请选择状态</option>
                            <option value ="NOT_UPLOAD">未上传</option>
                            <option value ="QUARANTINE">待质检</option>
                            <option value ="QUALITY_THROUGH">质检通过</option>
                            <option value ="QUALITY_NO_THROUGH">质检未通过</option>
                            <option value ="OBTAINING">获取中</option>
                            <option value ="OBTAIN_SUCCESS">获取成功</option>
                            <option value ="OBTAIN_FAIL">获取失败</option>
                            <option value ="OFFLINE_SUBMISSION">线下提交</option>
                            <option value ="AUDIT_NOT_PASS">审查未通过</option>
                            <option value ="PAST_DUE_FAILURE">过期</option>
                        </select>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button id="attachbutton" class="layui-btn layui-btn-normal" lay-submit lay-filter="formDemo">立即提交</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">进件状态修改</h2>
        <div class="layui-colla-content layui-show">
            <!--<form id="formAppStatus" class="layui-form" action="/updateLendApp" method="get">-->
            <form id="formAppStatus" class="layui-form" onsubmit="return false" action="##" method="post">
                <div class="layui-form-item">
                    <label class="layui-form-label">输入手机号:</label>
                    <div class="layui-input-block">
                        <input type="text" name="mobile"  lay-verify="required" placeholder="请输入手机号" autocomplete="off" class="layui-input" style="width:200px">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">进件状态:</label>
                    <div class="layui-input-block" style="width:200px">
                        <select name="state_type" lay-verify="required">
                            <option value ="">请选择状态</option>
                            <option value ="CUSTOMER_RECORD">客户录件</option>
                            <option value ="SALE_EXAMINE">销售质检</option>
                            <option value="AUDIT_STORE">门店审查</option>
                            <option value="AUDIT_CREDIT">信审审核</option>
                            <option value="APPROVED">个贷批核</option>
                            <option value="LEND_REJECTED">拒贷</option>
                            <option value="CANCEL_SIGN">门店放弃</option>
                            <option value="CANCEL_AUDIT">用户放弃</option>
                            <option value="MAIN_CARD_AUTHENTICATION">主卡鉴权</option>
                            <option value="VICE_CARD_AUTHENTICATION">副卡鉴权</option>AUDIT
                            <option value="WAIT_CONTRACT">等待电子签章</option>
                            <option value="CONFIRM_CONTRACT">确认冷静期</option>
                            <option value="CONFIRM_CONTRACT_SUCCESS">确认合同成功</option>
                            <option value="SIGN_AUDITING">签约审核中</option>
                            <option value="SIGN_SUCCESS">签约成功</option>
                            <option value="PROCESSING">放款成功</option>
                            <option value="PROCESSING_ERROR">放款失败</option>
                            <option value="AUDIT_VIDEO">去视频</option>
                            <option value="AUDIT_VIDEO_INTERRUPT">视频中断</option>
                            <option value="AUDIT_VIDEO_CONFIRM">视频审核中</option>
                            <option value="QUALIFICATION_DISCREPANCY">资质不符</option>
                            <option value="APPROVED_FALLBACK">批核转车贷兜底</option>
                            <option value="REJECTED_FALLBACK">拒贷转车贷兜底</option>
                            <option value="APPROVED_CAR_RETURN">批核-转车贷材料驳回</option>
                            <option value="REJECTED_CAR_RETURN">拒贷-转车贷材料驳回</option>
                            <option value="APPROVED_CAR_CHANGE">批核-转车贷资质不符</option>
                            <option value="REJECTED_CAR_CHANGE">拒贷-转车贷资质不符</option>
                            <option value="WAIT_CONFIRM_PRODUCT">等待确认产品</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button id="appstatus" class="layui-btn layui-btn-normal" lay-submit lay-filter="formDemo">立即提交</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">清空钢铁侠设备</h2>
        <div class="layui-colla-content layui-show">
            <!--<form class="layui-form" action="/clearDeviceToken" method="POST">-->
            <form id="formDevice" onsubmit="return false" action="##" method="post">
                <div class="layui-form-item">
                    <label class="layui-form-label">清空设备:</label>
                    <div class="layui-input-block">
                        <input type="text" name="saleID" placeholder="请输入销售工号或者不输入" autocomplete="off" class="layui-input" style="width:200px">
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button id="device" class="layui-btn layui-btn-normal" lay-submit>立即提交</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">查询身份证手机号加密</h2>
        <div class="layui-colla-content layui-show">
            <form id="mcform"  onsubmit="return false" action="##" method="post">
                <div class="layui-form-item">
                    <label class="layui-form-label">请输入:</label>
                    <div class="layui-input-block">
                        <input id ="mcArray" type="text" name="array" placeholder="请输入手机号或者身份证号" autocomplete="off" class="layui-input" style="width:200px">
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button id="submc" class="layui-btn layui-btn-normal" lay-submit lay-filter="formDemo">立即提交</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">查询销售绑定进件</h2>
        <div class="layui-colla-content layui-show">
            <!--<form class="layui-form" action="/clearDeviceToken" method="POST">-->
            <form id="formuser" onsubmit="return false" action="##" method="post">
                <div class="layui-form-item">
                    <label class="layui-form-label">销售工号:</label>
                    <div class="layui-input-block">
                        <input type="text" name="saleno" placeholder="请输入销售工号或者不输入" autocomplete="off" class="layui-input" style="width:200px">
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button id="salesub" class="layui-btn layui-btn-normal" lay-submit>立即提交</button>
                    </div>
                </div>
            </form>
            <div style="margin: 10px 50px 50px 50px">
        <table class="layui-hide" id="customertable"></table>
        </div>
        </div>  
    </div> 
    
     
    


</div>
<script>

layui.use('element', function(){})
layui.use('form', function(){})





'use strict';
$(function(){
    sub("#device","#formDevice","/clearDeviceToken");
    sub("#appstatus","#formAppStatus","/updateLendApp");
    sub("#attachbutton","#formAttachment","/updateAsset");
    sub("#submc","#mcform","/getEncrStr");
    showTable("#salesub","#formuser","/getCustomer");
})

var sub = function(buttonName, formName, url){
     $(buttonName).click(function(){
       $.ajax({
        type: "POST",
        url: url,
        data: $(formName).serialize(),
        dataType: "json",
        success: function(data){
           alert("code: " + data.code + "," + "message: " + data.message + "," + "result: " + data.result );
        },
        error : function() {
        alert("异常！");
        }
         });
    })
}

var showTable = function(buttonName, formName, url){
    $(buttonName).click(function(){
       $.ajax({
        type: "POST",
        url: url,
        data: $(formName).serialize(),
        dataType: "json",
        success: function(data){
           
           // console.log( data.code );
           // console.log(data.message); console.log(data.result);
           var result = data.result;
           layui.use('table', function(){
            var table = layui.table;
            table.render({
            elem: '#customertable'
            ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,data:result
            ,page:true
            ,cols: [[
              {field:'id',width:80, title: 'ID', sort: true}
              ,{field:'sales_no', width:80, title: '销售工号'} 
              ,{field:'customer_name', width:120, title: '用户名'}
              ,{field:'mobile', width:'30%', title: '手机号'}
              ,{field:'id_no',title: '身份证'}
              //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
            ]]
            });
            });

        },
        error : function() {
        alert("异常！");
        }
         });
    })
}
</script>

</body>
</html>



