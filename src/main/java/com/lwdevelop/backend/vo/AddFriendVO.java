package com.lwdevelop.backend.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class AddFriendVO {
    
    @ApiModelProperty(value = "登入信箱",required = true)
    private String localEmail;

    @ApiModelProperty(value = "輸入的用戶名",required = true)
    private String email;

}
