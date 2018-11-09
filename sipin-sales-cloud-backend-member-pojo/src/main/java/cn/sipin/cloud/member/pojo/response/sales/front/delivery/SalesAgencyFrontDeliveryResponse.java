/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.response.sales.front.delivery;

import cn.sipin.cloud.member.pojo.common.agencyDlivery.SalesAgencyDeliveryVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SalesAgencyFrontDeliveryResponse extends SalesAgencyDeliveryVo {


  /**
   * 门店id
   */
  @ApiModelProperty(value = "门店id")
  private Long shopId;

  @ApiModelProperty(value = "地址id")
  private Long id;

  /**
   * 省的中文名字
   */
  @ApiModelProperty(value = "省名字")
  private String provinceName;

  /**
   * 市的中文名字
   */
  @ApiModelProperty(value = "市名字")
  private String cityName;

  /**
   * 地区的中文名字
   */
  @ApiModelProperty(value = "地区名字")
  private String districtName;


  @ApiModelProperty(value = "省市区详细地址")
  private String fullAddress;


}
