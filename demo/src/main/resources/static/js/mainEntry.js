

layui.use('form', function(){
    var form =layui.form;
})


$(function(){

    loadProductList();
    loadProduct1();
    loadProductSec();
    loadProAttach();
    loadSelectAppState();
    loadSelectAttachmentState();
    sub("#device","#formDevice","/clearDeviceToken");
    sub("#appstatus","#formAppStatus","/updateLendApp");
    sub("#attachbutton","#formAttachment","/updateAsset");
    sub("#submc","#mcform","/getEncrStr");
    sub("#bcsub","#bcChanel","/bcPush");
    showTable("#salesub","#formuser","/getCustomer");
    showAppFinupTable("#appSub","#appForm","/getAppFinupInfor");


})