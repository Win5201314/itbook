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

@ApiModel(description = "商品")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonSerialize(include =  JsonSerialize.Inclusion.NON_NULL)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "id", value = "商品编号，主键id")
    private Long id;
    @ApiModelProperty(name = "productName", value = "商品名字")
    private String productName;
    @ApiModelProperty(name = "productDescription", value = "商品描述")
    private String productDescription;
    @ApiModelProperty(name = "productType", value = "商品类型")
    private String productType;
    @ApiModelProperty(name = "productProvider", value = "供应商")
    private String productProvider;
    @ApiModelProperty(name = "productWeight", value = "商品重量")
    private String productWeight;
    @ApiModelProperty(name = "productPrice", value = "商品当前价格")
    private String productPrice;
    @ApiModelProperty(name = "beforePrice", value = "之前的价格")
    private String beforePrice;

    @ApiModelProperty(name = "mainImage", value = "产品主图")
    private String mainImage;
    @ApiModelProperty(name = "fourImages", value = "轮播图，4张")
    private String fourImages;
    @ApiModelProperty(name = "title", value = "产品标题")
    private String title;
    @ApiModelProperty(name = "detailImages", value = "商品详细图片,图片url用英文逗号隔开")
    private String detailImages;

    @ApiModelProperty(name = "status", value = "精品首发=0, 剁手灵感=1, LINE FRIENDS=2")
    private Integer status;

    @JsonIgnore
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(name = "create_time", value = "商品上架时间")
    private Date create_time;

    @JsonIgnore
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(name = "update_time", value = "商品更新时间")
    private Date update_time;
}
