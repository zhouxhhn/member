/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.common.salesAgency;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@JsonSerialize(include= Inclusion.ALWAYS)
@Data
public class SalesAgencyVo {

  /**
   * 经销商名称
   */
  @NotNull(message = "经销商名称不能为空")
  @Size(min = 1, max = 64, message = "经销商名称过长或者过短")
  @ApiModelProperty(value = "经销商名称", required = true)
  private String name;
  /**
   * 经销商等级
   */
  @ApiModelProperty(value = "等级", required = true)
  @NotNull(message = "等级不能为空")
  private String grade;
  /**
   * 通讯地址
   */
  @ApiModelProperty(value = "通讯地址")
  private String address;
  /**
   * 联系电话
   */
  @ApiModelProperty(value = "联系电话")
  private String phone;
  /**
   * 联系人
   */
  @ApiModelProperty(value = "联系人")
  private String contacts;

  /**
   * 结算折扣
   */
  @ApiModelProperty(value = "结算折扣",required = true)
  @NotNull(message = "结算折扣不能为空")
  private BigDecimal discount;

  /**
   * 营业执照图片地址
   */
  @ApiModelProperty(value = "营业执照图片地址")
  private String licenseImgUrl;

  /**
   * 营业执照图片密钥
   */
  @ApiModelProperty(value = "营业执照图片密钥")
  private String licenseImgSecret;

  /**
   * 备注
   */
  @ApiModelProperty(value = "备注")
  private String remark;
}
