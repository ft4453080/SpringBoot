package com.highrock.order.action;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSONObject;
import com.highrock.order.bean.Order;
import com.highrock.order.service.OrderService;
import com.highrock.utils.*;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping(value="/order")
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProfileProperties profileProperties;
    @Autowired
    private BackEndApiProperties backEndApiProperties;

    /**
     * API 2.1 query Order INFO THEN Call API 3.1 to Save Order Info to Backend
     * @author ZHANGJIAN
     * @Time 2017-07-28
     * @param order_no
     * @return result = S or F;errormsg has a value when result is F.
     */
    @RequestMapping(value="/Order_Payed", method= RequestMethod.POST)
    public String getOrder(@RequestBody String order_no) {
       Map<String, Object> result = new HashMap<String, Object>();
        try {
            logger.info("Get Order:"+order_no+" ...");
            order_no = URLDecoder.decode(order_no, "UTF-8");
            Map<String, Object> jsonMap = JSONHelper.convertJsonStrToMap(order_no);
            Map<String, Object> orderInfo = orderService.getOrderInfo(Objects.toString(jsonMap.get("order_no")));
            if(MapUtils.isEmpty(orderInfo)){
                logger.info("Get Order:"+order_no+"Failed.The Order is Missing!");
                result.put("result","F");
                result.put("errormsg","Order is Missing");
                return JSON.toJSONString(result);
            }
            logger.info("success!");
            //CALL API 3.1 72CRAFT BACKEND TO CREATE ORDER
            String s =  JSON.toJSONString(orderInfo);
            logger.info("Send Order:"+order_no+" to BackEnd...");
            String url = profileProperties.getBackEndApiUrl() + backEndApiProperties.getUrl().get("insertOrder");
            logger.info("Calling "+url);
            String backendResult = HttpHelper.sendPOST(url, s,backEndApiProperties.getAuth().get("user"),backEndApiProperties.getAuth().get("password"));
            //CALL API 3.1 72CRAFT BACKEND TO CREATE ORDER END & SUCCESS
            if(Objects.toString(JSON.parseObject(backendResult).get("result")).equals("S")){
                logger.info("success!");
                result.put("result","S");
                result.put("errormsg","");
                return JSON.toJSONString(result);
            }
            else{
                logger.info("failed!");
                result.put("result","F");
                result.put("errormsg","Exception on BackEndAPI!");
                return JSON.toJSONString(result);
            }
        }
        catch(Exception e){
            logger.info("Exception on FrontEnd API!");
            result.put("result","F");
            result.put("errormsg","Exception on API!"+e);
            return JSON.toJSONString(result);
        }
    }

    /**
     * API 2.2 mddify commission & status of Order
     * @author ZHANGJIAN
     * @Time 2017-07-28
     * @param orderInfo
     * @return 1 as success 0 as failed
     */
    @RequestMapping(value="/Order_Status", method= RequestMethod.PUT)
    public String editOrderFromBackEnd(@RequestBody String orderInfo){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            logger.info("Get OrderInfo ...");
            orderInfo = URLDecoder.decode(orderInfo, "UTF-8");
            List<Map<String,Object>> jsonList = JSONHelper.convertJsonStrToList(orderInfo);
            String updateResult = orderService.updateOrders(jsonList);
/*            if(updateResult.equals("S")){
                String usResult = callUsApi(jsonList);
                return usResult;
            }
            else{*/
                resultMap.put("result","S");
                resultMap.put("errormsg",updateResult);
            return JSON.toJSONString(resultMap);
//            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result","F");
            resultMap.put("errormsg",e.getMessage());
            return JSON.toJSONString(resultMap);
        }
    }


    /**
     * API 2.3 Order Modify Address
     * @author ZHANGJIAN
     * @Time 2017-07-28
     * @param cp_order is a String can convert to JSON. Mapping including 'order_no','post_name','post_phone','post_address','comment','user_comment'
     * @return result = S or F;errormsg has a value when result is F.
     */
    @RequestMapping(value="/Order_Addr", method= RequestMethod.PUT)
    public String editOrderFromFrontEnd(@RequestBody  String cp_order){
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            logger.info("Get OrderInfo From Param");
            cp_order = URLDecoder.decode(cp_order, "UTF-8");
            jsonMap = JSONHelper.convertJsonStrToMap(cp_order);
        }catch (Exception e){
            result.put("result","F");
            result.put("errormsg","Param is invalid");
            return JSON.toJSONString(result);
        }
        String order_no=Objects.toString(jsonMap.get("order_no"));
        logger.info("Check order"+order_no+"...");
        Map<String, Object> orderInfo = orderService.getOrderInfo(order_no);
        if(MapUtils.isEmpty(orderInfo)){
            logger.info("Get Order:"+order_no+"Failed.The Order is Missing!");
            result.put("result","F");
            result.put("errormsg","Order is Missing");
            return JSON.toJSONString(result);
        }
        logger.info("Update Order at FrontEnd");

        Integer i= orderService.updateOrderForAddr(jsonMap);
        if (i==1){
            logger.info("Success!");
            logger.info("Call BackEnd API...");
            try {
                String url = profileProperties.getBackEndApiUrl() + backEndApiProperties.getUrl().get("updateOrder");
                logger.info("Calling "+url);
                String backendResult = HttpHelper.sendPUT(url, cp_order,backEndApiProperties.getAuth().get("user"),backEndApiProperties.getAuth().get("password"));
            }
            catch(Exception e){
                logger.info("Failed!");
                result.put("result","S");
                result.put("errormsg","Exception on BackEndAPI!");
                return JSON.toJSONString(result);
            }
            logger.info("Success!");
            result.put("result","S");
            result.put("errormsg","");
            return JSON.toJSONString(result);
        }
        else{
            logger.info("Exception on FrontEnd API!");
            result.put("result","F");
            result.put("errormsg","Update failed");
            return JSON.toJSONString(result);
        }
    }
    @RequestMapping(value="{order_no}", method= RequestMethod.GET)
    public String getOrderInfo(@PathVariable("order_no") String order_no) {
            System.out.println(profileProperties.getEarthlingApiUrl());
           return  profileProperties.getBackEndApiUrl();
    }

    @RequestMapping(value="/Order_Express", method= RequestMethod.POST)
    public String saveOrderExpress(@RequestBody String orderInfo) {
        logger.info("get Order Info:"+orderInfo);
        List<Map<String, Object>> jsonList;

        Map<String, Object> result = new HashMap<String, Object>();
        try {
            jsonList = JSONHelper.convertJsonStrToList(orderInfo);
        }
        catch(Exception e){
            logger.info("Exception on FrontEnd API!");
            result.put("result","F");
            result.put("errormsg","param convert exception");
            return JSON.toJSONString(result);
        }
        try {
            orderService.upsertOrderExpress(jsonList);
            logger.info("Success!");
            List<Map<String,Object>> orderList = setOrderInfo(jsonList);
            logger.info("Call API 1.1...");
            callUsApi(orderList);
            result.put("result","S");
            result.put("errormsg","");
            return JSON.toJSONString(result);
        }
        catch (Exception e){
            logger.info("Exception in updating");
            result.put("result","F");
            result.put("errormsg",e.getMessage());
            return JSON.toJSONString(result);
        }
    }
    private List<Map<String,Object>> setOrderInfo(List<Map<String,Object>> jsonList){
        logger.info("Ready To call API 1.1...");
        Set<String> orderSet =new HashSet<String>();
        for(Map<String,Object> map:jsonList){
            orderSet.add(Objects.toString(map.get("order_no")));
        }
        List<Map<String,Object>> orderList = new ArrayList<Map<String,Object>>();
        for(String order:orderSet){
            Map<String,Object> orderMap = new HashMap<String,Object>();
            orderMap.put("order_no",order);
            orderMap.put("status",Order.ORDER_STATUS_SHIPPING);
            orderList.add(orderMap);
        }
        logger.info("Param ready!");
        return orderList;
    }
    private String callUsApi(List<Map<String,Object>> jsonList){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            for (Map<String, Object> jsonMap : jsonList) {
                String jsonParam = JSON.toJSONString(jsonMap);
                logger.info("Send Order:" + jsonMap.get("order_no") + " to Earthling...");
                //earthlingAPI
                String url = profileProperties.getEarthlingApiUrl().get("root") + profileProperties.getEarthlingApiUrl().get("orderStatus");
                logger.info("PATCHING..." + url);
                String earthlingResult = HttpHelper.sendPATCH(url, jsonParam, profileProperties.getEarthlingAuth().get("user"), profileProperties.getEarthlingAuth().get("password"));
                logger.info("Done:"+earthlingResult);
                JSONObject er = JSON.parseObject(earthlingResult);
                String result = Objects.toString(er.get("result"));
                if (result.equals("S")) {
                    logger.info("success!");
                } else {
                    logger.info("failed!:" + Objects.toString(er.get("errormsg")));
                    //respond to backend
                    logger.info("failed in calling API 1.1 for ORDER:"+jsonMap.get("order_no"));
                    resultMap.put("result","F");
                    resultMap.put("errormsg",er.get("errormsg"));
                    return JSON.toJSONString(resultMap);
                }
            }
            logger.info("Success in call API 1.1");
            resultMap.put("result","S");
            resultMap.put("errormsg","");
            return JSON.toJSONString(resultMap);
        }
        catch (Exception e){
            resultMap.put("result","F");
            resultMap.put("errormsg",e.getMessage());
            return JSON.toJSONString(resultMap);
        }
    }


}
