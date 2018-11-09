/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.request.sales.backend.user.indexUser;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class IndexUserRequest {

  @ApiModelProperty(value = "姓名")
  private String userName;

  @ApiModelProperty(value = "工号")
  private String empno;

  @ApiModelProperty(value = "所属门店code")
  private String shopCode;
}
