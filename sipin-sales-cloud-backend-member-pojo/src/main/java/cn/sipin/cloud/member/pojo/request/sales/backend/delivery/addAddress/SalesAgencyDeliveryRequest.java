/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.request.sales.backend.delivery.addAddress;

import javax.validation.constraints.NotNull;

import cn.sipin.cloud.member.pojo.common.agencyDlivery.SalesAgencyDeliveryVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "SalesAgencyDeliveryRequest")
public class SalesAgencyDeliveryRequest extends SalesAgencyDeliveryVo {

  /**
   * 门店id
   */
  @NotNull(message = "门店id不能为空")
  @ApiModelProperty(value = "门店id",required = true)
  private Long shopId;
}
