/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.client.service.sales.front;

import com.baomidou.mybatisplus.plugins.Page;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import cn.sipin.cloud.member.client.callback.sales.front.SalesFrontServiceFallBack;
import cn.sipin.cloud.member.pojo.request.sales.front.PurchasePaymentRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.SalesFrontUserLoginRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.editUserPwd.SalesEditUserPasswordRequest;
import cn.sipin.cloud.member.pojo.response.sales.backend.user.indexUser.IndexUserResponse;
import cn.siyue.platform.base.ResponseData;
import feign.Headers;

@FeignClient(name = "sales-member-service",path = "/sales/front", fallback = SalesFrontServiceFallBack.class)
public interface SalesFrontService {

  @RequestMapping(value = "/searchShop", method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData searchShop(
      @RequestParam(value = "type") String type, @RequestParam(value = "page", required = false) int page, @RequestParam(value = "size", required = false) int
      size
  );

  @RequestMapping(value = "/userLogin", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData userLogin(@RequestBody SalesFrontUserLoginRequest request);

  @RequestMapping(value = "/getAgencyInfo", method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData getAgencyInfo();

  @RequestMapping(value = "/indexUser", method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData<Page<IndexUserResponse>> indexUser();

  @RequestMapping(value = "/purchasePayment", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData purchasePayment(PurchasePaymentRequest request);

  @RequestMapping(value = "/getPaymentNo/{purchaseOrderNo}", method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData getPaymentNoByPurchaseNo(@PathVariable(value = "purchaseOrderNo") String purchaseOrderNo);

  @RequestMapping(value = "/getUserByToken", method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData getUserByToken();

  @RequestMapping(value = "/editUserPwd", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData editUserPwd(SalesEditUserPasswordRequest request);
}
