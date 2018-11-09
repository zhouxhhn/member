/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.request.salesPermission.rolesPermission.setRolePermission;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SalesRolePermissionSettingRequest {

  /**
   * 权限组id
   */
  @NotNull(message = "权限组id不能为空")
  @ApiModelProperty(value = "权限组id",required = true)
  private Long groupId;

  /**
   * 权限id
   */
  @ApiModelProperty(value = "权限ids")
  private List<Long> permissionActionIds;
}
