/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.request.salesPermission.roles.index;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查找角色列表请求参数封装类
 */
@Data
public class IndexSalesRolesRequest {

  @ApiModelProperty(value = "名称")
  private String name;

  @ApiModelProperty(value = "编号")
  private String code;

  @ApiModelProperty(value = "可用范围")
  private String scope;
}
