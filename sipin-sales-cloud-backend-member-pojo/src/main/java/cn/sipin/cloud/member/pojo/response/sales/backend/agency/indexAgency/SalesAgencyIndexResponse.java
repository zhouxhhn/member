/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.response.sales.backend.agency.indexAgency;

import com.baomidou.mybatisplus.annotations.TableField;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import cn.sipin.cloud.member.pojo.common.salesAgency.SalesAgencyVo;
import cn.sipin.cloud.member.pojo.pojo.salesShop.SalesShop;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SalesAgencyIndexResponse  extends SalesAgencyVo {

  @ApiModelProperty(value = "经销商id")
  private Long id;

  @ApiModelProperty(value = "经销商帐号")
  private String code;

  @ApiModelProperty(value = "帐户余额")
  private BigDecimal balance;


  /**
   * 经销商等级
   */
  @ApiModelProperty(value = "经销商等级")
  private String grade;
  /**
   * 通讯地址
   */
  @ApiModelProperty(value = "通讯地址")
  private String address;
  /**
   * 联系电话
   */
  @ApiModelProperty(value = "联系电话")
  private String phone;
  /**
   * 联系人
   */
  @ApiModelProperty(value = "联系人")
  private String contacts;

  /**
   * 结算折扣
   */
  @ApiModelProperty(value = "结算折扣")
  private BigDecimal discount;
  /**
   * 营业执照图片地址
   */
  @ApiModelProperty(value = "营业执照图片地址")
  @TableField("license_img_url")
  private String licenseImgUrl;
  /**
   * 营业执照图片密钥
   */
  @ApiModelProperty(value = "营业执照图片密钥")
  @TableField("license_img_secret")
  private String licenseImgSecret;



  /**
   * 备注
   */
  @ApiModelProperty(value = "备注")
  private String remark;

  /**
   * 备注
   */
  @ApiModelProperty(value = "外部编码")
  private String outerCode;

  private List<SalesShop> shopResponseList =new ArrayList<>();
}
