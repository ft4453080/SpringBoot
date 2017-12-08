package com.highrock.cart.dao;

import com.highrock.storage.bean.StorageMaterial;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;


public interface CartMapper {

    int updateStorage(@Param("map") HashMap map);
}
