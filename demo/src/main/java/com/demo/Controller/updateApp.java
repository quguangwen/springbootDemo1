
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.net.www.http.HttpClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Controller
//@Scope("prototype")
@Slf4j
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


//    @RequestMapping("/main")
//    public String testMain(){
//        log.debug("登录主页");
//        return "main";
//    }

    @RequestMapping("/index")
    public String test(){
        log.debug("登录主页");
        return "index";
    }



    @RequestMapping(value = "/updateAsset", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Object> updateAttachState(@RequestParam("mobile") String mobile, @RequestParam("status") String status, @RequestParam("type") String type){
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
    public String getLendRequest(@RequestParam("id") long id){
        FinupLend finupLend = lendRequestService.getLendRequest(id);
        log.info("获取进件ID： " + finupLend.getId());
        return finupLend.getId() + "  " + finupLend.getStatus();

    }

    @RequestMapping(value = "/clearDeviceToken", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Object> deleteSaleDeviceToken(@RequestParam("saleID") String saleID){
        int a = deleteSale.delete(saleID.trim());
        log.info(saleID + " 删除成功 ");
        return ResponseVo.ofSuccess();
    }

    @RequestMapping(value = "/updateLendApp", method = RequestMethod.POST)
    @ResponseBody
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
    public ArrayList getEncryptString(@RequestParam("array") ArrayList arrayList) {
        ArrayList arrayList1 = searchEncrypt.getEncrpt(arrayList);
        return arrayList1;
    }

    @RequestMapping(value = "/getDecrpyt", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList getDecryptString(@RequestParam("array") ArrayList arrayList) {
        ArrayList arrayList1 = searchEncrypt.getDecrpt(arrayList);
        return arrayList1;
    }

    @RequestMapping(value="/getEncrStr", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Object> getEncryptStr(@RequestParam("array") String array){
        String s= searchEncrypt.getEncrpt(array);
        ResponseVo vo = new ResponseVo();
        vo.setResult(s);
        return ResponseVo.ofSuccess(vo.getResult());
    }

    @RequestMapping(value = "/getCustomer", method = RequestMethod.POST)
    @ResponseBody
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
    public ResponseVo<Object> getAppStateItem(){
        HashMap<Object, Object> hashMap = new HashMap();
        hashMap = ReadProperties.getResourceMap("state.properties");
        ResponseVo responseVo = new ResponseVo();
        responseVo.setResult(hashMap);
        return ResponseVo.ofSuccess(responseVo.getResult());
    }


    @RequestMapping(value="/getAttachmentStatus", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVo<Object> getAttachmentStatus(){
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap = ReadProperties.getResourceMap("attachmentStatus.properties");
        ResponseVo responseVo = new ResponseVo();
        responseVo.setResult(hashMap);
        return responseVo.ofSuccess(responseVo.getResult());
    }

    @RequestMapping(value="/testNoMapping", method = RequestMethod.GET)
    @ResponseBody
    public List getResult(){
        List<HashMap> list = new ArrayList();
        list = appCustomerService.getTest();
        return list;
    }

    @RequestMapping(value="/getProductSec", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVo<Object> getProName(){
        List<HashMap> list = new ArrayList<>();
        list = appCustomerService.getProduct();
        ResponseVo responseVo = new ResponseVo();
        responseVo.setResult(list);
        return responseVo.ofSuccess(responseVo.getResult());
    }
    @RequestMapping(value = "/getProductList", method = RequestMethod.GET)
    @ResponseBody
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
    public String getBCTest(@RequestParam("id_no") String id_no,
                            @RequestParam("product_name") String product_code,
                            @RequestParam("occupation_nature") String occupation_nature,
                            @RequestParam("sale_no11") String sale_no,
                            @RequestParam("channelLevelOne") String channelLevelOne,
                            @RequestParam("channelLevelTwo") String channelLevelTwo,
                            @RequestParam("flag") String flag,
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
