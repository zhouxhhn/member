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
import cn.sipin.cloud.member.client.callback.sales.SalesAgencyServiceFallBack;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.addAgency.AddAgencyRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.indexAgency.IndexAgencyRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.updateAgency.UpdateAgencyRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.updateBalance.SalesUpdateBalanceRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.updateOuterCode.SalesUpdateOuterCodeRequest;
import cn.sipin.cloud.member.pojo.response.sales.backend.agency.index.IndexSalesAgencyResponse;
import cn.sipin.cloud.member.pojo.response.sales.backend.agency.indexAgency.SalesAgencyIndexResponse;
import cn.siyue.platform.base.ResponseData;

/**
 * 调用会员管理服务生产者的接口
 */
@FeignClient(name = "sales-member-service", fallback = SalesAgencyServiceFallBack.class)
public interface SalesAgencyService {

  @RequestMapping(value = "/sales/agency/indexAgency", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData<Page<SalesAgencyIndexResponse>> indexAgency(@RequestParam("page") int page, @RequestParam("size") int size, IndexAgencyRequest indexAgencyRequest);

  @RequestMapping(value = "/sales/agency/index", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData<Page<IndexSalesAgencyResponse>> index();

  @RequestMapping(value = "/sales/agency/searchAgency/{agencyId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData searchAgency(@PathVariable(value = "agencyId") Long agencyId);

  @RequestMapping(value = "/sales/agency/deleteAgency/{agencyId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData deleteAgency(@PathVariable(value = "agencyId") Long agencyId);

  @RequestMapping(value = "/sales/agency/addAgency", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData addAgency(AddAgencyRequest addAgencyRequest);

  @RequestMapping(value = "/sales/agency/updateAgency/{agencyId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData updateAgency(@PathVariable(value = "agencyId") Long agencyId, UpdateAgencyRequest updateAgencyRequest);

  @RequestMapping(value = "/sales/agency/updateBalance/{agencyId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData updateBalance(@PathVariable(value = "agencyId") Long agencyId, SalesUpdateBalanceRequest request);

  @RequestMapping(value = "/sales/agency/getBalance/{agencyId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData getBalance(@PathVariable(value = "agencyId") Long agencyId, @RequestParam("page") int page, @RequestParam("size") int size);

  @RequestMapping(value = "/sales/agency/updateOuterCode/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData updateOuterCode(@PathVariable(value = "id") Long id, SalesUpdateOuterCodeRequest request);

  @RequestMapping(value = "/sales/agency/getDiscount/{agencyCode}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData getDiscount(@PathVariable(value = "agencyCode") String agencyCode);

}
