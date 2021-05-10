package com.nowbio.database.efn.modules.ums.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @author yun
 * @since 2021/5/7 13:55
 */

@Setter
@Getter
public class AdminRegisterParam {

    @NotEmpty(message="姓名不能为空")
    private String username;

    @NotEmpty(message="密码不能为空")
    private String password;

    @ApiModelProperty(value = "头像")
    private String icon;

    @Email(message="邮箱格式错误")
    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "备注信息")
    private String note;
}
