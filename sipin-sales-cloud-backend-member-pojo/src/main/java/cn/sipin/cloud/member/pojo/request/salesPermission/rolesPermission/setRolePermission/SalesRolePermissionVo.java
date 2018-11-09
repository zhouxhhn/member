/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.request.salesPermission.rolesPermission.setRolePermission;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SalesRolePermissionVo {

  /**
   * 权限组id
   */
  @NotNull(message = "权限组id不能为空")
  @ApiModelProperty(value = "权限组id",required = true)
  private Long groupId;

  /**
   * 角色组id
   */
  @NotNull(message = "权限id不能为空")
  @ApiModelProperty(value = "权限id",required = true)
  private Long permissionActionId;
}
