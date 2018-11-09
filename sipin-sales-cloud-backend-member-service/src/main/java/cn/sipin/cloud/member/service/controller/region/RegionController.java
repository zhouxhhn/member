/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.service.controller.region;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import cn.sipin.cloud.member.pojo.pojo.region.Region;
import cn.sipin.cloud.member.service.service.region.RegionServiceContract;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import cn.siyue.platform.httplog.annotation.LogAnnotation;

/**
 * 获取中国所有省市区信息
 */
@RequestMapping(path = "/member/region", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class RegionController {

  private RegionServiceContract regionService;

  @Autowired
  public RegionController(RegionServiceContract regionService){
    this.regionService = regionService;
  }

  /**
   * 获取中国所有省市区信息
   */
  @LogAnnotation
  @GetMapping("/index")
  public ResponseData index() {
    try {
      //查找所有信息
      List<Region> regionList = regionService.selectList(new EntityWrapper<>());
      List<Region> treeRegionList = regionService.build(regionList);
      return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),
          ResponseBackCode.SUCCESS.getMessage(),treeRegionList);
    } catch (Exception e) {
      return ResponseData.build(
          ResponseBackCode.ERROR_NOT_FOUND.getValue(),
          ResponseBackCode.ERROR_NOT_FOUND.getMessage()
      );
    }
  }
}
