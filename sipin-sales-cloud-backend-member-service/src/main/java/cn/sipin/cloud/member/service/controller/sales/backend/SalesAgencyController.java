/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.service.controller.sales.backend;

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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.sipin.cloud.member.pojo.constants.MemberConstants;
import cn.sipin.cloud.member.pojo.pojo.salesAgency.SalesAgency;
import cn.sipin.cloud.member.pojo.pojo.salesAgency.SalesAgencyAccountHistory;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.addAgency.AddAgencyRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.indexAgency.IndexAgencyRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.updateAgency.UpdateAgencyRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.updateBalance.SalesUpdateBalanceRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.updateOuterCode.SalesUpdateOuterCodeRequest;
import cn.sipin.cloud.member.pojo.response.sales.backend.agency.index.IndexSalesAgencyResponse;
import cn.sipin.cloud.member.pojo.response.sales.backend.agency.indexAgency.SalesAgencyIndexResponse;
import cn.sipin.cloud.member.pojo.response.sales.backend.agency.searchAgency.SalesAgencyResponse;
import cn.sipin.cloud.member.service.service.sales.SalesAgencyAccountHistoryServiceContract;
import cn.sipin.cloud.member.service.service.sales.SalesAgencyServiceContract;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import cn.siyue.platform.httplog.annotation.LogAnnotation;

/**
 * 经销商管理
 */
@RequestMapping(path = "/sales/agency", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class SalesAgencyController {

  private SalesAgencyServiceContract salesAgencyService;

  private SalesAgencyAccountHistoryServiceContract salesAgencyAccountHistory;

  @Autowired
  public SalesAgencyController(SalesAgencyServiceContract salesAgencyService,
                               SalesAgencyAccountHistoryServiceContract salesAgencyAccountHistory){
    this.salesAgencyService = salesAgencyService;
    this.salesAgencyAccountHistory = salesAgencyAccountHistory;
  }

  /**
   * 获取所有经销商列表
   *
   * @param page 页码
   * @param size 每页显示页码的大小
   * @return 角色列表信息
   */
  @LogAnnotation
  @PostMapping("/indexAgency")
  public ResponseData<Page<SalesAgencyIndexResponse>> indexAgency(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size,
      @RequestBody IndexAgencyRequest indexAgencyRequest) {
    Page<SalesAgencyIndexResponse> responsePage = new Page<>(page, size);
    responsePage.setAsc(false);
    return  salesAgencyService.selectAgencyPage(responsePage, indexAgencyRequest);
  }

  /**
   * 内部获取所有经销商列表
   */
  @LogAnnotation
  @GetMapping("/index")
  public ResponseData<Page<IndexSalesAgencyResponse>> index() {
    Page<IndexSalesAgencyResponse> responsePage = new Page<>(MemberConstants.AGENCY_PAGE, MemberConstants.AGENCY_SIZE);
    responsePage.setAsc(false);
    List<SalesAgency> salesAgencyList =  salesAgencyService.selectList(new EntityWrapper<>());
    List<IndexSalesAgencyResponse> responses = new ArrayList<>();
    IndexSalesAgencyResponse response;
    if(salesAgencyList != null && salesAgencyList.size() > 0){
      for (SalesAgency salesAgency: salesAgencyList) {
        response = new IndexSalesAgencyResponse();
        BeanUtils.copyProperties(salesAgency,response);
        responses.add(response);
      }
    }
    responsePage.setTotal(responses.size());
    responsePage.setRecords(responses);
    return new ResponseData<>(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage(),responsePage);
  }

  /**
   * 查找单个经销商
   *
   * @param agencyId 经销商id
   * @return 返回查找信息
   */
  @LogAnnotation
  @GetMapping("/searchAgency/{agencyId}")
  public ResponseData searchAgency(@PathVariable Long agencyId) {

    SalesAgency salesAgency = salesAgencyService.selectById(agencyId);
    SalesAgencyResponse response = new SalesAgencyResponse();
    BeanUtils.copyProperties(salesAgency, response);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage(),response);
  }

  /**
   * 添加经销商
   *
   * @param addAgencyRequest 经销商信息
   * @return 生成信息返回
   */
  @LogAnnotation
  @PostMapping("/addAgency")
  public ResponseData addAgency(@RequestBody AddAgencyRequest addAgencyRequest) {
    return salesAgencyService.addAgency(addAgencyRequest);
  }

  /**
   * 更新经销商端信息
   *
   * @param updateAgencyRequest 经销商信息
   * @return 更新信息返回
   */
  @LogAnnotation
  @PutMapping("/updateAgency/{agencyId}")
  public ResponseData updateAgency(@PathVariable Long agencyId,@RequestBody UpdateAgencyRequest updateAgencyRequest) {
    return salesAgencyService.updateAgency(agencyId,updateAgencyRequest);

  }

  /**
   * 删除经销商
   * @param agencyId 经销商id
   */
  @LogAnnotation
  @PutMapping("/deleteAgency/{agencyId}")
  public ResponseData deleteAgency(@PathVariable Long agencyId) {
    salesAgencyService.deleteById(agencyId);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage());
  }

  /**
   * 修改余额
   * @param agencyId 经销商id
   */
  @LogAnnotation
  @PutMapping("/updateBalance/{agencyId}")
  public ResponseData updateBalance(@PathVariable Long agencyId,
                                    @RequestBody SalesUpdateBalanceRequest request) {
    return salesAgencyService.updateBalance(agencyId,request);
  }

  /**
   * 查询余额明细
   */
  @LogAnnotation
  @GetMapping("/getBalance/{agencyId}")
  public ResponseData getBalance(@PathVariable Long agencyId,
                                 @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                 @RequestParam(value = "size", required = false, defaultValue = "15") int size) {
    Page<SalesAgencyAccountHistory> responsePage = new Page<>(page, size);
    responsePage.setAsc(false);
    responsePage = salesAgencyAccountHistory.selectPage(
        responsePage, new EntityWrapper<SalesAgencyAccountHistory>().eq("agency_id", agencyId));

    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage(),responsePage);

  }

  /**
   * 修改经销商端外部编码
   * @param id 经销商id
   */
  @LogAnnotation
  @PutMapping("/updateOuterCode/{id}")
  public ResponseData updateOuterCode(@PathVariable Long id,
                                    @RequestBody SalesUpdateOuterCodeRequest request) {
    return salesAgencyService.updateOuterCode(id,request);
  }

  /**
   * 查询折扣
   */
  @LogAnnotation
  @GetMapping("/getDiscount/{agencyCode}")
  public ResponseData getDiscount(@PathVariable String agencyCode) {
    Map<String,Object> map = new HashMap<>();
    map.put("code",agencyCode);
    List<SalesAgency> list =  salesAgencyService.selectByMap(map);
    if(list == null || list.isEmpty()){
      return ResponseData.build(ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getValue(),"经销商不存在");
    }
    SalesAgency agency = list.get(0);
    BigDecimal discount = agency.getDiscount();
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage(),discount);
  }
}
