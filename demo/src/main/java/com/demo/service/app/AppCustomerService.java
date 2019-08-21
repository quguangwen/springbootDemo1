package com.demo.service.app;

import com.demo.config.DatabaseContextHolder;
import com.demo.entity.AppCustomerVo;
import com.demo.entity.AppLendFinupVo;
import com.demo.mapper.AppCustomerVoMapper;
import com.demo.mapper.AppLendFinupVoMapper;
import com.demo.util.AesUtils;
import com.demo.util.ReadProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class AppCustomerService {

    @Autowired
    AppCustomerVo appCustomerVo;
    @Autowired
    AppCustomerVoMapper appCustomerVOMapper;
    @Autowired
    AppLendFinupVoMapper appLendFinupVoMapper;



    public List getCustInfor(HashMap<String, String> hashMap) {

        List<AppCustomerVo> list = new ArrayList();
        log.info("查询销售绑定进件名单：");
        list = appCustomerVOMapper.getCustInforBySale(hashMap);
        for (int i = 0; i < list.size(); i++) {
            appCustomerVo = list.get(i);
            if (appCustomerVo.getMobile().contains("xy") ||appCustomerVo.getIdNo().equals("")) {
                String encrypt = appCustomerVo.getMobile();
                appCustomerVo.setMobile(AesUtils.decrypt(encrypt));
            }
            if (appCustomerVo.getIdNo().contains("xy") || appCustomerVo.getIdNo().equals("")) {
                String encrypt1 = appCustomerVo.getIdNo();
                appCustomerVo.setIdNo(AesUtils.decrypt(encrypt1));
            }

            String stateKey = appCustomerVo.getStateType();
            if (stateKey != null) {
                appCustomerVo.setStateType(ReadProperties.getStateCNValue(stateKey));
            }
        }
        return list;
    }

    public List getAppFinupResult(String mobile) {
        List<AppLendFinupVo> list = new ArrayList();
        log.info("根据手机号查询进件信息：");
        String encryptMobile = AesUtils.encrypt(mobile);
        list = appLendFinupVoMapper.getLendAppFInfor(encryptMobile);
        return list;
    }

    public List getTest() {
        log.info("查询客户信息表");
        List<HashMap> list = new ArrayList<>();
        list = appCustomerVOMapper.getTestResult();
        return list;
    }

    public List getProduct() {
        log.info("获得产品名称列表");
        List<HashMap> list = new ArrayList<>();
        list = appCustomerVOMapper.getProductList();
        return list;
    }

    public List getAttachmentList(String product_type) {
        log.info("获得产品附件列表");
        List<HashMap> list = new ArrayList<>();
        list = appCustomerVOMapper.getAttachmentList(product_type);
        return list;
    }


}