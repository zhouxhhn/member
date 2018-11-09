/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.response.sales.backend.user.searchUser;

import java.util.List;

import javax.validation.constraints.NotNull;

import cn.sipin.cloud.member.pojo.response.salesPermission.roles.searchUser.SalesUserRolesResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchUserResponse {

  @ApiModelProperty(value = "类型,门店管理员为0，系统管理员为1",required = true)
  private int type;

  @ApiModelProperty(value = "所属门店")
  private String shopCode;

  @ApiModelProperty(value = "所属门店名称")
  private String shopName;

  @ApiModelProperty(value = "姓名")
  private String name;

  @ApiModelProperty(value = "帐号")
  private String code;

  @ApiModelProperty(value = "工号")
  private String empno;

  @ApiModelProperty(value = "角色")
  private List<SalesUserRolesResponse> roles;
}
