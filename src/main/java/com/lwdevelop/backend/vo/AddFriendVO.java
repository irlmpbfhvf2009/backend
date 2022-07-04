package com.lwdevelop.backend.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class AddFriendVO {
    
    @ApiModelProperty(value = "登入信箱",required = true)
    private String email;

    @ApiModelProperty(value = "好友用戶名",required = true)
    private String friendUsername;

}
