package com.demo.mapper;

import com.demo.entity.FinupLend;
import org.apache.ibatis.annotations.Param;


public interface LendRequestMapper<T> {

    FinupLend getLendRequest(long appLendRequestID);
    int updateStatus(FinupLend finupLend);
    FinupLend getLendCustomerID(long appLendRequestID);
    int deleteMainCard(long id);
    int deleteViceCard(long id);
    int updateSubW(long lendRequestID);
    int updateSubStatus(FinupLend finupLend);
    Long getMaxLendID(FinupLend finupLend);

}
