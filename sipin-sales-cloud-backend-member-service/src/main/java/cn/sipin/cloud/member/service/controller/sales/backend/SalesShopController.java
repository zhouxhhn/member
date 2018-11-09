/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.service.controller.sales.backend;

import com.baomidou.mybatisplus.plugins.Page;

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

import cn.sipin.cloud.member.pojo.request.sales.backend.agency.updateOuterCode.SalesUpdateOuterCodeRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.shop.addShop.AddShopRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.shop.index.IndexShopRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.shop.updateShop.UpdateShopRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.shop.updateSourceIdRequest.SalesUpdateSourceIdRequest;
import cn.sipin.cloud.member.pojo.response.sales.backend.shop.index.IndexShopResponse;
import cn.sipin.cloud.member.service.service.sales.SalesShopServiceContract;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.httplog.annotation.LogAnnotation;

/**
 * 经销商门店管理
 */
@RequestMapping(path = "/sales/shop", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class SalesShopController {

  private SalesShopServiceContract salesShopService;

  @Autowired
  public SalesShopController(SalesShopServiceContract salesShopService){
    this.salesShopService = salesShopService;
  }

  /**
   * 获取指定经销商门店列表
   * @param page 页码
   * @param size 每页显示页码的大小
   * @return 角色列表信息
   */
  @LogAnnotation
  @PostMapping("/index")
  public ResponseData<Page<IndexShopResponse>> index(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size,
      @RequestBody IndexShopRequest request) {
     return salesShopService.index(page,size,request);
  }

  /**
   * 查找单个门店信息
   * @param shopId 门店id
   * @return 返回查找信息
   */
  @LogAnnotation
  @GetMapping("/searchShop/{shopId}")
  public ResponseData searchShop(@PathVariable Long shopId) {
    return salesShopService.searchShop(shopId);
  }

  /**
   * 新增门店信息
   * @param addShopRequest 门店信息
   * @return 生成信息返回
   */
  @LogAnnotation
  @PostMapping("/addShop")
  public ResponseData addShop(@RequestBody AddShopRequest addShopRequest) {
    return salesShopService.addShop(addShopRequest);
  }

  /**
   * 更新门店信息
   *
   * @param updateShopRequest 门店信息
   * @return 更新信息返回
   */
  @LogAnnotation
  @PutMapping("/updateShop/{shopId}")
  public ResponseData updateShop(@PathVariable Long shopId,@RequestBody UpdateShopRequest updateShopRequest) {
    return salesShopService.updateShop(shopId,updateShopRequest);
  }

  /**
   * 删除门店信息
   *
   * @param shopId 门店id
   * @return 返回删除信息
   */
  @LogAnnotation
  @PutMapping("/deleteShop/{shopId}")
  public ResponseData deleteShop(@PathVariable Long shopId) {
    return salesShopService.deleteShop(shopId);
  }

  /**
   * 修改门店sourceId
   * @param id 门店id
   */
  @LogAnnotation
  @PutMapping("/updateSourceId/{id}")
  public ResponseData updateSourceId(@PathVariable Long id, @RequestBody SalesUpdateSourceIdRequest request) {
    return salesShopService.updateSourceId(id,request);
  }

}
