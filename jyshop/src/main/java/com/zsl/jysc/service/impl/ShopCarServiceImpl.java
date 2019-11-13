package com.zsl.jysc.service.impl;

import com.zsl.jysc.entity.ShopCar;
import com.zsl.jysc.mapper.ShopCarMapper;
import com.zsl.jysc.service.IShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopCarServiceImpl implements IShopCarService {

    @Autowired
    private ShopCarMapper shopCarMapper;

    @Override
    public boolean addShopCar(ShopCar shopCar) {
        return shopCarMapper.addShopCar(shopCar);
    }

    @Override
    public boolean deleteShopCarById(Long shopCarId) {
        return shopCarMapper.deleteShopCarById(shopCarId) >= 1;
    }

    @Override
    public boolean updateShopCar(ShopCar shopCar) {
        return shopCarMapper.updateShopCar(shopCar) >= 1;
    }
}
