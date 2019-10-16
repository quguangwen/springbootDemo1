
package com.demo.Controller;

import com.demo.entity.FinupLend;
import com.demo.entity.ResponseVo;
import com.demo.service.DeleteSale;
import com.demo.service.SearchEncrypt;
import com.demo.service.app.AppCustomerService;
import com.demo.service.finup.LendRequestService;
import com.demo.service.app.UpdateLendAppStatus;
import com.demo.util.AesUtils;
import com.demo.util.ReadProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Controller
@Slf4j
@Api(value = "这是全部接口")
public class updateApp {

    @Autowired
    UpdateLendAppStatus updateLendAppStatus;
    @Autowired
    LendRequestService lendRequestService;
    @Autowired
    DeleteSale deleteSale;
    @Autowired
    SearchEncrypt searchEncrypt;
    @Autowired
    AppCustomerService appCustomerService;

    private String date = LocalDateTime.now().toString();



//
//    @RequestMapping("/")
//    public String test(Model model) {
//        Map<String, String> map = new HashMap<String,String>();
//
//        map.put("NOT_UPLOAD","未上传");
//        map.put("QUARANTINE","质检中");
//        map.put("QUALITY_THROUGH","质检成功");
//        model.addAttribute("typeMap",map);
//        return "main";
//
//    }



    @RequestMapping(value = "/index")
    public String test(){
        log.debug("登录主页");
        return "index";
    }

    @RequestMapping(value = "/updateAsset", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改进件状态",httpMethod = "POST")
    public ResponseVo<Object> updateAttachState(
            @RequestBody
            @ApiParam(value = "mobile", name = "客户手机号 ", required = true)
            @RequestParam("mobile") String mobile,
            @ApiParam(value = "status", name = "附件状态 ", required = true)
            @RequestParam("status") String status,
            @ApiParam(value = "type", name = "附件类型 ", required = true)
            @RequestParam("type") String type  ) {
       int a = updateLendAppStatus.updateAppAttachState(mobile.trim(), type.trim(), status.trim());
       //System.out.println(a);
       if(a == 1){
           log.info("修改成功");
           return ResponseVo.ofSuccess();
       } else{
           log.error("修改失败");
           return ResponseVo.ofError("update failed");
       }
    }
    @RequestMapping(value = "/getRequest", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取进件ID",httpMethod = "POST")
    public String getLendRequest(@RequestParam("id") long id){
        FinupLend finupLend = lendRequestService.getLendRequest(id);
        log.info("获取进件ID： " + finupLend.getId());
        return finupLend.getId() + "  " + finupLend.getStatus();

    }

    @RequestMapping(value = "/clearDeviceToken", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "清空钢铁侠DeviceToken",httpMethod = "POST")
    public ResponseVo<Object> deleteSaleDeviceToken(@RequestParam("saleID") String saleID){
        int a = deleteSale.delete(saleID.trim());
        log.info(saleID + " 删除成功 ");
        return ResponseVo.ofSuccess();
    }

    @RequestMapping(value = "/updateLendApp", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改个贷进件状态",httpMethod = "POST")
    public ResponseVo<Object> updateAppState(@RequestParam("mobile") String mobile, @RequestParam("state_type") String state_type){

        int a = updateLendAppStatus.updateAppFinup(mobile.trim(),state_type.trim());
        if(a == 1){
            log.info(mobile + "更新成功" );
            return ResponseVo.ofSuccess();
        } else {
            log.error(mobile + "更新失败");
            return ResponseVo.ofError("update failed");
        }

    }

    @RequestMapping(value = "/getEncrpyt", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "加密和解密信息接口",httpMethod = "GET")
    public ArrayList getEncryptString(@RequestParam("array") ArrayList arrayList) {
        ArrayList arrayList1 = searchEncrypt.getEncrpt(arrayList);
        return arrayList1;
    }

    @RequestMapping(value = "/getDecrpyt", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "加密和解密信息接口返回列表",httpMethod = "GET")
    public ArrayList getDecryptString(@RequestParam("array") ArrayList arrayList) {
        ArrayList arrayList1 = searchEncrypt.getDecrpt(arrayList);
        return arrayList1;
    }

    @RequestMapping(value="/getEncrStr", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "加密和解密信息接口返回列表",httpMethod = "GET")
    public ResponseVo<Object> getEncryptStr(@RequestParam("array") String array){
        String s= searchEncrypt.getEncrpt(array);
        ResponseVo vo = new ResponseVo();
        vo.setResult(s);
        return ResponseVo.ofSuccess(vo.getResult());
    }

    @RequestMapping(value = "/getCustomer", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询该销售关联的所有进件",httpMethod = "POST")
    public ResponseVo<Object> getCustReslist(@RequestParam("saleno") String saleno, @RequestParam("mobile") String mobile, @RequestParam("product_name") String product_name){
        HashMap<String, String> hashMapParam = new HashMap<>();
        hashMapParam.put("sale_no",saleno.trim());
        hashMapParam.put("mobile",AesUtils.encrypt(mobile.trim()));
        hashMapParam.put("product_name",product_name.trim());
        System.out.println(hashMapParam.get("sale_no") + " ," + hashMapParam.get("mobile") + " ," + hashMapParam.get("product_name") + " ");

        List list = appCustomerService.getCustInfor(hashMapParam);
        if(list.size() > 0){
            log.info("查询结果" + list.size() + "条记录");
            ResponseVo vo = new ResponseVo();
            vo.setResult(list);
            return ResponseVo.ofSuccess(vo.getResult());
        } else {
            log.info("查询结果为空");
            return ResponseVo.ofError("改销售无进件");
        }

    }

    @RequestMapping(value = "/getAppFinupInfor", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "根据手机号查询相关联进件信息",httpMethod = "POST")
    public ResponseVo<Object> getAppFinupInfor(@RequestParam("mobile") String mobile){
        List list = appCustomerService.getAppFinupResult(mobile.trim());
        ResponseVo responseVo = new ResponseVo();
        if(list.size() > 0){
            log.info("查询结果" + list.size() + "条记录");
            responseVo.setResult(list);
            return ResponseVo.ofSuccess(responseVo.getResult());
        } else {
            log.info("查询结果为空");
            return ResponseVo.ofError("改销售无进件");
        }
    }

    @RequestMapping(value="/getAppStateItem", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获得个贷进件状态列表",httpMethod = "GET")
    public ResponseVo<Object> getAppStateItem(){
        HashMap<Object, Object> hashMap = new HashMap();
        hashMap = ReadProperties.getResourceMap("state.properties");
        ResponseVo responseVo = new ResponseVo();
        responseVo.setResult(hashMap);
        return ResponseVo.ofSuccess(responseVo.getResult());
    }


    @RequestMapping(value="/getAttachmentStatus", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获得附件状态列表",httpMethod = "GET")
    public ResponseVo<Object> getAttachmentStatus(){
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap = ReadProperties.getResourceMap("attachmentStatus.properties");
        ResponseVo responseVo = new ResponseVo();
        responseVo.setResult(hashMap);
        return responseVo.ofSuccess(responseVo.getResult());
    }

    @RequestMapping(value="/testNoMapping", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "这是一个测试",httpMethod = "GET")
    public List getResult(){
        List<HashMap> list = new ArrayList();
        list = appCustomerService.getTest();
        return list;
    }

    @RequestMapping(value="/getProductSec", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询产品名称列表",httpMethod = "GET")
    public ResponseVo<Object> getProName(){
        List<HashMap> list = new ArrayList<>();
        list = appCustomerService.getProduct();
        ResponseVo responseVo = new ResponseVo();
        responseVo.setResult(list);
        return responseVo.ofSuccess(responseVo.getResult());
    }
    @RequestMapping(value = "/getProductList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获得产品列表",httpMethod = "GET")
    public ResponseVo<Object> getProductName(){
        List<HashMap> list = new ArrayList<>();
        list = appCustomerService.getProduct();
        ResponseVo responseVo = new ResponseVo();
        responseVo.setResult(list);
        return responseVo.ofSuccess(responseVo.getResult());
    }



    @RequestMapping(value = "/getAttachByPro", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Object> getAttachmentByProduct(@RequestParam("product_type") String product_type){
       ResponseVo responseVo = new ResponseVo();
       List<HashMap> list = new ArrayList<>();
       list = appCustomerService.getAttachmentList(product_type);
       responseVo.setResult(list);
       return responseVo.ofSuccess(responseVo.getResult());
    }

    @RequestMapping(value = "/selectSocial", method = RequestMethod.POST)
    @ResponseBody
    public String getSelectResult (@RequestParam("mobile") String mobile, @RequestParam("path") String path)  {
        ResponseVo responseVo = new ResponseVo();
        String applyNo = null;
        try{
            applyNo = updateLendAppStatus.getMagicDataID(mobile.trim());
        }catch (Exception e){
            return e.getLocalizedMessage();
        }
        return updateLendAppStatus.client(path,applyNo,"204","true");

    }

    @RequestMapping(value = "/bcPush", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "百川推单接口",httpMethod = "POST")
    public String getBCTest(
            @RequestBody
            @ApiParam(value = "id_no", name = "客户身份证号 ", required = true)
            @RequestParam("id_no") String id_no,
            @ApiParam(value = "product_name", name = "产品名称 ", required = true)
            @RequestParam("product_name") String product_code,
            @ApiParam(value = "occupation_nature", name = "客户职业类型 ", required = true)
            @RequestParam("occupation_nature") String occupation_nature,
            @ApiParam(value = "sale_no11", name = "绑定销售工号 ", required = true)
            @RequestParam("sale_no11") String sale_no,
            @ApiParam(value = "channelLevelOne", name = "渠道一 ", required = true)
            @RequestParam("channelLevelOne") String channelLevelOne,
            @ApiParam(value = "channelLevelTwo", name = "渠道二 ", required = true)
            @RequestParam("channelLevelTwo") String channelLevelTwo,
            @ApiParam(value = "flag", name = "推单有效标签 ", required = true)
            @RequestParam("flag") String flag,
            @ApiParam(value = "shopName", name = "推单门店 ", required = true)
            @RequestParam("shopName") String shopName)
                            {
        ResponseVo responseVo = new ResponseVo();
        String s =updateLendAppStatus.bcPush(id_no.trim(),
                product_code.trim(),
                occupation_nature.trim(),
                sale_no.trim(),
                channelLevelOne.trim(),
                channelLevelTwo.trim(),
                flag.trim(),
                shopName.trim());
        return s;

    }


}
