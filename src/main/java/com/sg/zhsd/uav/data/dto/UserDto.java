package com.sg.zhsd.uav.data.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description="用户查询参数信息")
@Data
public class UserDto {

    @ApiModelProperty(value="用户名")
    private String username;

    @ApiModelProperty(value="密码")
    private String password;

    @ApiModelProperty(value="验证码")
    private String code;

    @ApiModelProperty(value="用户名")
    private String id;

    @ApiModelProperty(value="逻辑删除")
    private String deleted;

    @ApiModelProperty(value="电话")
    private String phone;

    @ApiModelProperty(value="年龄")
    private String age;

    @ApiModelProperty(value="地址")
    private String address;

    @ApiModelProperty(value="职位")
    private String position;

    @ApiModelProperty(value="说明")
    private String comment;
}
