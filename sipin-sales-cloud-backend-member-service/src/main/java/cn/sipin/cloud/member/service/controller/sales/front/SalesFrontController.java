/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.service.controller.sales.front;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.BeanUtils;
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
import java.util.ArrayList;
import java.util.List;
import cn.sipin.cloud.member.pojo.pojo.salesShop.SalesShop;
import cn.sipin.cloud.member.pojo.pojo.salesUser.SalesUser;
import cn.sipin.cloud.member.pojo.pojo.salesUser.SalesUserCompose;
import cn.sipin.cloud.member.pojo.request.sales.front.PurchasePaymentRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.SalesFrontUserLoginRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.editUserPwd.SalesEditUserPasswordRequest;
import cn.sipin.cloud.member.pojo.response.sales.backend.user.indexUser.IndexUserResponse;
import cn.sipin.cloud.member.pojo.response.sales.front.user.SalesUserFrontResponse;
import cn.sipin.cloud.member.service.service.sales.SalesAgencyServiceContract;
import cn.sipin.cloud.member.service.service.sales.SalesShopServiceContract;
import cn.sipin.cloud.member.service.service.sales.SalesUserServiceContract;
import cn.sipin.cloud.member.service.util.JsonTokenUtil;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import cn.siyue.platform.httplog.annotation.LogAnnotation;

/**
 * 经销商前台controller
 */
@RestController
@RequestMapping(path = "/sales/front/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SalesFrontController {

  private SalesShopServiceContract salesShopService;

  private SalesUserServiceContract salesUserService;

  private SalesAgencyServiceContract salesAgencyService;

  private JsonTokenUtil jsonTokenUtil;

  @Autowired
  public SalesFrontController(SalesShopServiceContract salesShopService,
                              SalesUserServiceContract salesUserService,
                              SalesAgencyServiceContract salesAgencyService,
                              JsonTokenUtil jsonTokenUtil){
    this.salesShopService = salesShopService;
    this.salesUserService = salesUserService;
    this.salesAgencyService = salesAgencyService;
    this.jsonTokenUtil = jsonTokenUtil;
  }

  /**
   * 查询所有门店
   */
  @LogAnnotation
  @GetMapping("/searchShop")
  public ResponseData searchShop(@RequestParam(value = "type",required = false,defaultValue = "all") String type,
      @RequestParam(value = "page", required = false,defaultValue = "1") int page,
      @RequestParam(value = "size", required = false,defaultValue = "15") int size) {
    Page userPage = new Page<SalesShop>(page, size);
    userPage.setAsc(false);
    return salesShopService.searchAllShop(type,page,size);

  }

  /**
   * 前台登录
   */
  @LogAnnotation
  @PostMapping(value = "/userLogin")
  public ResponseData userLogin(@RequestBody SalesFrontUserLoginRequest request) {
     return salesUserService.userLogin(request.getUserCode(),
                                       request.getPassword(), request.getShopcode());
  }

  /**
   * 获取经销商信息
   */
  @LogAnnotation
  @GetMapping(value = "/getAgencyInfo")
  public ResponseData getAgencyInfo() {
    return salesAgencyService.getAgencyInfo();
  }

  /**
   * 获取指定门店的管理员列表
   * @param page 页码
   * @param size 每页显示页码的大小
   * @return 角色列表信息
   */
  @LogAnnotation
  @GetMapping("/indexUser")
  public ResponseData<Page<IndexUserResponse>> indexUser(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size
  ) {
    Long userId = jsonTokenUtil.getCurrentUserId();
    SalesUser salesUser =  salesUserService.selectById(userId);

    Page userPage = new Page(page, size);
    userPage.setAsc(false);
    userPage = salesUserService.selectPage(userPage, new EntityWrapper<SalesUser>().eq("shop_code", salesUser.getShopCode()));
    List<SalesUserFrontResponse> responses = new ArrayList<>();
    if(userPage.getRecords() != null && userPage.getRecords().size() >0){
      List<SalesUser> list = userPage.getRecords();
      int length = list.size();
      for (int i = 0; i < length; i++) {
        SalesUser user = list.get(i);
        SalesUserFrontResponse response = new SalesUserFrontResponse();
        BeanUtils.copyProperties(user, response);
        responses.add(response);
      }
      userPage.setRecords(responses);
    }

    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(), ResponseBackCode.SUCCESS.getMessage(), userPage);
  }

  /**
    *采购订单支付接口
   */
  @LogAnnotation
  @PostMapping(value = "/purchasePayment")
  public ResponseData purchasePayment(@RequestBody PurchasePaymentRequest request) {
    return salesAgencyService.purchasePayment(request);
  }

  /**
   * 根据采购订单号查支付流水号即是否已扣款
   */
  @LogAnnotation
  @GetMapping("/getPaymentNo/{purchaseOrderNo}")
  public ResponseData getPaymentNoByPurchaseNo(@PathVariable String purchaseOrderNo) {
    return salesAgencyService.purchasePayment(purchaseOrderNo);
  }

  /**
   * 根据token获取用户id,门店id和经销商id
   */
  @LogAnnotation
  @GetMapping("/getUserByToken")
  public ResponseData getUserByToken() {
    Long userId = jsonTokenUtil.getCurrentUserId();
    SalesUser salesUser = salesUserService.selectById(userId);
    //返回token
    SalesUserCompose salesUserCompose = new SalesUserCompose();
    //返回给前台时去掉密码
    salesUser.setPassword(null);
    salesUserCompose.setSalesUser(salesUser);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage(),salesUserCompose);
  }

  /**
   * 修改个人密码
   */
  @LogAnnotation
  @PutMapping("/editUserPwd")
  public ResponseData editUserPwd(@RequestBody SalesEditUserPasswordRequest request) {
    return salesUserService.editUserPwd(request);
  }

}
