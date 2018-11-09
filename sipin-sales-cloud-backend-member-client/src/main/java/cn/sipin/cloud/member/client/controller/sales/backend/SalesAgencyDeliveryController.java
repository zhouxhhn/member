/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.client.controller.sales.backend;

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
import cn.sipin.cloud.member.client.service.sales.SalesAgencyDeliveryService;
import cn.sipin.cloud.member.pojo.request.sales.backend.delivery.addAddress.SalesAgencyDeliveryRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.delivery.updateAddress.UpdateSalesAgencyDeliveryRequest;
import cn.sipin.cloud.member.pojo.response.sales.backend.delivery.SalesAgencyDeliveryResponse;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 经销商配送信息管理
 */
@Api(tags = "经销商端_后台_配送信息的管理接口")
@RequestMapping(path = "/sales/agency/delivery", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class SalesAgencyDeliveryController {

  private SalesAgencyDeliveryService salesAgencyDeliveryService;

  @Autowired
  public SalesAgencyDeliveryController(SalesAgencyDeliveryService salesAgencyDeliveryService){
    this.salesAgencyDeliveryService = salesAgencyDeliveryService;
  }


  /*
   * 新增地址
   */
  @ApiOperation(nickname = "salesAgencyDeliveryAddAddress",value = "新增地址接口")
  @PostMapping("/addAddress")
  public ResponseData addAddress(@RequestBody @Valid SalesAgencyDeliveryRequest salesAgencyDeliveryRequest, BindingResult result) {

    //请求的数据参数格式不正确
    if (result.hasErrors()) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage(),result.getAllErrors()
      );
    }
    return salesAgencyDeliveryService.addAddress(salesAgencyDeliveryRequest);
  }

  /*
   * 修改地址
   */
  @ApiOperation(nickname = "salesAgencyDeliveryUpdateAddress",value = "修改地址接口")
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
    return salesAgencyDeliveryService.updateAddress(addressId,updateSalesAgencyDeliveryRequest);
  }

  /*
   * 设为默认地址
   */
  @ApiOperation(nickname = "salesAgencyDeliveryEditDefaultAddress",value = "设置默认地址接口")
  @PutMapping("/setDefaultAddress/{addressId}")
  public ResponseData setDefaultAddress(@PathVariable Long  addressId) {
    return salesAgencyDeliveryService.setDefaultAddress(addressId);
  }


  /*
   * 删除地址
   */
  @ApiOperation(nickname = "salesAgencyDeliveryDeleteAddress",value = "删除地址接口")
  @PutMapping("/deleteAddress/{addressId}")
  public ResponseData deleteAddress(@PathVariable Long  addressId) {
    return salesAgencyDeliveryService.deleteAddress(addressId);
  }

  /*
   * 查询单个地址
   */
  @ApiOperation(nickname = "salesAgencyDeliverySearchAddress",value = "查询单个地址接口")
  @GetMapping("/searchAddress/{addressId}")
  public ResponseData<SalesAgencyDeliveryResponse> searchAddress(@PathVariable Long  addressId) {
    return salesAgencyDeliveryService.searchAddress(addressId);
  }

  /**
   * 查询该用户所有的地址
   */
  @ApiOperation(nickname = "salesAgencyDeliveryIndex",value = "查询该门店的所有地址接口")
  @GetMapping("/index/{shopId}")
  public ResponseData<Page> index(@PathVariable Long shopId,
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size) {
    return salesAgencyDeliveryService.index(shopId,page, size);
  }

}
