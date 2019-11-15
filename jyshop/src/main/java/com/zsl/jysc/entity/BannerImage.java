package com.zsl.jysc.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@ApiModel(description = "轮播图")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonSerialize(include =  JsonSerialize.Inclusion.NON_NULL)
public class BannerImage implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "id", value = "主键id")
    private int id;

    @ApiModelProperty(name = "images", value = "三张轮播图")
    private String images;
}
