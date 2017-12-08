package com.highrock.storage.dao;

import com.highrock.storage.bean.StorageMaterial;
import org.apache.ibatis.annotations.Param;


public interface StorageMapper {

    int updateStorage(@Param("storageMaterial") StorageMaterial  storageMaterial);
}
