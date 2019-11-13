package com.zsl.jysc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "购物车")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonSerialize(include =  JsonSerialize.Inclusion.NON_NULL)
public class ShopCar implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "id", value = "主键id")
    private Long id;
    @ApiModelProperty(name = "userId", value = "用户主键")
    private Long userId;

    //允许有一定的数据冗余，方便查询
    @ApiModelProperty(name = "productId", value = "商品编号")
    private Long productId;
    @ApiModelProperty(name = "productPrice", value = "商品当前价格")
    private String productPrice;
    @ApiModelProperty(name = "mainImage", value = "产品主图")
    private String mainImage;
    @ApiModelProperty(name = "title", value = "产品标题")
    private String title;
    @ApiModelProperty(name = "productCount", value = "产品数量")
    private Integer productCount;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "joinDate", value = "加入时间")
    private Date joinDate;

    //可能可以冗余
    /*
    @ApiModelProperty(name = "productName", value = "商品名字")
    private String productName;
    @ApiModelProperty(name = "productDescription", value = "商品描述")
    private String productDescription;
    @ApiModelProperty(name = "productType", value = "商品类型")
    private String productType;
    @ApiModelProperty(name = "productProvider", value = "供应商")
    private String productProvider;
    @ApiModelProperty(name = "productWeight", value = "商品重量")
    private String productWeight;*/
}
