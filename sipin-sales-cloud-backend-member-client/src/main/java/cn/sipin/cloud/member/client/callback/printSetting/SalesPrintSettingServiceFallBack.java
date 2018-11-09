/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.client.callback.printSetting;

import org.springframework.stereotype.Component;

import cn.sipin.cloud.member.client.service.sales.front.SalesFrontService;
import cn.sipin.cloud.member.client.service.sales.front.SalesPrintSettingService;
import cn.sipin.cloud.member.pojo.request.printSetting.PrintSettingRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.PurchasePaymentRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.SalesFrontUserLoginRequest;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;

/**
 * 经销商前台熔断器
 */
@Component
public class SalesPrintSettingServiceFallBack implements SalesPrintSettingService {

  @Override public ResponseData printSetting(String no, PrintSettingRequest request) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }
}
