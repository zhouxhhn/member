/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.request.sales.backend.shop.updateSourceIdRequest;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "SalesUpdateSourceIdRequest")
public class SalesUpdateSourceIdRequest {

  /**
   * sourceId
   */
  @NotNull(message = "sourceId")
  @ApiModelProperty(value = "sourceId",required = true)
  private String sourceId ;
}
