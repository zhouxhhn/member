/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.client.controller.salesPermission;

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
import cn.sipin.cloud.member.client.service.salesPermission.SalesRolesService;
import cn.sipin.cloud.member.pojo.pojo.salesPermission.SalesRole;
import cn.sipin.cloud.member.pojo.request.salesPermission.roles.add.AddSalesRolesRequest;
import cn.sipin.cloud.member.pojo.request.salesPermission.roles.index.IndexSalesRolesRequest;
import cn.sipin.cloud.member.pojo.request.salesPermission.roles.update.UpdateSalesRolesRequest;
import cn.sipin.cloud.member.pojo.request.salesPermission.rolesPermission.setRolePermission.SalesRolePermissionSettingRequest;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 角色管理
 */
@Api(tags = "经销商端_后台_角色管理接口")
@RequestMapping(path = "/sales/roles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class SalesRolesController {

  private SalesRolesService salesRoleService;

  @Autowired
  public SalesRolesController(SalesRolesService salesRoleService){
    this.salesRoleService = salesRoleService;
  }

  /**
   * 查找所有角色列表
   */
  @ApiOperation(nickname = "salesRolesIndex",value = "获取角色列表接口")
  @GetMapping("/index")
  public ResponseData<Page<SalesRole>> index(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size,
      @Valid IndexSalesRolesRequest request) {
    return salesRoleService.index(page,size,request);
  }

  /**
   * 查找单个角色
   */
  @ApiOperation(nickname = "salesRolesSearch",value = "查找单个角色接口")
  @GetMapping("/search/{roleId}")
  public ResponseData search(@PathVariable Long roleId) {
    return salesRoleService.search(roleId);
  }

  /**
   * 新增角色
   */
  @ApiOperation(nickname = "salesRolesAdd",value = "新增角色接口")
  @PostMapping("/add")
  public ResponseData add(@RequestBody @Valid AddSalesRolesRequest request, BindingResult result) {
    //请求的数据参数格式不正确
    if (result.hasErrors()) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage(), result.getAllErrors()
      );
    }
    return salesRoleService.addRoles(request);
  }

  /**
   * 修改角色
   */
  @ApiOperation(nickname = "salesRolesUpdate",value = "修改角色接口")
  @PutMapping("/update/{roleId}")
  public ResponseData update(@PathVariable Long roleId, @RequestBody UpdateSalesRolesRequest request, BindingResult result) {
    //请求的数据参数格式不正确
    if (result.hasErrors()) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage(), result.getAllErrors()
      );
    }
    return salesRoleService.update(roleId,request);
  }

  /**
   * 删除角色
   */
  @ApiOperation(nickname = "salesRolesDelete",value = "删除角色接口")
  @PutMapping("/delete/{roleId}")
  public ResponseData delete(@PathVariable Long roleId) {
    return salesRoleService.delete(roleId);
  }

  /**
   * 角色授权
   */
  @ApiOperation(nickname = "salesPermissionRoleSetRolePermission",value = "角色授权接口")
  @PutMapping("/setRolePermission/{roleId}")
  public ResponseData setRolePermission(@PathVariable Long roleId, @RequestBody @Valid SalesRolePermissionSettingRequest request,
                                        BindingResult result) {
    //请求的数据参数格式不正确
    if (result.hasErrors()) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage(), result.getAllErrors()
      );
    }
    return salesRoleService.setRolePermission(roleId,request);
  }

}
