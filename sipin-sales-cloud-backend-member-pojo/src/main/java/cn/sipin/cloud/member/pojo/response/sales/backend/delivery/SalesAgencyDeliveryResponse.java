/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.response.sales.backend.delivery;

import cn.sipin.cloud.member.pojo.common.agencyDlivery.SalesAgencyDeliveryVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SalesAgencyDeliveryResponse extends SalesAgencyDeliveryVo {


  /**
   * 门店id
   */
  @ApiModelProperty(value = "门店id")
  private Long shopId;

  @ApiModelProperty(value = "地址id")
  private Long id;
}
