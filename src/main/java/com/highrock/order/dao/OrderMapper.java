package com.highrock.order.dao;

import com.highrock.order.bean.Order;
import com.highrock.order.bean.OrderDetail;
import com.highrock.order.bean.OrderExpress;
import com.highrock.order.bean.OrderSub;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    @Select("select * from cp_order WHERE order_no = #{order_no}")
    @Results({
            @Result(id=true,property="id",column="id"),
            @Result(property="order_no",column="order_no"),
            @Result(property="order_time",column="order_time",javaType = Date.class,jdbcType = JdbcType.DATETIMEOFFSET),
            @Result(property="order_price",column="order_price"),
            @Result(property="currency",column="currency"),
            @Result(property="commission",column="commission"),
            @Result(property="sell_tax",column="sell_tax"),
            @Result(property="user_id",column="user_id"),
            @Result(property="shipping_address",column="shipping_address"),
            @Result(property="shipping_city",column="shipping_city"),
            @Result(property="shipping_state_code",column="shipping_state_code"),
            @Result(property="shipping_zip",column="shipping_zip"),
            @Result(property="status",column="status"),
            @Result(property="payment_time",column="payment_time",javaType = Date.class,jdbcType = JdbcType.DATETIMEOFFSET),
            @Result(property="comment",column="comment"),
            @Result(property="user_comment",column="user_comment"),
            @Result(property="store_no",column="store_no")
    })
    Order findOrder(@Param("order_no") String order_no);

    @Select("select * from cp_order_detail WHERE order_no = #{order_no}")
    @Results({
            @Result(id=true,property="id",column="id"),
            @Result(property="order_no",column="order_no"),
            @Result(property="order_sub_no",column="order_sub_no"),
            @Result(property="item_no",column="item_no"),
            @Result(property="option_no",column="option_no"),
            @Result(property="input_type",column="input_type"),
            @Result(property="input_content",column="input_content")
    })
    List<OrderDetail> findOrderDetail(@Param("order_no") String order_no);

    @Select("select * from cp_order_sub WHERE order_no = #{order_no}")
    @Results({
            @Result(id=true,property="id",column="id"),
            @Result(property="order_no",column="order_no"),
            @Result(property="order_sub_no",column="order_sub_no"),
            @Result(property="style_no",column="style_no"),
            @Result(property="num",column="num"),
            @Result(property="style_backend_no",column="style_backend_no"),
            @Result(property="price",column="price"),
            @Result(property="selection_img",column="selection_img"),
            @Result(property="state_tax",column="state_tax"),
            @Result(property="county_tax",column ="county_tax"),
            @Result(property="city_tax",column ="city_tax"),
            @Result(property="special_tax",column ="special_tax")
    })
    List<OrderSub> findOrderSub(@Param("order_no") String order_no);

    @Select("select * from cp_order_express WHERE order_no = #{order_no}")
    @Results({
            @Result(id=true,property="id",column="id"),
            @Result(property="order_no",column="order_no"),
            @Result(property="order_sub_no",column="order_sub_no"),
            @Result(property="num",column="num"),
            @Result(property="express_name",column="express_name"),
            @Result(property="express_tracking_number",column="express_tracking_number"),
            @Result(property="expected_arrival_time",column="expected_arrival_time")
    })
    List<OrderExpress> findOrderExpress(@Param("order_no") String order_no);

    @Insert({"<script>",
            "INSERT INTO `cp_order` (order_no,order_time,order_price,currency,commission,sell_tax,user_id,shipping_address,shipping_city,shipping_state_code,shipping_zip,status,payment_time,comment,user_comment,store_no) VALUES ",
            "(#{order.order_no},#{order.order_time},#{order.order_price},#{order.currency},#{order.commission},#{order.sell_tax},#{order.user_id},#{order.shipping_address},#{order.shipping_city},#{order.shipping_state_code},#{order.shipping_zip},#{order.status},#{order.payment_time},#{order.comment},#{order.user_comment},#{order.store_no});",
            "</script>"})
    int insertOrder(@Param("order") Order order);

    @Insert({"<script>",
            "INSERT INTO `cp_order_sub` (order_no,order_sub_no,style_no,num,style_backend_no,price,selection_img,state_tax,county_tax,city_tax,special_tax) VALUES " ,
            "(#{orderSub.order_no}, #{orderSub.order_sub_no}, #{orderSub.style_no}, #{orderSub.num}, #{orderSub.style_backend_no}, #{orderSub.price},#{orderSub.selection_img},#{orderSub.state_tax},#{orderSub.county_tax},#{orderSub.city_tax},#{orderSub.special_tax});",
            "</script>"})
    int insertOrderSub(@Param("orderSub") OrderSub orderSub);

    @Insert({"<script>",
            "INSERT INTO `cp_order_detail` (order_no,order_sub_no,item_no,option_no,input_type,input_content) VALUES",
             " (#{orderDetail.order_no},#{orderDetail.order_sub_no},#{orderDetail.item_no},#{orderDetail.option_no},#{orderDetail.input_type},#{orderDetail.input_content});",
            "</script>"})
    int insertOrderDetail(@Param("orderDetail") OrderDetail orderDetail);

    @Insert({"<script>",
            "INSERT INTO `cp_order_express` (order_no,order_sub_no,num,express_name,express_tracking_number,expected_arrival_time) VALUES",
            " (#{orderExpress.order_no}, #{orderExpress.order_sub_no}, #{orderExpress.num}, #{orderExpress.express_name}, #{orderExpress.express_tracking_number}, #{orderExpress.expected_arrival_time});",
            "</script>"})
    int insertOrderExpress(@Param("orderExpress") Map<String,Object> orderExpress);


    @Select("SELECT * from cp_user")
    @ResultType(List.class)
    List<Map<String,Object>> findUserList();

    @Update({"UPDATE cp_order SET status=#{map.status}<when test='map.commission!=null'>,commission = #{map.commission}</when>　WHERE order_no=#{map.order_no}"})
    int updateOrderStatusAndCommission(@Param("map") Map<String, Object> map);


    @Update({"<script>",
            "UPDATE cp_order",
            "SET shipping_address=#{order.shipping_address}",
            ",comment = #{order.comment}",
            ",user_comment = #{order.user_comment}",
            "WHERE order_no=#{order.order_no}",
            "</script>"})
    int updateOrderForAddr(@Param("order") Map<String, Object> order);

    @Delete("DELETE from cp_order where order_no=#{order_no}")
    int deleteOrder(@Param("order_no") String order_no);

    @Delete("DELETE from cp_order_sub where order_no=#{order_no}")
    int deleteOrderSub(@Param("order_no") String order_no);

    @Delete("DELETE from cp_order_detail where order_no=#{order_no}")
    int deleteOrderDetail(@Param("order_no") String order_no);

    @Delete("DELETE from cp_order_express where order_no=#{order_no}")
    int deleteOrderExpress(@Param("order_no") String order_no);

    @Select("SELECT * from order where order_no=#{order_no}")
    @ResultType(Map.class)
    Map<String,Object> findOrderMap(String order_no);

    @Update({"<script>",
            "UPDATE cp_order",
            "SET commission = #{map.commission}",
            "WHERE order_no=#{map.order_no}",
            "</script>"})
    int updateOrder(@Param("map") Map map);
}
