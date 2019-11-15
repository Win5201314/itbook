package com.zsl.jysc.mapper;

import com.zsl.jysc.entity.ShopCar;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopCarMapper {

    List<ShopCar> selectShopCarDesc();
    boolean addShopCar(ShopCar shopCar);
    int deleteShopCarById(Long shopCarId);
    int updateShopCar(ShopCar shopCar);
}
