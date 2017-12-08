package com.highrock.order.bean;

/**
 * Created by user on 2017/7/17.
 */
public class OrderDetail {
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

    public String getItem_no() {
        return item_no;
    }

    public void setItem_no(String item_no) {
        this.item_no = item_no;
    }

    public String getOption_no() {
        return option_no;
    }

    public void setOption_no(String option_no) {
        this.option_no = option_no;
    }
    public Integer getInput_type() {
        return input_type;
    }

    public void setInput_type(Integer input_type) {
        this.input_type = input_type;
    }

    public String getInput_content() {
        return input_content;
    }

    public void setInput_content(String input_content) {
        this.input_content = input_content;
    }
     private Integer id;
     private String order_no;
     private String order_sub_no;
     private String item_no;
     private String option_no;
     private Integer input_type;
     private String input_content;

}
