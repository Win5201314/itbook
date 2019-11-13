package com.zsl.jysc.mapper;

import com.zsl.jysc.entity.ShopCar;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopCarMapper {

    boolean addShopCar(ShopCar shopCar);
    int deleteShopCarById(Long shopCarId);
    int updateShopCar(ShopCar shopCar);
}
