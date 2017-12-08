package com.highrock.order.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by user on 2017/7/17.
 */
public class OrderExpress {


    private Integer id;
    private String order_no;
    private String order_sub_no;
    private Integer num;
    private String express_name;
    private String express_tracking_number;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private String expected_arrival_time;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getOrder_sub_no() {
        return order_sub_no;
    }

    public void setOrder_sub_no(String order_sub_no) {
        this.order_sub_no = order_sub_no;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getExpress_name() {
        return express_name;
    }

    public void setExpress_name(String express_name) {
        this.express_name = express_name;
    }

    public String getExpress_tracking_number() {
        return express_tracking_number;
    }

    public void setExpress_tracking_number(String express_tracking_number) {
        this.express_tracking_number = express_tracking_number;
    }

    public String getExpected_arrival_time() {
        return expected_arrival_time;
    }

    public void setExpected_arrival_time(String expected_arrival_time) {
        this.expected_arrival_time = expected_arrival_time;
    }
}
