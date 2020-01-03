package com.zsl.jysc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "订单")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonSerialize(include =  JsonSerialize.Inclusion.NON_NULL)
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "id", value = "订单编号，主键id(时间戳 + UUID)")
    private Long id;
    //订单用户信息
    @ApiModelProperty(name = "userId", value = "用户主键id")
    private Long userId;
    @ApiModelProperty(name = "username", value = "用户名")
    private String username;
    //地址信息
    @ApiModelProperty(name = "addressId", value = "地址信息主键")
    private Long addressId;
    @ApiModelProperty(name = "address", value = "详细地址")
    private String address;
    @ApiModelProperty(name = "phoneNumber", value = "手机号")
    private String phoneNumber;
    //商品信息
    @ApiModelProperty(name = "productId", value = "商品主键")
    private Long productId;
    @ApiModelProperty(name = "productName", value = "商品名字")
    private String productName;
    @ApiModelProperty(name = "productCount", value = "商品购买数量")
    private Integer productCount;
    @ApiModelProperty(name = "productPrice", value = "商品价格")
    private Double productPrice;
    //订单状态信息
    @ApiModelProperty(name = "orderStatus", value = "订单状态")
    private Integer orderStatus;
    //支付时候需要用
    @ApiModelProperty(name = "payStatus", value = "订单支付状态")
    private Integer payStatus;

    @JsonIgnore
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(name = "create_time", value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date create_time;

    @JsonIgnore
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(name = "update_time", value = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date update_time;
}
