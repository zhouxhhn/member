/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.client.controller.sales.front;

import com.baomidou.mybatisplus.plugins.Page;
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
import cn.sipin.cloud.member.client.service.sales.front.SalesAgencyFrontDeliveryService;
import cn.sipin.cloud.member.pojo.request.sales.backend.delivery.updateAddress.UpdateSalesAgencyDeliveryRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.delivery.addAddress.SaveSalesAgencyFrontDeliveryRequest;
import cn.sipin.cloud.member.pojo.response.sales.backend.delivery.SalesAgencyDeliveryResponse;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 经销商配送信息管理
 */
@Api(tags = "经销商端_前台_配送信息的管理接口")
@RequestMapping(path = "/sales/agency/front/delivery", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class SalesAgencyFrontDeliveryController {

  private SalesAgencyFrontDeliveryService salesAgencyFrontDeliveryService;

  @Autowired
  public SalesAgencyFrontDeliveryController(SalesAgencyFrontDeliveryService salesAgencyFrontDeliveryService){
    this.salesAgencyFrontDeliveryService = salesAgencyFrontDeliveryService;
  }


  /*
   * 新增地址
   */
  @ApiOperation(nickname = "salesAgencyDeliveryFrontAddAddress",value = "新增地址接口")
  @PostMapping("/addAddress")
  public ResponseData addAddress(@RequestBody @Valid SaveSalesAgencyFrontDeliveryRequest saveSalesAgencyDeliveryRequest, BindingResult result) {
    //请求的数据参数格式不正确
    if (result.hasErrors()) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage(),result.getAllErrors()
      );
    }
    return salesAgencyFrontDeliveryService.addAddress(saveSalesAgencyDeliveryRequest);
  }

  /*
   * 修改地址
   */
  @ApiOperation(nickname = "salesAgencyDeliveryFrontUpdateAddress",value = "修改地址接口")
  @PutMapping("/updateAddress/{addressId}")
  public ResponseData updateAddress(@PathVariable Long addressId,
                                    @RequestBody @Valid UpdateSalesAgencyDeliveryRequest updateSalesAgencyDeliveryRequest,
                                    BindingResult result) {
    //请求的数据参数格式不正确
    if (result.hasErrors()) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage(),result.getAllErrors()
      );
    }
    return salesAgencyFrontDeliveryService.updateAddress(addressId,updateSalesAgencyDeliveryRequest);
  }

  /*
   * 设为默认地址
   */
  @ApiOperation(nickname = "salesAgencyDeliveryFrontEditDefaultAddress",value = "设置默认地址接口")
  @PutMapping("/setDefaultAddress/{addressId}")
  public ResponseData setDefaultAddress(@PathVariable Long  addressId) {
    return salesAgencyFrontDeliveryService.setDefaultAddress(addressId);
  }


  /*
   * 删除地址
   */
  @ApiOperation(nickname = "salesAgencyDeliveryFrontDeleteAddress",value = "删除地址接口")
  @PutMapping("/deleteAddress/{addressId}")
  public ResponseData deleteAddress(@PathVariable Long  addressId) {
    return salesAgencyFrontDeliveryService.deleteAddress(addressId);
  }

  /*
   * 查询单个地址
   */
  @ApiOperation(nickname = "salesAgencyDeliveryFrontSearchAddress",value = "查询单个地址接口")
  @GetMapping("/searchAddress/{addressId}")
  public ResponseData<SalesAgencyDeliveryResponse> searchAddress(@PathVariable Long  addressId) {
    return salesAgencyFrontDeliveryService.searchAddress(addressId);
  }

  /**
   * 查询该用户所有的地址
   */
  @ApiOperation(nickname = "salesAgencyDeliveryFrontIndex",value = "查询该门店的所有地址接口")
  @GetMapping("/index")
  public ResponseData<Page> index(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size) {
    return salesAgencyFrontDeliveryService.index(page, size);
  }

}
