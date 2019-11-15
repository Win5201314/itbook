package com.zsl.jysc.service.impl;

import com.zsl.jysc.entity.Product;
import com.zsl.jysc.service.IProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {
    @Override
    public boolean addNewProduct(Product product) {
        return false;
    }
}
