package com.highrock.utils.entity;

import com.highrock.order.dao.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by user on 2017/8/7.
 */
@Component
public class ResultHelper {
    public static String RESULT_SUCCESS="S";
    public static String RESULT_FAIL="F";
    private String order_no;
    private String result ;
    private String errorMsg ;
    private String commission ;



    public ResultHelper() {
    }

    public ResultHelper(String result, String errorMsg) {
        this.result = result;
        this.errorMsg = errorMsg;
    }

    public ResultHelper(String result, String errorMsg, String commission) {
        this.result = result;
        this.errorMsg = errorMsg;
        this.commission = commission;
    }

    public ResultHelper(String order_no , String result, String errorMsg, String commission) {
        this.order_no = order_no;
        this.result = result;
        this.errorMsg = errorMsg;
        this.commission = commission;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }
}
