package com.lwdevelop.backend.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ApiModel
@ToString
public class EmailVO {
    @ApiModelProperty(value = "信箱", required = true)
	private String email;
}
