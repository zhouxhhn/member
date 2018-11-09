/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.client.service.sales.front;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.sipin.cloud.member.client.callback.printSetting.SalesPrintSettingServiceFallBack;
import cn.sipin.cloud.member.client.callback.sales.front.SalesFrontServiceFallBack;
import cn.sipin.cloud.member.pojo.request.printSetting.PrintSettingRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.PurchasePaymentRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.SalesFrontUserLoginRequest;
import cn.siyue.platform.base.ResponseData;

@FeignClient(name = "sales-member-service",path = "/sales/front", fallback = SalesPrintSettingServiceFallBack.class)
public interface SalesPrintSettingService {

  @RequestMapping(value = "/printSetting/{no}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData printSetting(@PathVariable(value = "no") String no,PrintSettingRequest request);

}
