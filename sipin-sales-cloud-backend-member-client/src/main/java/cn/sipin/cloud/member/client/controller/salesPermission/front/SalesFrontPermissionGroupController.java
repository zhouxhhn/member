/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.client.controller.salesPermission.front;

import com.baomidou.mybatisplus.plugins.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.sipin.cloud.member.client.service.salesPermission.SalesPermissionGroupService;
import cn.sipin.cloud.member.client.service.salesPermission.front.SalesFrontPermissionGroupService;
import cn.sipin.cloud.member.client.service.salesPermission.front.SalesFrontRolesService;
import cn.sipin.cloud.member.pojo.pojo.salesPermission.SalesPermissionGroup;
import cn.sipin.cloud.member.pojo.response.salesPermission.permissionGroup.indexAction.SalesPermissionActionResponse;
import cn.siyue.platform.base.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 经销商端后台权限组管理
 */
@Api(tags = "经销商端_前台_权限组管理接口")
@RestController
@RequestMapping(path = "/sales/front/permission/group", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SalesFrontPermissionGroupController {

  private SalesFrontPermissionGroupService salesPermissionGroupService;

  @Autowired
  public SalesFrontPermissionGroupController(SalesFrontPermissionGroupService salesPermissionGroupService){
    this.salesPermissionGroupService = salesPermissionGroupService;
  }

  /**
   * 获取前台权限组列表
   */
  @ApiOperation(nickname = "salesPermissionGroupIndexFrontGroup",value = "获取前台权限组列表接口")
  @GetMapping("/indexFrontGroup")
  public ResponseData<Page<SalesPermissionGroup>> indexFrontGroup(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size) {
      return salesPermissionGroupService.indexFrontGroup(page,size);
  }


  /**
   * 获取后台权限组列表
   */
  @ApiOperation(nickname = "salesPermissionGroupIndexAction",value = "获取指定角色权限组的权限列表接口")
  @GetMapping("/indexAction/{roleId}/{groupId}")
  public ResponseData<Page<SalesPermissionActionResponse>>indexAction(@PathVariable Long roleId, @PathVariable Long groupId) {
    return salesPermissionGroupService.indexAction(roleId,groupId);
  }
}
