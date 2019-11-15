package com.zsl.jysc.service;

import com.zsl.jysc.entity.Product;

import java.util.List;

public interface IMainPageService {

    String selectBannerImages();

    List<Product> selectProductByStatus();
    List<Product> selectProductByStatusEq2();
}
