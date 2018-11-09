/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.response.sales.front.user;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SalesUserFrontResponse {

  @ApiModelProperty(value = "门店员工主健id")
  private Long id;
  /**
   * 员工帐号
   */
  @ApiModelProperty(value = "员工帐号")
  private String code;
  /**
   * 姓名
   */
  @ApiModelProperty(value = "姓名")
  private String name;
}
