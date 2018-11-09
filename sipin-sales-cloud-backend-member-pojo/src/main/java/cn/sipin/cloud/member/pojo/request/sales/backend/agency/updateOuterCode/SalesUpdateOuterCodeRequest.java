/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.request.sales.backend.agency.updateOuterCode;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "SalesUpdateOuterCodeRequest")
public class SalesUpdateOuterCodeRequest {

  /**
   * 外部编码
   */
  @NotNull(message = "外部编码")
  @ApiModelProperty(value = "外部编码",required = true)
  private String outerCode ;
}
