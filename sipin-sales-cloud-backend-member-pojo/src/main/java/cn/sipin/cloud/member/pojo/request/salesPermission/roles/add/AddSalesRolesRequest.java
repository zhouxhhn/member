/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.request.salesPermission.roles.add;

import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 新增角色请求参数封装类
 */
@Data
public class AddSalesRolesRequest {

  /**
   * 角色名称
   */
  @NotNull(message = "角色名称不能为空")
  @ApiModelProperty(value = "角色名称",required = true)
  private String name;

  /**
   * 子系统
   */
  @NotNull(message = "子系统不能为空")
  @ApiModelProperty(value = "子系统：前台为0，后台为1",required = true)
  private Integer childrenSystem;
  /**
   * 状态
   */
  @NotNull(message = "状态不能为空")
  @ApiModelProperty(value = "状态：启用为0，禁用为1",required = true)
  private Integer status;
  /**
   * 类型
   */
  @NotNull(message = "类型不能为空")
  @ApiModelProperty(value = "类型：公有为0，私有为1，系统管理员为2",required = true)
  private Integer type;
  /**
   * 可用范围
   */
  @ApiModelProperty(value = "可用范围")
  private Long scope;
}
