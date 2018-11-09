/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.service.controller.sales.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import cn.sipin.cloud.member.pojo.request.printSetting.PrintSettingRequest;
import cn.sipin.cloud.member.service.service.printSetting.PrintSettingServiceContract;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.httplog.annotation.LogAnnotation;

/**
 * 经销商前台controller
 */
@RestController
@RequestMapping(path = "/sales/front/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SalesPrintSettingController {

  private PrintSettingServiceContract printSettingService;


  @Autowired
  public SalesPrintSettingController(PrintSettingServiceContract printSettingService){
    this.printSettingService = printSettingService;
  }

  /**
   * 打印设置
   */
  @LogAnnotation
  @PutMapping(value ="/printSetting/{shopCode}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseData printSetting(@PathVariable String shopCode,@RequestBody @Valid PrintSettingRequest request) {
    return printSettingService.printSetting(shopCode,request);
  }
}
