/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.response.salesPermission.permissionGroup.indexAction;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SalesPermissionActionResponse {

  @ApiModelProperty(value = "id")
  private Long id;

  /**
   * 显示名
   */
  @ApiModelProperty(value = "权限名")
  private String displayName;
  /**
   * 权限分组
   */
  @ApiModelProperty(value = "权限组")
  private Long groupId;

  /**
   * 状态
   */
  @ApiModelProperty(value = "状态：选中(1),没有选中(0)")
  private int status;
}
