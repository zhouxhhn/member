/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.common.address;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@JsonSerialize(include= Inclusion.ALWAYS)
@Data
public class MemberAddressVo {


  /**
   * 用户id
   */
  @NotNull(message = "用户id不能为空")
  @ApiModelProperty(value = "用户id",required = true)
  private Long userId;

  /**
   * 收货人
   */
  @NotNull(message = "收货人不能为空")
  @ApiModelProperty(value = "收货人",required = true)
  private String receiverName;

  /**
   * 手机号码
   */
  @NotNull(message = "手机号码不能为空")
  private String cellphone;

  /**
   * 详细地址
   */
  @NotNull(message = "详细地址不能为空")
  @ApiModelProperty(value = "详细地址",required = true)
  private String address;


  /**
   * 省code
   */
  @NotNull(message = "省code不能为空")
  @ApiModelProperty(value = "省code",required = true)
  private Long provinceCode;

  /**
   * 市code
   */
  @NotNull(message = "市code不能为空")
  @ApiModelProperty(value = "市code",required = true)
  private Long cityCode;

  /**
   * 区code
   */
  @NotNull(message = "区code不能为空")
  @ApiModelProperty(value = "区code",required = true)
  private Long districtCode;


  /**
   * 默认地址:0代表不是默认地址，1代表默认地址
   */
  @NotNull(message = "默认地址状态不能为空")
  @ApiModelProperty(value = "默认地址:0代表不是默认地址，1代表默认地址",required = true)
  private Integer defaultAddress;

  /**
   * 状态:0代表有效，1代表失效(删除)
   */
  @ApiModelProperty(value = "状态:0代表有效，1代表失效(删除)")
  private Integer status = 0;

}
