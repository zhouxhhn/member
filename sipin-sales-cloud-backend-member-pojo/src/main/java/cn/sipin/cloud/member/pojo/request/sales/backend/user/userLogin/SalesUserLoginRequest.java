/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.request.sales.backend.user.userLogin;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SalesUserLoginRequest {

  @NotNull(message = "帐号不能为空")
  @ApiModelProperty(value = "帐号",required = true)
  private String userCode;

  @NotNull(message = "密码不能为空")
  @ApiModelProperty(value = "密码",required = true)
  private String password;

  @ApiModelProperty(value = "门店帐号")
  private String shopCode;
}
