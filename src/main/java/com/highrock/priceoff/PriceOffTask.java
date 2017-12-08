package com.highrock.priceoff;



import com.alibaba.fastjson.JSON;
import com.highrock.order.dao.OrderMapper;
import com.highrock.priceoff.dao.PriceMapper;
import com.highrock.utils.PriceConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by user on 2017/10/23.
 */

@Component
@EnableScheduling
public class PriceOffTask {
    @Autowired
    PriceMapper priceMapper;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Scheduled(cron="0 0 18 19 * ? ")//18:00 FROM 14 TO 19;
    //@Scheduled(cron="0 0/15 * 1/1 * ?")//Every Min
    public void priceRate5(){
        priceRate(PriceConst.RATE_5);
    }
    //@Scheduled(cron="0 0 0 14,15,16,17,18,19 * ? ")//00:00 FROM 14 TO 19;
    //@Scheduled(cron="0 59 * 1/1 * ?")//Every Min
    public void priceRate9(){
        priceRate(PriceConst.RATE_9);
    }
    @Scheduled(cron="0 0 9 20 * ? ")//09:00 Day20;
    //@Scheduled(cron="0 10/15 * 1/1 * ?")//Every Min
    public void priceRate10(){
        priceRate(1.0);
    }

    private void priceRate(Double rate){
        try {
        logger.info("startPriceChange...wiht rate:"+rate+ Calendar.getInstance().getTime().toString());
        Map param = new HashMap<String,Double>();
        param.put("basicPrice",priceRate(PriceConst.BASIC_PRICE,rate));
        param.put("goosePrice",priceRate(PriceConst.GOOSE_PRICE,rate));
        param.put("hoodePrice",priceRate(PriceConst.HOOD_PRICE,rate));
        param.put("chestPocketPrice",priceRate(PriceConst.CHEST_POCKET_PRICE,rate));
        param.put("chestLogoPrice",priceRate(PriceConst.CHEST_LOGO_PRICE,rate));
        param.put("cuffLogoPrice",priceRate(PriceConst.CUFF_LOGO_PRICE,rate));
        for(Object s:param.keySet()){
            logger.info(Objects.toString(s)+"======"+param.get(Objects.toString(s)));
        }
            int i = priceMapper.priceOff(param);
            logger.info("success");
        }
        catch (Exception e){
            logger.info(e.getMessage());
        }
    }


    private double priceRate(Double price,Double rate){
        return price * rate;
    }
}

