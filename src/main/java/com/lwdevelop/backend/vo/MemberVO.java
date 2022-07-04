package com.lwdevelop.backend.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ApiModel
@ToString
public class MemberVO {
    @ApiModelProperty(value = "用戶名", required = true)
	private String username;
    @ApiModelProperty(value = "信箱", required = true)
	private String email;
    @ApiModelProperty(value = "密碼", required = true)
	private String password;
    @ApiModelProperty(value = "令牌", required = true)
    private String token;
    @ApiModelProperty(value = "登入信箱",required = true)
    private String loginEmail;
}
