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
import cn.sipin.cloud.member.client.service.sales.SalesShopService;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.updateOuterCode.SalesUpdateOuterCodeRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.shop.addShop.AddShopRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.shop.index.IndexShopRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.shop.updateShop.UpdateShopRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.shop.updateSourceIdRequest.SalesUpdateSourceIdRequest;
import cn.sipin.cloud.member.pojo.response.sales.backend.shop.index.IndexShopResponse;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 门店消费者
 */

@Api(tags = "经销商_后台_门店管理接口")
@RequestMapping(path = "/sales/shop", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class SalesShopController {

  private SalesShopService salesShopService;

  @Autowired
  public SalesShopController(SalesShopService salesShopService){
    this.salesShopService = salesShopService;
  }

  /**
   * 获取所有门店列表
   *
   * @param page 页码
   * @param size 每页显示页码的大小
   * @return 角色列表信息
   */
  @ApiOperation(nickname = "salesShopIndexShop",value = "获取指定经销商门店列表")
  @GetMapping("/indexShop")
  public ResponseData<Page<IndexShopResponse>> indexShop(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size,
      @Valid IndexShopRequest request, BindingResult result
  ) {
    if (result.hasErrors()) {
      return new ResponseData<>(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),result.getFieldError().getDefaultMessage());
    }
    return salesShopService.index( page, size, request);
  }

  /**
   * 查找单个门店
   *
   * @param shopId 门店id
   * @return 返回查找信息
   */
  @ApiOperation(nickname = "salesShopSearchShop",value = "获取单个门店信息")
  @GetMapping("/searchShop/{shopId}")
  public ResponseData searchShop(@PathVariable Long shopId) {
    return salesShopService.searchShop(shopId);
  }

  /**
   * 门店端代码的生成
   *
   * @param addShopRequest 门店信息
   * @return 生成信息返回
   */
  @ApiOperation(nickname = "salesShopAddShop",value = "新增门店信息")
  @PostMapping("/addShop")
  public ResponseData addShop(@RequestBody @Valid AddShopRequest addShopRequest, BindingResult result) {
    //请求的数据参数格式不正确
    if (result.hasErrors()) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage(),result.getAllErrors()
      );
    }
    return salesShopService.addShop(addShopRequest);
  }

  /**
   * 更新门店信息
   *
   * @param updateShopRequest 门店信息
   * @return 更新信息返回
   */
  @ApiOperation(nickname = "salesShopUpdateShop",value = "修改门店信息")
  @PutMapping("/updateShop/{shopId}")
  public ResponseData updateShop(@PathVariable Long shopId, @RequestBody @Valid UpdateShopRequest updateShopRequest, BindingResult result) {
    //请求的数据参数格式不正确
    if (result.hasErrors()) {
      return ResponseData.build(ResponseBackCode.ERROR_PARAM_INVALID.getValue(),ResponseBackCode.ERROR_PARAM_INVALID.getMessage(), result.getAllErrors());
    }
    return salesShopService.updateShop(shopId,updateShopRequest);
  }

  /**
   * 删除门店
   *
   * @param shopId 门店id
   * @return 返回删除信息
   */
  @ApiOperation(nickname = "SalesShopDeleteShop",value = "删除门店信息")
  @PutMapping("/deleteShop/{shopId}")
  public ResponseData deleteShop(@PathVariable Long shopId) {
    return salesShopService.deleteShop(shopId);
  }

  /**
   * 修改门店sourceId
   * @param id 门店id
   */
  @ApiOperation(nickname = "salesAgencyUpdateSourceId",value = "修改门店sourceId")
  @PutMapping("/updateSourceId/{id}")
  public ResponseData  updateSourceId(@PathVariable Long id, @RequestBody @Valid SalesUpdateSourceIdRequest request
      , BindingResult result) {
    //请求的数据参数格式不正确
    if (result.hasErrors()) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage(),result.getAllErrors()
      );
    }
    return salesShopService.updateSourceId(id,request);
  }
}
