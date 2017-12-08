package com.highrock.storage.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.highrock.storage.bean.StorageMaterial;
import com.highrock.storage.service.StorageService;
import com.highrock.utils.HttpHelper;
import com.highrock.utils.JSONHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping(value="/storage")
public class StorageController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    StorageService storageService;

    @RequestMapping(value="/storage_update", method= RequestMethod.PUT)
    public String editOrderFromBackEnd(@RequestBody String storageInfo){
        try{
            logger.info("get storageInfo...");
            storageInfo = URLDecoder.decode(storageInfo, "UTF-8");
            logger.info(storageInfo);
            List<StorageMaterial> storageInfoList = JSONArray.parseArray(storageInfo,StorageMaterial.class);
            logger.info("success!");
            int i = storageService.updateStorage(storageInfoList);
            if(i==1) {
                return "s";
            }
            return "f:Update failed!";
        } catch (UnsupportedEncodingException e) {
            logger.info("failed!");
            e.printStackTrace();
            return "f:Error Param Format!";
        }

    }
}
