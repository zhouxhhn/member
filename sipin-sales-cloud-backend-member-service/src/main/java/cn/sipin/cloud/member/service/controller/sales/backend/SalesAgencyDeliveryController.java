/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.service.controller.sales.backend;

import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.sipin.cloud.member.pojo.request.sales.backend.delivery.addAddress.SalesAgencyDeliveryRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.delivery.updateAddress.UpdateSalesAgencyDeliveryRequest;
import cn.sipin.cloud.member.pojo.response.sales.backend.delivery.SalesAgencyDeliveryResponse;
import cn.sipin.cloud.member.service.service.sales.SalesAgencyDeliveryServiceContract;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.httplog.annotation.LogAnnotation;

/**
 * 门店_配送信息管理
 */
@RequestMapping(path = "/sales/agency/delivery", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class SalesAgencyDeliveryController {

  private SalesAgencyDeliveryServiceContract salesAgencyDeliveryService;

  @Autowired
  public SalesAgencyDeliveryController(SalesAgencyDeliveryServiceContract salesAgencyDeliveryService
                                       ){
    this.salesAgencyDeliveryService = salesAgencyDeliveryService;
  }

  /*
   * 新增地址
   */
  @LogAnnotation
  @PostMapping("/addAddress")
  public ResponseData addAddress(@RequestBody SalesAgencyDeliveryRequest request) {
    return salesAgencyDeliveryService.addAddress(request);
  }

  /*
   * 修改地址
   */
  @LogAnnotation
  @PutMapping("/updateAddress/{addressId}")
  public ResponseData updateAddress(@PathVariable Long addressId,
                                    @RequestBody UpdateSalesAgencyDeliveryRequest request) {
    return salesAgencyDeliveryService.updateAddress(addressId,request);
  }

  /*
   * 修改默认地址
   */
  @LogAnnotation
  @PutMapping("/setDefaultAddress/{addressId}")
  public ResponseData setDefaultAddress(@PathVariable Long addressId) {
    return  salesAgencyDeliveryService.setDefaultAddress(addressId);
  }


  /*
   * 删除地址
   */
  @LogAnnotation
  @PutMapping("/deleteAddress/{addressId}")
  public ResponseData deleteAddress(@PathVariable Long addressId) {
    return salesAgencyDeliveryService.deleteAddress(addressId);
  }

  /*
   * 查询单个地址
   */
  @LogAnnotation
  @GetMapping("/searchAddress/{addressId}")
  public ResponseData<SalesAgencyDeliveryResponse> searchAddress(@PathVariable Long addressId) {
    return salesAgencyDeliveryService.searchAddress(addressId);
  }

  /**
   * 查询该门店所有的地址
   */
  @LogAnnotation
  @GetMapping("/index/{shopId}")
  public ResponseData<Page> index(@PathVariable Long shopId,
                                  @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                  @RequestParam(value = "size", required = false, defaultValue = "15") int size) {
    return salesAgencyDeliveryService.index(shopId,page,size);
  }
}
