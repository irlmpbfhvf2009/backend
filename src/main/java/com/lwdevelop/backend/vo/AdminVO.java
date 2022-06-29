package com.lwdevelop.backend.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ApiModel
@ToString
public class AdminVO {
    @ApiModelProperty(value = "用戶名", required = true)
	private String username;
    @ApiModelProperty(value = "信箱", required = true)
	private String email;
    @ApiModelProperty(value = "密碼", required = true)
	private String password;
    
}
