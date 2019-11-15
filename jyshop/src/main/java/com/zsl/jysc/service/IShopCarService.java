package com.zsl.jysc.service;

import com.zsl.jysc.entity.ShopCar;

import java.util.List;

public interface IShopCarService {

    List<ShopCar> selectShopCarDesc();
    boolean addShopCar(ShopCar shopCar);
    boolean deleteShopCarById(Long shopCarId);
    boolean updateShopCar(ShopCar shopCar);
}
