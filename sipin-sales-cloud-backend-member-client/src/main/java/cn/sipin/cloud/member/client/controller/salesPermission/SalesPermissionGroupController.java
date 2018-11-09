/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.client.controller.salesPermission;

import com.baomidou.mybatisplus.plugins.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.sipin.cloud.member.client.service.salesPermission.SalesPermissionGroupService;
import cn.sipin.cloud.member.pojo.response.salesPermission.permissionGroup.indexAction.SalesPermissionActionResponse;
import cn.siyue.platform.base.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 经销商端后台权限组管理
 */
@Api(tags = "经销商端_后台_权限组管理接口")
@RestController
@RequestMapping(path = "/sales/permission/group", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SalesPermissionGroupController {

  private SalesPermissionGroupService salesPermissionGroupService;

  @Autowired
  public SalesPermissionGroupController(SalesPermissionGroupService salesPermissionGroupService){
    this.salesPermissionGroupService = salesPermissionGroupService;
  }

  /**
   * 获取前台权限组列表
   */
  @ApiOperation(nickname = "salesPermissionGroupIndexFrontGroup",value = "获取前台权限组列表接口")
  @GetMapping("/front/indexFrontGroup")
  public ResponseData indexFrontGroup(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size) {
      return salesPermissionGroupService.indexFrontGroup(page,size);
  }

  /**
   * 获取后台权限组列表
   */
  @ApiOperation(nickname = "salesPermissionGroupIndexBackGroup",value = "获取后台权限组列表接口")
  @GetMapping("/back/indexBackGroup")
  public ResponseData indexBackGroup(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size) {
    return salesPermissionGroupService.indexBackGroup(page, size);
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
