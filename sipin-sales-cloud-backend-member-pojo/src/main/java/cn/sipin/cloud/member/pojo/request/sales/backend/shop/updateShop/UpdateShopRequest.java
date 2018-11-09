/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.request.sales.backend.shop.updateShop;

import com.baomidou.mybatisplus.annotations.TableField;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "UpdateShopRequest")
@Data
public class UpdateShopRequest {

  /**
   * 门店名称
   */
  @NotNull(message = "门店名称不能为空")
  @ApiModelProperty(value = "门店名称",required = true)
  private String name;
  /**
   * 门店地址
   */
  @NotNull(message = "门店地址不能为空")
  @ApiModelProperty(value = "门店地址",required = true)
  private String address;

  /**
   * 门店电话
   */
  @NotNull(message = "门店电话不能为空")
  @ApiModelProperty(value = "门店电话",required = true)
  private String phone;

  /**
   * 经销商帐号
   */
  @NotNull(message = "经销商帐号不能为空")
  @ApiModelProperty(value = "经销商帐号",required = true)
  @TableField("agency_code")
  private String agencyCode;
}
