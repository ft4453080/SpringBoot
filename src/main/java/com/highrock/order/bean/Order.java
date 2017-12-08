package com.highrock.order.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by user on 2017/7/17.
 */
public class Order {
    public static String ORDER_STATUS_SHIPPING = "600";
    private Integer id;
    private String order_no;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date order_time;
    private Double order_price;
    private String currency;
    private Double commission;
    private Double sell_tax;
    private Integer user_id;
    private String shipping_address;
    private String shipping_city;
    private String shipping_state_code;
    private String shipping_zip;
    private String status;
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date payment_time;
    private String comment;
    private String user_comment;
    private String store_no;


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

    public Date getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Date order_time) {
        this.order_time = order_time;
    }

    public Double getOrder_price() {
        return order_price;
    }

    public void setOrder_price(Double order_price) {
        this.order_price = order_price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Double getSell_tax() {
        return sell_tax;
    }

    public void setSell_tax(Double sell_tax) {
        this.sell_tax = sell_tax;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }

    public String getShipping_city() {
        return shipping_city;
    }

    public void setShipping_city(String shipping_city) {
        this.shipping_city = shipping_city;
    }

    public String getShipping_state_code() {
        return shipping_state_code;
    }

    public void setShipping_state_code(String shipping_state_code) {
        this.shipping_state_code = shipping_state_code;
    }

    public String getShipping_zip() {
        return shipping_zip;
    }

    public void setShipping_zip(String shipping_zip) {
        this.shipping_zip = shipping_zip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(Date payment_time) {
        this.payment_time = payment_time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUser_comment() {
        return user_comment;
    }

    public void setUser_comment(String user_comment) {
        this.user_comment = user_comment;
    }

    public String getStore_no() {
        return store_no;
    }

    public void setStore_no(String store_no) {
        this.store_no = store_no;
    }
}
