package com.demo.service.finup;


import com.demo.entity.AppLendInfor;
import com.demo.entity.FinupLend;
import com.demo.mapper.LendRequestMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LendRequestService {

    @Autowired
    LendRequestMapper lendRequestMapper;
    @Autowired
    FinupLend finupLend;
    @Autowired
    AppLendInfor appLendInfor;



    public void updateLendRequestSub(long appLendRequestID, String subStatus){
        FinupLend finupLend = (FinupLend) lendRequestMapper.getLendRequest(appLendRequestID);
        long lendRequestID = finupLend.getId();
        log.info("更新进件子状态: " + subStatus);
        if(subStatus.equals("WAIT_APP_CONFIRM")){
            int a = lendRequestMapper.updateSubW(lendRequestID);
        } else if(subStatus.equals("WAIT_FIRST_SUPPLY")){
            int a = lendRequestMapper.updateSubStatus(subStatus, lendRequestID);
        } else if(subStatus.equals("WAIT_SECOND_SUPPLY")){
            int a = lendRequestMapper.updateSubStatus(subStatus, lendRequestID);
        } else if(subStatus.equals("SECOND_SUPPLY_MATERIAL")){
            int a = lendRequestMapper.updateSubStatus(subStatus, lendRequestID);
        }
    }

    public FinupLend getLendRequest(long appLendRequestID){
        //DatabaseContextHolder.setDatabaseType(DatabaseType.finup_lend);
        log.info("获取个贷系统进件号：");
        FinupLend finupLend = (FinupLend) lendRequestMapper.getLendRequest(appLendRequestID);

        return finupLend;
    }

    public int updateFinupState(long appLendRequestID, String status){
        finupLend.setAppLendRequestID(appLendRequestID);
        finupLend.setStatus(status);
        int a = lendRequestMapper.updateStatus(finupLend);
        return a;
    }

    public int deleteMCard(long appLendRequestID){
        finupLend = lendRequestMapper.getLendCustomerID(appLendRequestID);
        long id = finupLend.getLend_customer_id();
        log.info("删除主卡");
        int a = lendRequestMapper.deleteMainCard(id);
        return a;
    }

    public int deleteVCard(long appLendRequestID){
        finupLend = lendRequestMapper.getLendCustomerID(appLendRequestID);
        long id = finupLend.getLend_customer_id();
        System.out.println(id);
        log.info("删除副卡");
        int a = lendRequestMapper.deleteViceCard(id);
        System.out.println(a);
        return a;
    }



    public void updateFinup(long appLendRequestID, String status){

        switch(status){
            case "APPROVED":
                log.info("更新个贷系统进件为等待合同");
                updateFinupState(appLendRequestID,"WAIT_CONTRACT");
                break;
            case "LEND_REJECTED":
                log.info("更新个贷系统进件个贷拒贷");
                updateFinupState(appLendRequestID,"LEND_REJECTED");
                break;
            case "CANCEL_AUDIT":
                log.info("更新个贷系统进件为客户放弃");
                updateFinupState(appLendRequestID,"CANCEL_AUDIT");
                break;
            case "CANCEL_SIGN":
                log.info("更新个贷系统进件为放弃签约");
                updateFinupState(appLendRequestID,"CANCEL_SIGN");
                break;
            case "MAIN_CARD_AUTHENTICATION":
                log.info("更改个贷系统进件为主卡鉴权");
                deleteMCard(appLendRequestID);
                updateFinupState(appLendRequestID,"WAIT_CONTRACT");
                //deleteVCard(appLendRequestID);
                break;
            case "VICE_CARD_AUTHENTICATION":
                log.info("更改个贷系统进件为副卡鉴权");
                deleteVCard(appLendRequestID);
                updateFinupState(appLendRequestID,"WAIT_CONTRACT");
                break;
            case "WAIT_CONTRACT":
                log.info("更改个贷系统进件为等待电子签章");
                updateFinupState(appLendRequestID,"WAIT_FOR_ELECTRONIC_SIGNATURE");
                break;
            case "CONFIRM_CONTRACT":
                log.info("更改个贷系统为确认合同");
                updateFinupState(appLendRequestID,"CALM_PERIOD");
                break;
            case "CONFIRM_CONTRACT_SUCCESS":
                log.info("更改个贷系统为确认合同成功");
                updateFinupState(appLendRequestID,"WAIT_SIGN");
                break;
            case "PROCESSING":
                log.info("更改个贷系统为放款成功");
                updateFinupState(appLendRequestID,"PROCESSING");
                break;
            case "AUDIT_CREDIT":
                log.info("更改个贷系统为等待处理");
                updateFinupState(appLendRequestID,"PENDING");
                break;
            case "SIGN_AUDITING":
                log.info("更改个贷系统为签约审核中");
                updateFinupState(appLendRequestID,"SIGN_AUDITING");
                break;
            case " ":
                log.info("更改个贷系统为等待付款");
                updateFinupState(appLendRequestID,"WAIT_PAY");
                break;
            case "AUDIT_VIDEO":
                log.info("更改个贷系统为视频审核中");
                updateFinupState(appLendRequestID,"PENDING");
                break;
            case "AUDIT_VIDEO_INTERRUPT":
                log.info("更改个贷系统为视频中断");
                updateFinupState(appLendRequestID,"PENDING");
                break;
            case "AUDIT_VIDEO_CONFIRM":
                log.info("更改个贷系统为视频完成确认");
                updateFinupState(appLendRequestID,"PENDING");
                break;
            case "QUALIFICATION_DISCREPANCY":
                log.info("更改个贷系统为等待付款");
                updateFinupState(appLendRequestID,"QUALIFICATION_DISCREPANCY");
                break;
            case "APPROVED_FALLBACK":
                log.info("更改个贷系统为个贷车贷批核兜底");
                updateFinupState(appLendRequestID,"WAIT_APPEND_INFORMATION");
                break;
            case "REJECTED_FALLBACK":
                log.info("更改个贷系统为车贷兜底个贷拒贷");
                updateFinupState(appLendRequestID,"WAIT_APPEND_INFORMATION");
                break;
            case "APPEND_INFORMATION":
                log.info("更改个贷系统为等待补充材料");
                updateFinupState(appLendRequestID,"WAIT_APPEND_INFORMATION");
                break;
            case "APPROVED_CAR_RETURN":
                log.info("更改个贷系统为补充车辆信息材料不符");
                updateFinupState(appLendRequestID,"WAIT_APPEND_INFORMATION");
                break;
            case "REJECTED_CAR_RETURN":
                log.info("更改个贷系统为补充车辆信息材料不符");
                updateFinupState(appLendRequestID,"WAIT_APPEND_INFORMATION");
                break;
            case "APPROVED_CAR_CHANGE":
                log.info("更改个贷系统为更改车辆信息");
                updateFinupState(appLendRequestID,"APPEND_INFORMATION");
                break;
            case "REJECTED_CAR_CHANGE":
                log.info("更新车辆信息");
                updateFinupState(appLendRequestID,"APPEND_INFORMATION");
                break;
            case "WAIT_CONFIRM_PRODUCT":
                log.info("更改个贷系统为等待确认产品");
                updateFinupState(appLendRequestID,"WAIT_CONFIRM_PRODUCT");
                break;
            case "CUSTOMER_RECORD":
                log.info("更改个贷系统为客户录件");
                updateFinupState(appLendRequestID,"APP_REJECT");
                break;
            case "SALE_EXAMINE":
                log.info("更改个贷系统为门店质检");
                updateFinupState(appLendRequestID,"APP_REJECT");
                break;
            case "AUDIT_STORE":
                log.info("更改个贷系统为门店审核中");
                updateFinupState(appLendRequestID,"PENDING");
                break;
            case "APPROVED_PB_B":
                log.info("更改个贷系统为PB产品批核");
                updateFinupState(appLendRequestID,"PENDING");
                log.info("更改付状态为等待APP确认");
                updateLendRequestSub(appLendRequestID, "WAIT_APP_CONFIRM");
                break;
            case "APPROVED_PB":
                log.info("更改个贷系统为PB产品批核");
                updateFinupState(appLendRequestID,"PENDING");
                log.info("更改付状态为等待APP确认");
                updateLendRequestSub(appLendRequestID, "WAIT_APP_CONFIRM");
                break;
            case "PB_SECOND_OTHER":
                log.info("等待极速贷第一次补充材料");
                updateFinupState(finupLend.getAppLendRequestID(),"PENDING");
                log.info("更改子状态为等待补充材料");
                updateLendRequestSub(appLendRequestID,"WAIT_APP_CONFIRM");
                break;
            case "FIRST_SUPPLY_MATERIAL":
                log.info("极速贷第一次补充材料");
                updateFinupState(appLendRequestID,"PENDING");
                log.info("更改子状态为等待补充材料");
                updateLendRequestSub(appLendRequestID,"WAIT_FIRST_SUPPLY");
                break;
            case "PB_SECOND_SUPPLY_OPTION":
                log.info("极速贷等待第二次补充材料");
                updateFinupState(appLendRequestID,"PENDING");
                log.info("更改子状态为等待第二次补充材料");
                updateLendRequestSub(appLendRequestID,"WAIT_APP_CONFIRM");
                break;
            case "SECOND_SUPPLY_MATERIAL":
                log.info("极速贷第二次补充材料");
                updateFinupState(appLendRequestID,"PENDING");
                log.info("更改子状态为第二次补充材料");
                updateLendRequestSub(appLendRequestID,"WAIT_SECOND_SUPPLY");
                break;
            case "LIVE_DETECTION":
                log.info("更改个贷系统为活体检测");
                updateFinupState(appLendRequestID,"APP_REJECT");
                break;
            case "PASS_FAILED":
                log.info("更改个贷系统为放款失败");
                updateFinupState(appLendRequestID,"PASS_FAILED");
                break;
            case "PROCESSING_ERROR":
                log.info("更改个贷系统为放款异常");
                updateFinupState(appLendRequestID, "PROCESSING_ERROR");
                break;
            case "REPAID":
                log.info("更改个贷系统为已结清");
                updateFinupState(appLendRequestID,"REPAID");
                break;
            case "WAIT_APPEND_INFORMATION":
                log.info("更改个贷系统为补充材料");
                updateFinupState(appLendRequestID,"WAIT_APPEND_INFORMATION");
                break;

        }

    }

}
