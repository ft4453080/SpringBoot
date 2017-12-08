package com.highrock.order.bean;

/**
 * Created by user on 2017/7/17.
 */
public class OrderSub {
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

    public String getStyle_no() {
        return style_no;
    }

    public void setStyle_no(String style_no) {
        this.style_no = style_no;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getStyle_backend_no() {
        return style_backend_no;
    }

    public void setStyle_backend_no(String style_backend_no) {
        this.style_backend_no = style_backend_no;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSelection_img() {
        return selection_img;
    }

    public void setSelection_img(String selection_img) {
        this.selection_img = selection_img;
    }


    private Integer id;
    private String order_no;
    private String order_sub_no;
    private String style_no;
    private Integer num;
    private String style_backend_no;
    private Double price;
    private String selection_img;
    private Double state_tax;
    private Double county_tax;
    private Double city_tax;
    private Double special_tax;

    public Double getState_tax() {
        return state_tax;
    }

    public void setState_tax(Double state_tax) {
        this.state_tax = state_tax;
    }

    public Double getCounty_tax() {
        return county_tax;
    }

    public void setCounty_tax(Double county_tax) {
        this.county_tax = county_tax;
    }

    public Double getCity_tax() {
        return city_tax;
    }

    public void setCity_tax(Double city_tax) {
        this.city_tax = city_tax;
    }

    public Double getSpecial_tax() {
        return special_tax;
    }

    public void setSpecial_tax(Double special_tax) {
        this.special_tax = special_tax;
    }


}
