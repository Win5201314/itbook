package com.zsl.jysc.mapper;

import com.zsl.jysc.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {

    List<Order> selectOrderByUserIdAndOrderStatus(@Param(value = "userId") long userId, @Param(value = "orderStatus") int orderStatus);
}
