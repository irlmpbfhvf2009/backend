package com.lwdevelop.backend.vo;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
//@ApiModel
public class MemberLoginVO {

    //@ApiModelProperty(value = "信箱",required = true)
    private String email;

    //@ApiModelProperty(value = "密碼",required = true)
    private String password;

    /*
     
    
    @ApiModelProperty(value = "作業系統", required = false)
	private String platform;

    @ApiModelProperty(value = "裝置版本號", required = false)
	private String version;

    @ApiModelProperty(value = "登入IP", required = false)
	private String IP;
    
    */
}
