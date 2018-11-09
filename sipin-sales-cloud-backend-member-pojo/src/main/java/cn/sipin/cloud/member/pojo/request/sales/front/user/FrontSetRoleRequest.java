/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.request.sales.front.user;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "FrontSetRoleRequest")
public class FrontSetRoleRequest {

  @NotNull(message = "角色id不能为空")
  @ApiModelProperty(value = "角色",required = true)
  List<Long> roles;
}
