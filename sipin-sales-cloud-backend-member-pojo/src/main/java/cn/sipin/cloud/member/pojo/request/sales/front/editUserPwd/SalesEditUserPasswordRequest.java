/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.request.sales.front.editUserPwd;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "SalesEditUserPasswordRequest")
@Data
public class SalesEditUserPasswordRequest {

  @NotNull(message = "旧密码不能为空")
  @ApiModelProperty(value = "旧密码",required = true)
  private String oldPassword;

  @NotNull(message = "新密码不能为空")
  @ApiModelProperty(value = "新密码",required = true)
  @Size(min = 6,message = "密码过短，密码长度至少6位")
  private String newPassword;

  @NotNull(message = "重复输入的新密码不能为空")
  @ApiModelProperty(value = "重复输入的新密码",required = true)
  @Size(min = 6,message = "密码过短，密码长度至少6位")
  private String renewPassword;
}
