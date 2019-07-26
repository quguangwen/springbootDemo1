
package com.demo.service.app;

import com.alibaba.fastjson.JSONObject;
import com.demo.entity.AppLendInfor;
import com.demo.entity.AttachBean;
import com.demo.entity.BChanelBean;
import com.demo.mapper.AppLendInforMapper;
import com.demo.mapper.AttachmentMapper;
import com.demo.service.finup.LendRequestService;
import com.demo.util.AesUtils;
import com.demo.util.OAuthTokenUtil;
import com.demo.util.ReadProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


@Slf4j
@Service
public class UpdateLendAppStatus {

    @Autowired
    LendRequestService lendRequestService;
    @Autowired
    AppLendInfor appLendInfor;
    @Autowired
    AttachBean attachBean;
    @Autowired
    AttachmentMapper attachmentMapper;
    @Autowired
    AppLendInforMapper appLendInforMapper;
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    BChanelBean bChanelBean;




    public int updateAppAttachState(String mobile, String type, String status){
        log.info("开始更新附件" + type + " :" + status);
        String encryptMobile = AesUtils.encrypt(mobile);
        attachBean.setType(type);
        attachBean.setMobile(encryptMobile);
        attachBean.setStatus(status);
       // DatabaseContextHolder.setDatabaseType(DatabaseType.lend_app);
        int a = attachmentMapper.updateAppStatus(attachBean);
        return a;
    }

    public long getAppRequestID(String mobile){
        log.info("开始查询进件ID");
        String encryptMobile = AesUtils.encrypt(mobile);
        appLendInfor = appLendInforMapper.getAppRequestID(encryptMobile);
        long appID = appLendInfor.getId();
        log.info("进件ID: " + appID);
        return appID;
    }

    public int updateAppByID(String mobile, String state_type){
        String encryptMobile = AesUtils.encrypt(mobile);
        System.out.println(state_type);
        log.info("根据手机号查找APP进件号：");
        long id = getAppRequestID(mobile);
        System.out.println("applendrequest :" + id);

        System.out.println("State_type:*********** " + appLendInfor.getState_type());

        if(isSpecialProduct(mobile)) {
            if (state_type.equals("CONFIRM_CONTRACT_SUCCESS")) {
                appLendInfor.setState_type("SIGN_AUDITING");
                System.out.println(appLendInfor.getState_type());
            }
        }

        if(state_type.equals("MAIN_CARD_AUTHENTICATION") || state_type.equals("VICE_CARD_AUTHENTICATION")){
            appLendInfor.setState_type("APPROVED");
        } else{
            appLendInfor.setState_type(state_type);
        }


        appLendInfor.setId(id);
        appLendInfor.setMobile(encryptMobile);
        System.out.println("***********" + appLendInfor.getState_type());
        int a = appLendInforMapper.updateAppStateByID(appLendInfor);
        return a;
    }

    public int updateAppFinup(String mobile, String state_type){
        int a = 0;
        long id = 0L;
        if(state_type.equals("PASS_FAILED")){
            a = updateAppByID(mobile,"SIGN_SUCCESS");
            if(a ==1){
                id = getAppRequestID(mobile);
                lendRequestService.updateFinup(id,"PASS_FAILED");
            }
        } else if(state_type.equals("PROCESSING_ERROR")) {
            a= updateAppByID(mobile, "PROCESSING");
            if(a == 1){
                id = getAppRequestID(mobile);
                lendRequestService.updateFinup(id, state_type);
            }
        } else {
            a = updateAppByID(mobile,state_type);
            if(a == 1){
                id = getAppRequestID(mobile);
                lendRequestService.updateFinup(id, state_type);
            }
        }

        return a;
    }

    public boolean isSpecialProduct(String mobile){
        boolean flag= false;
        long appid = getAppRequestID(mobile);
        System.out.println("appid: " + appid);
        appLendInfor = appLendInforMapper.getProductType(appid);
        String product_type = appLendInfor.getProduct_type();
        if(!product_type.equals("") || !product_type.equals(null)){
            ArrayList arrayList = ReadProperties.getProductList();
            for(int i=0;i<arrayList.size();i++){
                if(product_type.equals(arrayList.get(i))){
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    public String getMagicDataID(String mobile){
        long appid = getAppRequestID(mobile);
        appLendInfor = appLendInforMapper.getMagicDataID(appid);
        String magicID = appLendInfor.getMagic_data_center_id();
        return magicID;
    }

    public String client(String path1, String applyNO, String corpID, String durex ){
        String url = ReadProperties.getValue("dataCenterURL") +path1 + "?" + "applyNo=" + applyNO + "&durex=" + durex +  "&corpId=" + corpID;
        System.out.println("data center url: " + url);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,String.class);
        return responseEntity.getBody();
    }


    public String bcPush(String id_no,String product_code, String occupation_nature, String sale_no){
        boolean flag = true;
        long order_no =0;
        while(flag){
            order_no = (long)((Math.random()*9+1)*10000);
            long ab = appLendInforMapper.searchDupOrderNo(order_no);
            if(ab == 0){
               flag = false;
            }
        }

        long idCheck = appLendInforMapper.searchDupID(AesUtils.encrypt(id_no));
        if(idCheck != 0){
            appLendInforMapper.deleteDupID(AesUtils.encrypt(id_no));
        }
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        String url = "http://finup-lend-app-server.lendapp.beta/api/baichuan/push";
        bChanelBean.setChannelLevelOne("BC");
        bChanelBean.setChannelLevelTwo("BCAPP");
        bChanelBean.setCityCode("北京市");
        bChanelBean.setIdNo(id_no);
        bChanelBean.setMobile("18618439839");
        bChanelBean.setName("自动推单");
        bChanelBean.setOccupationNature(occupation_nature);
        bChanelBean.setOrderNo(order_no);
        bChanelBean.setOrderTime(dateFormat.format(date));
        bChanelBean.setProductCode(product_code);
        if(sale_no.equals(null) || sale_no.equals("")){
            bChanelBean.setSalesNo("BC0000000023");
        }else{
            bChanelBean.setSalesNo(sale_no);
        }

//        int a = appLendInforMapper.insertBCName(bChanelBean);
//        return a;
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(bChanelBean);
        String body = jsonObject.toJSONString();
        System.out.println(body);
        String oauthToken = OAuthTokenUtil.getOAuth2AccessToken().getValue();
        System.out.println("oauth: " + oauthToken);
        HttpHeaders httpHeaders = new HttpHeaders();
        String tokenType = OAuthTokenUtil.getOAuth2AccessToken().getTokenType();
        httpHeaders.set("Authorization",String.format("%s %s", tokenType, OAuthTokenUtil.getOAuth2AccessToken().getValue()));
        httpHeaders.set("Content-Type","application/json;charset=utf-8");
        HttpEntity httpEntity = new HttpEntity(body,httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,httpEntity,String.class);
        return responseEntity.getBody();
    }

}
