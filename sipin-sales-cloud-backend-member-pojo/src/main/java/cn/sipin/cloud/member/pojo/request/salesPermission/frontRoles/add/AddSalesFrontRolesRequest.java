/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.request.salesPermission.frontRoles.add;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 新增角色请求参数封装类
 */
@ApiModel(value = "AddSalesFrontRolesRequest")
@Data
public class AddSalesFrontRolesRequest {

  /**
   * 角色名称
   */
  @NotNull(message = "角色名称不能为空")
  @ApiModelProperty(value = "角色名称",required = true)
  private String name;


  /**
   * 状态
   */
  @NotNull(message = "状态不能为空")
  @ApiModelProperty(value = "状态：启用为0，禁用为1",required = true)
  private Integer status;

}
