package com.zsl.jysc.service.impl;

import com.zsl.jysc.entity.Product;
import com.zsl.jysc.mapper.BannerImageMapper;
import com.zsl.jysc.mapper.ProductMapper;
import com.zsl.jysc.service.IMainPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainPageServiceImpl implements IMainPageService {

    @Autowired
    private BannerImageMapper bannerImageMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public String selectBannerImages() {
        return bannerImageMapper.selectBannerImages();
    }

    @Override
    public List<Product> selectProductByStatus() {
        return productMapper.selectProductByStatus();
    }

    @Override
    public List<Product> selectProductByStatusEq2() {
        return productMapper.selectProductByStatusEq2();
    }
}
