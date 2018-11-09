/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.client.service.sales.front;

import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import cn.sipin.cloud.member.client.callback.sales.front.SalesAgencyFrontDeliveryServiceFallBack;
import cn.sipin.cloud.member.pojo.request.sales.backend.delivery.updateAddress.UpdateSalesAgencyDeliveryRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.delivery.addAddress.SaveSalesAgencyFrontDeliveryRequest;
import cn.sipin.cloud.member.pojo.response.sales.backend.delivery.SalesAgencyDeliveryResponse;
import cn.siyue.platform.base.ResponseData;

/**
 * 调用会员管理服务生产者的接口
 */
@FeignClient(name = "sales-member-service", path = "/sales/agency/front/delivery",fallback = SalesAgencyFrontDeliveryServiceFallBack.class)
public interface SalesAgencyFrontDeliveryService {

  @RequestMapping(value = "/addAddress", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData addAddress(SaveSalesAgencyFrontDeliveryRequest saveSalesAgencyFrontDeliveryRequest);

  @RequestMapping(value = "/updateAddress/{addressId}", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData updateAddress(@PathVariable(value = "addressId") Long addressId, UpdateSalesAgencyDeliveryRequest updateSalesAgencyDeliveryRequest);

  @RequestMapping(value = "/setDefaultAddress/{addressId}", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData setDefaultAddress(@PathVariable(value = "addressId") Long addressId);

  @RequestMapping(value = "/deleteAddress/{addressId}", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData deleteAddress(@PathVariable(value = "addressId") Long addressId);

  @RequestMapping(value = "/searchAddress/{addressId}", method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData<SalesAgencyDeliveryResponse> searchAddress(@PathVariable(value = "addressId") Long addressId);

  @RequestMapping(value = "/index", method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData<Page> index(@RequestParam("page") int page, @RequestParam("size") int size);


}
