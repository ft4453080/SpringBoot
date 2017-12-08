package com.highrock.utils.entity;

import com.highrock.order.dao.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by user on 2017/8/11.
 */
@Component
public class DictMapHelper {
    @Autowired
    private OrderMapper orderMapper;



}
