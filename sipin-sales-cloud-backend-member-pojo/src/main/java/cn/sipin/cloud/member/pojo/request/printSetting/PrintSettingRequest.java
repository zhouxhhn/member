/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.request.printSetting;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 打印设置
 */
@Data
public class PrintSettingRequest {

  @ApiModelProperty(value = "打印主机IP")
  private String printerIp;

  @ApiModelProperty(value = "打印机")
  private Integer printer;

  @ApiModelProperty(value = "店铺地址")
  private String shopAddress;

  @ApiModelProperty(value = "店铺电话")
  private String shopPhone;

  @ApiModelProperty(value = "票尾提示")
  private String ticketTips;

  @ApiModelProperty(value = "POS机IP")
  private String posIp;

  @ApiModelProperty(value = "0表示AO打印机，1表示商米内置打印机")
  private Integer printerType = 1;

  @ApiModelProperty(value = "0表示通联pos，1表示不使用pos机")
  private Integer posType = 1;


}
