package com.demo.mapper;

import com.demo.entity.AppLendInfor;
import com.demo.entity.BChanelBean;



public interface AppLendInforMapper {

    public AppLendInfor getAppRequestID(String mobile);
    public int updateAppStateByID(AppLendInfor appLendInfor);
    public AppLendInfor getProductType(long appRequestID);
    public AppLendInfor getMagicDataID(long appRequestID);
    public long searchDupOrderNo(long order_no);
    public long searchDupID(String id_no);
    public long deleteDupID(String id_no);
    public int insertBCName(BChanelBean bChanelBean);

}
