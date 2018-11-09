/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.client.controller.region;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.sipin.cloud.member.client.service.region.RegionService;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 获取中国所有省市区信息
 */
@Api(tags = "省市地区信息查询接口")
@RequestMapping(path = "/member/region", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class RegionController {

  @Autowired
  private RegionService regionService;

  /**
   * 获取中国所有省市区信息
   */
  @ApiOperation(nickname = "regionIndex",value = "获取中国所有省市区信息列表接口", httpMethod = "GET")
  @GetMapping("/index")
  public ResponseData index() {
    try {
      return regionService.index();
    } catch (Exception e) {
      return ResponseData.build(
          ResponseBackCode.ERROR_NOT_FOUND.getValue(),
          ResponseBackCode.ERROR_NOT_FOUND.getMessage()
      );
    }
  }
}
