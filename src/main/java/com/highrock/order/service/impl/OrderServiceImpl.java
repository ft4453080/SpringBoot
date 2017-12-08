package com.highrock.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.highrock.order.bean.Order;
import com.highrock.order.bean.OrderDetail;
import com.highrock.order.bean.OrderExpress;
import com.highrock.order.bean.OrderSub;
import com.highrock.order.dao.OrderMapper;
import com.highrock.order.service.OrderService;
import com.highrock.utils.HttpHelper;
import com.highrock.utils.ProfileProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLDecoder;
import java.util.*;

/**
 * Created by user on 2017/7/26.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProfileProperties profile;

    @Autowired
    OrderMapper orderMapper;

    @Override
    public int updateOrder(Map<String, Object> orderMap) {
        return orderMapper.updateOrder(orderMap);
    }

    @Override
    public Map<String, Object> getOrderInfo(String order_no) {
        Map<String, Object> orderInfo = new HashMap<String, Object>();
        Order order = orderMapper.findOrder(order_no);
        if(order==null){
            return orderInfo;
        }
        List<OrderSub> orderSubList = orderMapper.findOrderSub(order_no);
        List<OrderDetail> orderDetailList = orderMapper.findOrderDetail(order_no);
        List<OrderExpress> orderExpressList = orderMapper.findOrderExpress(order_no);
        orderInfo.put("order", order);
        orderInfo.put("orderSub", orderSubList);
        orderInfo.put("orderDetail", orderDetailList);
        orderInfo.put("orderExpress", orderExpressList);
        return orderInfo;
    }

    @Override
    public String updateOrders(List<Map<String, Object>> jsonList) {
        try {
            logger.info("Success!");
            logger.info("Update OrderInfo...");
            boolean callFlag = true;
            for (Map<String,Object> jsonMap:  jsonList) {
                logger.info("Update"+Objects.toString(jsonMap.get("order_no")));
                Integer i = updateOrder(jsonMap);
                if(i.equals(1)) {
                    logger.info("Success!");
                }
            }
            return "S";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }
    @Override
    public void upsertOrderExpress(List<Map<String, Object>> jsonList) throws Exception{
        Set<String> orderSet = new HashSet<String>();
        logger.info("Delete orgin Express info...");
        for(Map<String, Object> map:jsonList){
            String order_no = Objects.toString(map.get("order_no"));
            if(orderSet.contains(order_no)){
                continue;
            }
            else{
                logger.info("Deleting"+map.get("order_no")+"...");
                orderMapper.deleteOrderExpress(order_no);
                orderSet.add(order_no);
                logger.info("Done!");
            }
        }
        logger.info("Insert Express info ....");
        for(Map<String, Object> map:jsonList){
            orderMapper.insertOrderExpress(map);
        }
        logger.info("Done!");
    }
    @Override
    public int updateOrderForAddr(Map<String, Object> orderMap) {
        return orderMapper.updateOrderForAddr(orderMap);
    }

}
