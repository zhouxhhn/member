/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.request.sales.front;

import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "SalesFrontUserLoginRequest")
@Data
public class SalesFrontUserLoginRequest {

  @NotNull(message = "帐号不能为空")
  @ApiModelProperty(value = "userCode",required = true)
  private String userCode;

  @NotNull(message = "密码不能为空")
  @ApiModelProperty(value = "password",required = true)
  private String password;


  @NotNull(message = "门店帐号不能为空")
  @ApiModelProperty(value = "shopcode",required = true)
  private String shopcode;
}
