package com.zsl.jysc.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@ApiModel(description = "邮寄地址")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonSerialize(include =  JsonSerialize.Inclusion.NON_NULL)
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "id", value = "主键id")
    private Integer id;
    

}
