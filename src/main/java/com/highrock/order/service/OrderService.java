package com.highrock.order.service;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 2017/7/26.
 */
public interface OrderService {
    public int updateOrder(Map<String,Object> orderMap);
    public Map<String,Object> getOrderInfo(String order_no);
    public int updateOrderForAddr(Map<String,Object> orderMap);
    String updateOrders(List<Map<String, Object>> jsonList) throws Exception;
    void upsertOrderExpress(List<Map<String, Object>> jsonList) throws Exception;;
}
