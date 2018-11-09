/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.service.controller.salesPermission.front;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.sipin.cloud.member.pojo.pojo.salesPermission.SalesPermissionGroup;
import cn.sipin.cloud.member.pojo.response.salesPermission.permissionGroup.indexAction.SalesPermissionActionResponse;
import cn.sipin.cloud.member.service.service.salesPermission.SalesPermissionActionServiceContract;
import cn.sipin.cloud.member.service.service.salesPermission.SalesPermissionGroupServiceContract;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import cn.siyue.platform.httplog.annotation.LogAnnotation;

/**
 * 经销商端后台权限组管理
 */
@RestController
@RequestMapping(path = "/sales/front/permission/group", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SalesFrontPermissionGroupController {

  private SalesPermissionGroupServiceContract salesPermissionGroupService;

  private SalesPermissionActionServiceContract salesPermissionActionService;

  @Autowired
  public SalesFrontPermissionGroupController(SalesPermissionGroupServiceContract salesPermissionGroupService,
                                             SalesPermissionActionServiceContract salesPermissionActionService){
    this.salesPermissionGroupService = salesPermissionGroupService;
    this.salesPermissionActionService = salesPermissionActionService;
  }

  /**
   * 前台查找权限组列表
   */
  @LogAnnotation
  @GetMapping("/indexFrontGroup")
  public ResponseData<Page<SalesPermissionGroup>> indexFrontGroup(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size) {
    Page userPage = new Page<SalesPermissionGroup>(page, size);
    userPage.setAsc(false);
    userPage = salesPermissionGroupService.selectPage(userPage,new EntityWrapper<SalesPermissionGroup>().eq("type", 0));
    return new ResponseData<>(ResponseBackCode.SUCCESS.getValue(), ResponseBackCode.SUCCESS.getMessage(), userPage);
  }


  /**
   * 查找指定权限组对应的权限列表
   */
  @LogAnnotation
  @GetMapping("/indexAction/{roleId}/{groupId}")
  public ResponseData<Page<SalesPermissionActionResponse>> indexAction(@PathVariable Long roleId, @PathVariable Long groupId) {
      return salesPermissionActionService.indexAction(roleId,groupId);
  }

}
