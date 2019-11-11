package com.zsl.boss.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@ApiModel(description = "附件简历")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Accessory implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(name = "id", value = "关键字")
    private Integer id;
    @ApiModelProperty(name = "url", value = "附件简历链接地址")
    private String url;
    @ApiModelProperty(name = "userId", value = "用户主键")
    private Integer userId;
}
