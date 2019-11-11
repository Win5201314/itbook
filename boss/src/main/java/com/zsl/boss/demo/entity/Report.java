package com.zsl.boss.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@ApiModel(description = "举报对象")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(name = "id", value = "关键字")
    private Integer id;
    private Integer type;
    private String content;
    private String imageUrl;
}
