/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.response.sales.backend.agency.index;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class IndexSalesAgencyResponse {

  @ApiModelProperty(value = "经销商id")
  private Long id;

  @ApiModelProperty(value = "经销商帐号")
  private String code;

  @ApiModelProperty(value = "经销商名称")
  private String name;

  @ApiModelProperty(value = "外部编码")
  private String outerCode;


}
