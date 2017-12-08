package com.highrock.cart;

import com.highrock.utils.LogProperties;
import com.highrock.utils.ProfileProperties;
import com.highrock.utils.S3Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by user on 2017/10/11.
 */
@Component
@EnableScheduling
public class DeleteCartTask {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(cron="0 0 0 1/1 * ?")//Every Day
    public void deleteCartWithOutUser(){

    }
}
