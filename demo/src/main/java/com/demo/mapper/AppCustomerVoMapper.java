package com.demo.mapper;

import java.util.HashMap;
import java.util.List;

public interface AppCustomerVoMapper {
    public List getCustomerBySale(String saleno);
    public List getCustInforBySale(HashMap<String, String> hashMap);
    public List<HashMap> getTestResult();
    public List<HashMap> getProductList();
    public List<HashMap> getAttachmentList(String product_type);

}
