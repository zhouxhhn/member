/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.response.sales.backend.shop.searchShop;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@JsonSerialize(include= Inclusion.ALWAYS)
@Data
public class SearchShopResponse {

  /**
   * 门店id
   */
  @ApiModelProperty(value = "门店id")
  private Long id;

  /**
   * 门店编码
   */
  @ApiModelProperty(value = "门店编码")
  private String code;

  /**
   * 门店名称
   */
  @ApiModelProperty(value = "门店名称")
  private String name;
  /**
   * 门店地址
   */
  @ApiModelProperty(value = "门店地址")
  private String address;

  /**
   * 门店电话
   */
  @ApiModelProperty(value = "门店电话")
  private String phone;

  /**
   * 经销商帐号
   */
  @ApiModelProperty(value = "经销商帐号")
  private String agencyCode;

  /**
   * 门店sourceId
   */
  @ApiModelProperty(value = "门店sourceId")
  private String sourceId;

}
