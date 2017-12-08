package com.highrock.user;

import com.alibaba.fastjson.JSON;
import com.highrock.order.dao.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
/**
 * Created by user on 2017/10/10.
 */
@RestController
@RequestMapping(value="/users")
public class UserController {
    @Autowired
    OrderMapper orderMapper;
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String getUserList() {
        return JSON.toJSONString(orderMapper.findUserList());
    }

}
