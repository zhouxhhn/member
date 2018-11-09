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
import cn.sipin.cloud.member.client.service.sales.front.SalesFrontService;
import cn.sipin.cloud.member.pojo.request.sales.front.PurchasePaymentRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.SalesFrontUserLoginRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.editUserPwd.SalesEditUserPasswordRequest;
import cn.sipin.cloud.member.pojo.response.sales.backend.user.indexUser.IndexUserResponse;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 经销商前台controller
 */
@Api(tags = "经销商_前台_基础信息接口")
@RestController
@RequestMapping(path = "/sales/front/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SalesFrontController {

  private SalesFrontService salesFrontService;


  @Autowired
  public SalesFrontController(SalesFrontService salesFrontService){
    this.salesFrontService = salesFrontService;
  }

  /**
   * 前台查询所有门店
   */
  @ApiOperation(nickname = "salesFrontSearchShop",value = "前台查询所有门店列表")
  @GetMapping("/searchShop")
  public ResponseData searchShop(@RequestParam(value = "type",required = false,defaultValue = "all") String type,
      @RequestParam(value = "page", required = false,defaultValue = "1") int page,
      @RequestParam(value = "size", required = false,defaultValue = "15") int size) {
    return salesFrontService.searchShop(type,page,size);
  }

  /**
   * 登录
   */
  @ApiOperation(nickname = "salesFrontUserLogin",value = "前台登录")
  @PostMapping("/userLogin")
  public ResponseData userLogin(@RequestBody @Valid SalesFrontUserLoginRequest request
      , BindingResult result) {
    //请求的数据参数格式不正确
    if (result.hasErrors()) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage(), result.getAllErrors()
      );
    }
    return salesFrontService.userLogin(request);
  }

  /**
   * 前台返回该用户的经销商信息
   */
  @ApiOperation(nickname = "salesFrontGetAgencyInfo",value = "返回该用户的经销商信息")
  @GetMapping("/getAgencyInfo")
  public ResponseData getAgencyInfo() {
    return salesFrontService.getAgencyInfo();
  }

  @ApiOperation(nickname = "salesFrontIndexUser",value = "获取该用户同门店的所有店员")
  @GetMapping("/indexUser")
  public ResponseData<Page<IndexUserResponse>> indexUser(){
    return salesFrontService.indexUser();
  }

  /**
   * 经销商采购订单支付接口
   */
  @ApiOperation(nickname = "salesFrontPurchasePayment",value = "经销商采购订单支付接口")
  @PostMapping(value = "/purchasePayment")
  public ResponseData purchasePayment(@RequestBody PurchasePaymentRequest request, BindingResult result) {
    if (result.hasErrors()) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage(), result.getAllErrors()
      );
    }
    return salesFrontService.purchasePayment(request);
  }

  /**
   * 根据采购订单号查支付流水号即是否已扣款
   */
  @ApiOperation(nickname = "salesFrontGetPaymentNo",value = "根据采购订单号查支付流水号接口")
  @GetMapping("/getPaymentNo/{purchaseOrderNo}")
  public ResponseData getPaymentNoByPurchaseNo(@PathVariable String purchaseOrderNo) {
    return salesFrontService.getPaymentNoByPurchaseNo(purchaseOrderNo);
  }

  @ApiOperation(nickname = "salesFrontGetUserByToken",value = "根据token获取员工信息")
  @GetMapping(value = "/getUserByToken")
  public ResponseData getUserByToken() {
    return salesFrontService.getUserByToken();
  }

  /**
   * 修改个人密码
   */
  @ApiOperation(nickname = "salesFrontUserEditUserPwd",value = "修改个人密码")
  @PutMapping("/editUserPwd")
  public ResponseData editUserPwd(@RequestBody @Valid SalesEditUserPasswordRequest request
      , BindingResult result) {
    //请求的数据参数格式不正确
    if (result.hasErrors()) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage(),result.getAllErrors()
      );
    }
    return salesFrontService.editUserPwd(request);
  }

}
