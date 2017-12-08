package com.highrock.priceoff.dao;

import com.highrock.storage.bean.StorageMaterial;

import com.sun.javafx.collections.MappingChange;
import org.apache.ibatis.annotations.Param;
import java.util.Map;


public interface PriceMapper {

    int priceOff(@Param("map") Map map);
}
