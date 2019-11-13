package com.zsl.jysc.service;

import com.zsl.jysc.entity.ShopCar;

public interface IShopCarService {

    boolean addShopCar(ShopCar shopCar);
    boolean deleteShopCarById(Long shopCarId);
    boolean updateShopCar(ShopCar shopCar);
}
