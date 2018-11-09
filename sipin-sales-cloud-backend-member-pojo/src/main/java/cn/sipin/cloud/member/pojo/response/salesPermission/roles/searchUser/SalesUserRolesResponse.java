/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.response.salesPermission.roles.searchUser;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SalesUserRolesResponse {

  /**
   * 角色id
   */
  @ApiModelProperty(value = "角色id")
  private Long id;

  /**
   * 角色名称
   */
  private String name;

  /**
   * 状态
   */
  @ApiModelProperty(value = "状态：启用为0，禁用为1")
  private Integer status;


}
