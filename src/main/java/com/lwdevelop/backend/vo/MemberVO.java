package com.lwdevelop.backend.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ApiModel
@ToString
public class MemberVO {
    @ApiModelProperty(value = "編號", required = true)
    private Integer id;
    @ApiModelProperty(value = "用戶名", required = true)
    private String username;
    @ApiModelProperty(value = "信箱", required = true)
    private String email;
    @ApiModelProperty(value = "密碼", required = true)
    private String password;
    @ApiModelProperty(value = "性別", required = true)
    private String gender;
    @ApiModelProperty(value = "年齡", required = true)
    private Integer age;
    @ApiModelProperty(value = "星座", required = true)
    private String constellation;
    @ApiModelProperty(value = "令牌", required = true)
    private String token;
    @ApiModelProperty(value = "登入信箱", required = true)
    private String loginEmail;

    @ApiModelProperty(value = "身高", required = true)
    private Integer height;
    @ApiModelProperty(value = "體重", required = true)
    private Integer weight;
    @ApiModelProperty(value = "體型", required = true)
    private String bodyType;
    @ApiModelProperty(value = "居住地區", required = true)
    private String liveArea; 
    @ApiModelProperty(value = "自我介紹", required = true)
    private String selfIntroduction; 
    @ApiModelProperty(value = "理想型", required = true)
    private String myType; 
    @ApiModelProperty(value = "職業類別", required = true)
    private String profession; 
    @ApiModelProperty(value = "學歷", required = true)
    private String education;
    @ApiModelProperty(value = "語言", required = true)
    private String language;
    @ApiModelProperty(value = "是否有抽菸", required = true)
    private String smokes;
    @ApiModelProperty(value = "是否有飲酒習慣", required = true)
    private String drinking;

}
