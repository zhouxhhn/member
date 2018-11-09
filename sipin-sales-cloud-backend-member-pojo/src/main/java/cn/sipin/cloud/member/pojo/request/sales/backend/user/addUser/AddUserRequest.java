/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.request.sales.backend.user.addUser;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddUserRequest {

  @NotNull(message = "类型不能为空")
  @ApiModelProperty(value = "类型,门店管理员为0，系统管理员为1",required = true)
  private int type;

  @ApiModelProperty(value = "所属门店")
  private String shopCode;

  @NotNull(message = "姓名不能为空")
  @ApiModelProperty(value = "姓名",required = true)
  private String name;

  @NotNull(message = "工号不能为空")
  @ApiModelProperty(value = "工号",required = true)
  private String empno;

  @NotNull(message = "密码不能为空")
  @ApiModelProperty(value = "密码",required = true)
  private String password;


}
