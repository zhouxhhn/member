/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.client.service.sales;

import com.baomidou.mybatisplus.plugins.Page;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import cn.sipin.cloud.member.client.callback.sales.SalesShopServiceFallBack;
import cn.sipin.cloud.member.pojo.request.sales.backend.shop.addShop.AddShopRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.shop.index.IndexShopRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.shop.updateShop.UpdateShopRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.shop.updateSourceIdRequest.SalesUpdateSourceIdRequest;
import cn.sipin.cloud.member.pojo.response.sales.backend.shop.index.IndexShopResponse;
import cn.siyue.platform.base.ResponseData;

/**
 * 调用会员管理服务生产者的接口
 */
@FeignClient(name = "sales-member-service",path = "/sales/shop",fallback = SalesShopServiceFallBack.class)
public interface SalesShopService {

  @RequestMapping(value = "/index", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData<Page<IndexShopResponse>> index(@RequestParam("page") int page, @RequestParam("size") int size, IndexShopRequest request);

  @RequestMapping(value = "/searchShop/{shopId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData searchShop(@PathVariable(value = "shopId") Long shopId);

  @RequestMapping(value = "/deleteShop/{shopId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData deleteShop(@PathVariable(value = "shopId") Long shopId);

  @RequestMapping(value = "/addShop", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData addShop(AddShopRequest addShopRequest);

  @RequestMapping(value = "/updateShop/{shopId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData updateShop(@PathVariable(value = "shopId") Long shopId, UpdateShopRequest updateShopRequest);

  @RequestMapping(value = "/updateSourceId/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData updateSourceId(@PathVariable(value = "id") Long shopId, SalesUpdateSourceIdRequest request);
}
