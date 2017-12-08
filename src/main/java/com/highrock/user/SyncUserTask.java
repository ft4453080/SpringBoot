package com.highrock.user;



import com.alibaba.fastjson.JSON;
import com.highrock.order.dao.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by user on 2017/10/23.
 */

@Component
@EnableScheduling
public class SyncUserTask {
    @Autowired
    OrderMapper orderMapper;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //Scheduled(cron="0 0 1,13 * * ?")//Every Day twice 1:00 13:00
    //@Scheduled(cron="0 0/1 * 1/1 * ?")//Every Min
    public void SyncUser(){
        logger.info("Hello World");
        logger.info(JSON.toJSONString(orderMapper.findUserList()));

    }
}

