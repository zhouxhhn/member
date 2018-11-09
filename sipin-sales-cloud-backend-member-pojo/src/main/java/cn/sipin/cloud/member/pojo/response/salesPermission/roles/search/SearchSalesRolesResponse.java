/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.response.salesPermission.roles.search;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchSalesRolesResponse {

  /**
   * 角色id
   */
  @ApiModelProperty(value = "角色id")
  private Long id;

  /**
   *编码
   */
  @ApiModelProperty(value = "编码")
  private String code;

  /**
   * 角色名称
   */
  @ApiModelProperty(value = "角色名称")
  private String name;

  /**
   * 子系统
   */
  @ApiModelProperty(value = "子系统：前台为0，后台为1")
  private Integer childrenSystem;
  /**
   * 状态
   */
  @ApiModelProperty(value = "状态：启用为0，禁用为1")
  private Integer status;
  /**
   * 类型
   */
  @ApiModelProperty(value = "类型：公有为0，私有为1")
  private Integer type;
  /**
   * 可用范围
   */
  @ApiModelProperty(value = "可用范围")
  private Long scope;

  /**
   * 创建人
   */
  @ApiModelProperty(value = "创建人")
  private String  creator;

}
