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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import cn.sipin.cloud.member.client.service.sales.SalesAgencyService;
import cn.sipin.cloud.member.pojo.pojo.salesAgency.SalesAgency;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.addAgency.AddAgencyRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.indexAgency.IndexAgencyRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.updateAgency.UpdateAgencyRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.updateBalance.SalesUpdateBalanceRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.updateOuterCode.SalesUpdateOuterCodeRequest;
import cn.sipin.cloud.member.pojo.response.sales.backend.agency.index.IndexSalesAgencyResponse;
import cn.sipin.cloud.member.pojo.response.sales.backend.agency.indexAgency.SalesAgencyIndexResponse;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import cn.siyue.platform.httplog.annotation.LogAnnotation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 经销商管理
 */
@RestController
@Api(tags = "经销商端_后台_经销商的管理接口")
@RequestMapping(path = "/sales/agency/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SalesAgencyController {

  private SalesAgencyService salesAgencyService;

  @Autowired
  public SalesAgencyController(SalesAgencyService salesAgencyService){
    this.salesAgencyService = salesAgencyService;
  }

  /**
   * 获取所有经销商列表
   *
   * @param page 页码
   * @param size 每页显示页码的大小
   * @return 角色列表信息
   */
  @ApiOperation(nickname = "salesAgencyIndexAgency",value = "获取所有经销商列表")
  @GetMapping("/indexAgency")
  public ResponseData<Page<SalesAgencyIndexResponse>> indexAgency(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size,
      @Valid IndexAgencyRequest indexAgencyRequest, BindingResult result
  ) {
    if (result.hasErrors()) {
      return new ResponseData<>(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),result.getFieldError().getDefaultMessage());
    }
    return salesAgencyService.indexAgency(page, size,indexAgencyRequest);
  }

  /**
   * 内部获取获取所有经销商列表
   */
  @ApiOperation(nickname = "salesAgencyIndex",value = "内部获取所有经销商列表")
  @GetMapping("/index")
  public ResponseData<Page<IndexSalesAgencyResponse>>  index() {
    return salesAgencyService.index();
  }

  /**
   * 查找单个经销商
   *
   * @param agencyId 经销商id
   * @return 返回查找信息
   */
  @ApiOperation(nickname = "salesAgencySearchAgency",value = "查找单个经销商")
  @GetMapping("/searchAgency/{agencyId}")
  public ResponseData searchAgency(@PathVariable Long agencyId) {
    return salesAgencyService.searchAgency(agencyId);
  }

  /**
   * 经销商端的生成
   *
   * @param addAgencyRequest 经销商信息
   * @return 生成信息返回
   */
  @ApiOperation(nickname = "salesAgencyAddAgency",value = "新增经销商")
  @PostMapping("/addAgency")
  public ResponseData addAgency(@RequestBody @Valid AddAgencyRequest addAgencyRequest, BindingResult result
  ) {
    //请求的数据参数格式不正确
    if (result.hasErrors()) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage(),result.getAllErrors()
      );
    }
    return salesAgencyService.addAgency(addAgencyRequest);
  }

  /**
   * 更新经销商端信息
   *
   * @param updateAgencyRequest 经销商信息
   * @return 更新信息返回
   */
  @ApiOperation(nickname = "salesAgencyUpdateAgency",value = "修改经销商")
  @PutMapping("/updateAgency/{agencyId}")
  public ResponseData updateAgency(@PathVariable Long agencyId,
                                   @RequestBody @Valid UpdateAgencyRequest updateAgencyRequest,
                                   BindingResult result) {
    //请求的数据参数格式不正确
    if (result.hasErrors()) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage(),result.getAllErrors()
      );
    }
    return salesAgencyService.updateAgency(agencyId,updateAgencyRequest);
  }

  /**
   * 删除经销商
   *
   * @param agencyId 经销商id
   * @return 返回删除信息
   */
  @ApiOperation(nickname = "salesAgencyDeleteAgency",value = "删除经销商")
  @PutMapping("/deleteAgency/{agencyId}")
  public ResponseData deleteAgency(@PathVariable Long agencyId) {
    return salesAgencyService.deleteAgency(agencyId);
  }

  /**
   * 修改余额
   * @param agencyId 经销商id
   */
  @ApiOperation(nickname = "salesAgencyUpdateBalance",value = "修改余额")
  @PutMapping("/updateBalance/{agencyId}")
  public ResponseData updateBalance(@PathVariable Long agencyId,
                                    @RequestBody @Valid SalesUpdateBalanceRequest request, BindingResult result) {
    //请求的数据参数格式不正确
    if (result.hasErrors()) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage(),result.getAllErrors()
      );
    }
    return salesAgencyService.updateBalance(agencyId,request);
  }

  /**
   * 获取所有经销商列表
   *
   * @param page 页码
   * @param size 每页显示页码的大小
   * @return 角色列表信息
   */
  @ApiOperation(nickname = "salesAgencyGetBalance",value = "获取指定经销商的余额明细")
  @GetMapping("/getBalance/{agencyId}")
  public ResponseData  getBalance(@PathVariable Long agencyId,
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size
  ) {
    return salesAgencyService.getBalance(agencyId,page, size);
  }


  /**
   * 修改经销商外部编码
   * @param id 经销商id
   */
  @ApiOperation(nickname = "salesAgencyUpdateOuterCode",value = "修改经销商外部编码")
  @PutMapping("/updateOuterCode/{id}")
  public ResponseData  updateOuterCode(@PathVariable Long id, @RequestBody @Valid SalesUpdateOuterCodeRequest request
      , BindingResult result) {
    //请求的数据参数格式不正确
    if (result.hasErrors()) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage(),result.getAllErrors()
      );
    }
    return salesAgencyService.updateOuterCode(id,request);
  }

  /**
   * 查询折扣
   */
  @ApiOperation(nickname = "salesAgencyGetDiscount",value = "根据经销商code查询折扣")
  @GetMapping("/getDiscount/{agencyCode}")
  public ResponseData getDiscount(@PathVariable String agencyCode) {
    return salesAgencyService.getDiscount(agencyCode);
  }
}
