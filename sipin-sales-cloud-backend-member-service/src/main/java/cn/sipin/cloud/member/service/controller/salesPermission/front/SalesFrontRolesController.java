/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.service.controller.salesPermission.front;

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
import cn.sipin.cloud.member.pojo.pojo.salesPermission.SalesRole;
import cn.sipin.cloud.member.pojo.pojo.salesUser.SalesUser;
import cn.sipin.cloud.member.pojo.request.salesPermission.frontRoles.add.AddSalesFrontRolesRequest;
import cn.sipin.cloud.member.pojo.request.salesPermission.frontRoles.update.UpdateSalesFrontRolesRequest;
import cn.sipin.cloud.member.pojo.request.salesPermission.rolesPermission.setRolePermission.SalesRolePermissionSettingRequest;
import cn.sipin.cloud.member.service.service.sales.SalesUserServiceContract;
import cn.sipin.cloud.member.service.service.salesPermission.SalesPermissionRoleServiceContract;
import cn.sipin.cloud.member.service.service.salesPermission.SalesRoleServiceContract;
import cn.sipin.cloud.member.service.util.JsonTokenUtil;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import cn.siyue.platform.httplog.annotation.LogAnnotation;

/**
 * 经销商端前台角色管理
 */
@RestController
@RequestMapping(path = "/sales/front/roles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SalesFrontRolesController {

  private SalesRoleServiceContract salesRoleService;

  private SalesPermissionRoleServiceContract salesPermissionRoleService;

  private SalesUserServiceContract salesUserService;

  private JsonTokenUtil jsonTokenUtil;

  @Autowired
  public SalesFrontRolesController(SalesRoleServiceContract salesRoleService, SalesUserServiceContract salesUserService,
                                   SalesPermissionRoleServiceContract salesPermissionRoleService,
                                   JsonTokenUtil jsonTokenUtil){
    this.salesRoleService = salesRoleService;
    this.salesPermissionRoleService = salesPermissionRoleService;
    this.salesUserService = salesUserService;
    this.jsonTokenUtil = jsonTokenUtil;
  }

  /**
   * 查找所有角色列表
   */
  @LogAnnotation
  @GetMapping("/index")
  public ResponseData<Page<SalesRole>> index(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size) {
      return salesRoleService.frontIndex(page,size);
  }

  /**
   * 查找单个角色
   */
  @LogAnnotation
  @GetMapping("/search/{roleId}")
  public ResponseData search(@PathVariable Long roleId) {
    return salesRoleService.search(roleId);
  }

  /**
   * 新增角色
   */
  @LogAnnotation
  @PostMapping("/add")
  public ResponseData add(@RequestBody AddSalesFrontRolesRequest request) {
    return salesRoleService.frontAddRoles(request);
  }

  /**
   * 修改角色
   */
  @LogAnnotation
  @PutMapping("/update/{roleId}")
  public ResponseData update(@PathVariable Long roleId,@RequestBody UpdateSalesFrontRolesRequest request) {
    return salesRoleService.frontUpdate(roleId,request);
  }

  /**
   * 删除角色
   */
  @LogAnnotation
  @PutMapping("/delete/{roleId}")
  public ResponseData delete(@PathVariable Long roleId) {
    return salesRoleService.delete(roleId);
  }

  /**
   * 角色授权
   */
  @LogAnnotation
  @PutMapping("/setRolePermission/{roleId}")
  public ResponseData setRolePermission(@PathVariable Long roleId,@RequestBody SalesRolePermissionSettingRequest request) {
    SalesRole salesRole = salesRoleService.selectById(roleId);
    if(salesRole == null){
      return ResponseData.build(ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getValue(),"找不到角色，不能进行绑定权限");
    }
    Long userId = jsonTokenUtil.getCurrentUserId();
    SalesUser salesUser = salesUserService.selectById(userId);
    return salesPermissionRoleService.setRolePermission(roleId,salesRole,salesUser,request);
  }
}
