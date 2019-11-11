package com.zsl.boss.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@ApiModel(description = "道具对象")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Prop implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(name = "id", value = "主键")
    private Integer id;
    @ApiModelProperty(name = "recruiterId", value = "招聘方主键")
    private Integer recruiterId;
    //道具其他属性待定
}
