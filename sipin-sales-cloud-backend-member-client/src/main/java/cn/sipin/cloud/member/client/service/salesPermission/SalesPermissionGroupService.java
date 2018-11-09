/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.client.service.salesPermission;

import com.baomidou.mybatisplus.plugins.Page;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import cn.sipin.cloud.member.client.callback.salesPermission.SalesPermissionGroupServiceFallBack;
import cn.sipin.cloud.member.pojo.response.salesPermission.permissionGroup.indexAction.SalesPermissionActionResponse;
import cn.siyue.platform.base.ResponseData;

/**
 * 调用会员管理服务生产者的接口
 */
@FeignClient(name = "sales-member-service", path = "/sales/permission/group",fallback = SalesPermissionGroupServiceFallBack.class)
public interface SalesPermissionGroupService {

  @RequestMapping(value = "/front/indexFrontGroup", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData indexFrontGroup(@RequestParam("page") int page, @RequestParam("size") int size);

  @RequestMapping(value = "/back/indexBackGroup", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData indexBackGroup(@RequestParam("page") int page, @RequestParam("size") int size);

  @RequestMapping(value = "/indexAction/{roleId}/{groupId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData<Page<SalesPermissionActionResponse>> indexAction(@PathVariable(value = "roleId") Long roleId, @PathVariable(value = "groupId") Long groupId);



}
