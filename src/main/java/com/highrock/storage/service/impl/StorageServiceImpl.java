package com.highrock.storage.service.impl;

import com.highrock.storage.bean.StorageMaterial;
import com.highrock.storage.dao.StorageMapper;
import com.highrock.storage.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StorageServiceImpl implements StorageService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    StorageMapper storageMapper;

    @Override
    public int updateStorage(List<StorageMaterial> storageMaterialList) {
        int result = 1;
        for(StorageMaterial sm:storageMaterialList){
            logger.info("ready to update storage of :"+sm.getMaterial_code());
            result = storageMapper.updateStorage(sm);
            if(result==1){
                logger.info("success!");
            }
            else{
                logger.info("failed!");
            }
        }
        return result;
    }
}
