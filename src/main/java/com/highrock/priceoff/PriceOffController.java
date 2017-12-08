package com.highrock.priceoff;

import com.highrock.priceoff.dao.PriceMapper;
import com.highrock.utils.PriceConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by user on 2017/11/16.
 */
@RestController
@RequestMapping(value="/priceoff")
public class PriceOffController {
    @Autowired
    PriceMapper priceMapper;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value="/", method= RequestMethod.POST)
    public String priceRate(@RequestBody String rat){
        try {
            logger.info("startPriceChange...wiht rate:"+rat+ Calendar.getInstance().getTime().toString());
            Map param = new HashMap<String,Double>();
            Double rate = Double.valueOf(rat);
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
        return "success";
    }


    private double priceRate(Double price,Double rate){
        return price * rate;
    }
}
