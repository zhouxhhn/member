/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.request.sales.backend.shop.index;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "IndexShopRequest")
@Data
public class IndexShopRequest {

  @ApiModelProperty(value = "门店名称")
  private String name;

  @ApiModelProperty(value = "门店编码")
  private String code;

  @ApiModelProperty(value = "所属经销商")
  private String agencyName;
}
