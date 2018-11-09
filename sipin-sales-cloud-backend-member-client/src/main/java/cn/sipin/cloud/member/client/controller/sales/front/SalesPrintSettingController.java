/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.client.controller.sales.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import cn.sipin.cloud.member.client.service.sales.front.SalesFrontService;
import cn.sipin.cloud.member.client.service.sales.front.SalesPrintSettingService;
import cn.sipin.cloud.member.pojo.request.printSetting.PrintSettingRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.PurchasePaymentRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.SalesFrontUserLoginRequest;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 经销商前台controller
 */
@Api(tags = "经销商_前台_打印设置接口")
@RestController
@RequestMapping(path = "/sales/front/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SalesPrintSettingController {

  private SalesPrintSettingService salesPrintSettingService;


  @Autowired
  public SalesPrintSettingController(SalesPrintSettingService salesPrintSettingService){
    this.salesPrintSettingService = salesPrintSettingService;
  }
  /**
   * 打印设置
   */
  @ApiOperation(nickname = "printSettingSalesOrder",value = "打印设置的接口")
  @PutMapping("/printSetting/{shopCode}")
  public ResponseData printSetting(@PathVariable String shopCode, @RequestBody @Valid PrintSettingRequest request, BindingResult result
  ) {
    if (result.hasErrors()) {
      return new ResponseData<>(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),result.getFieldError().getDefaultMessage());
    }
    return salesPrintSettingService.printSetting(shopCode,request);
  }
}
