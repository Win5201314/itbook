package com.zsl.jysc.mapper;

import com.zsl.jysc.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<Product> selectProductByStatus();
    List<Product> selectProductByStatusEq2();
}
