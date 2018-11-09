/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.client.service.salesPermission.front;

import com.baomidou.mybatisplus.plugins.Page;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import cn.sipin.cloud.member.client.callback.salesPermission.front.SalesFrontRolesServiceFallBack;
import cn.sipin.cloud.member.pojo.pojo.salesPermission.SalesRole;
import cn.sipin.cloud.member.pojo.request.salesPermission.frontRoles.add.AddSalesFrontRolesRequest;
import cn.sipin.cloud.member.pojo.request.salesPermission.frontRoles.update.UpdateSalesFrontRolesRequest;
import cn.sipin.cloud.member.pojo.request.salesPermission.rolesPermission.setRolePermission.SalesRolePermissionSettingRequest;
import cn.siyue.platform.base.ResponseData;

/**
 * 调用前台角色服务接口
 */
@FeignClient(name = "sales-member-service", path = "/sales/front/roles",fallback = SalesFrontRolesServiceFallBack.class)
public interface SalesFrontRolesService {

  @RequestMapping(value = "/index", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData<Page<SalesRole>> index(@RequestParam("page") int page, @RequestParam("size") int size);

  @RequestMapping(value = "/search/{roleId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData search(@PathVariable(value = "roleId") Long userId);

  @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData addRoles(AddSalesFrontRolesRequest request);

  @RequestMapping(value = "/update/{roleId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData update(@PathVariable(value = "roleId") Long roleId, UpdateSalesFrontRolesRequest request);

  @RequestMapping(value = "/delete/{roleId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData delete(@PathVariable(value = "roleId") Long roleId);

  @RequestMapping(value = "/setRolePermission/{roleId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData setRolePermission(@PathVariable(value = "roleId") Long roleId, SalesRolePermissionSettingRequest request);

}
