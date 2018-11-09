/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.response.sales.backend.user.indexUser;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import java.util.List;
import javax.validation.constraints.NotNull;

import cn.sipin.cloud.member.pojo.pojo.salesPermission.SalesRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@JsonSerialize(include= Inclusion.ALWAYS)
@Data
public class IndexUserResponse {

  @ApiModelProperty(value = "id")
  private Long id;

  @ApiModelProperty(value = "类型,门店管理员为0，系统管理员为1")
  private int type;

  @ApiModelProperty(value = "状态,有效为0，失效为1")
  private int status;

  @ApiModelProperty(value = "门店帐号")
  private String shopCode;

  @ApiModelProperty(value = "门店名字")
  private String shopName;

  @ApiModelProperty(value = "姓名")
  private String name;

  @ApiModelProperty(value = "员工编号")
  private String code;

  @ApiModelProperty(value = "工号")
  private String empno;

  @ApiModelProperty(value = "角色")
  private List<SalesRole> roleList;
}
