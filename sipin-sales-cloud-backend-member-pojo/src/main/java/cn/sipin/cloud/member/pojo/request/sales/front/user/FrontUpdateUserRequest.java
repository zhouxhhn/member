/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.request.sales.front.user;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "FrontUpdateUserRequest")
public class FrontUpdateUserRequest {

  @NotNull(message = "姓名不能为空")
  @ApiModelProperty(value = "姓名",required = true)
  private String name;

  @NotNull(message = "工号不能为空")
  @ApiModelProperty(value = "工号",required = true)
  private String empno;

  @NotNull(message = "密码不能为空")
  @ApiModelProperty(value = "密码",required = true)
  private String password;
}
