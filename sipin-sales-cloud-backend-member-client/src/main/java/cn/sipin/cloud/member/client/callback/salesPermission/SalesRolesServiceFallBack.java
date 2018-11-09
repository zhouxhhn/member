/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.client.callback.salesPermission;

import com.baomidou.mybatisplus.plugins.Page;

import org.springframework.stereotype.Component;
import cn.sipin.cloud.member.client.service.salesPermission.SalesRolesService;
import cn.sipin.cloud.member.client.service.salesPermission.front.SalesFrontRolesService;
import cn.sipin.cloud.member.pojo.pojo.salesPermission.SalesRole;
import cn.sipin.cloud.member.pojo.request.salesPermission.frontRoles.add.AddSalesFrontRolesRequest;
import cn.sipin.cloud.member.pojo.request.salesPermission.frontRoles.update.UpdateSalesFrontRolesRequest;
import cn.sipin.cloud.member.pojo.request.salesPermission.roles.add.AddSalesRolesRequest;
import cn.sipin.cloud.member.pojo.request.salesPermission.roles.index.IndexSalesRolesRequest;
import cn.sipin.cloud.member.pojo.request.salesPermission.roles.update.UpdateSalesRolesRequest;
import cn.sipin.cloud.member.pojo.request.salesPermission.rolesPermission.setRolePermission.SalesRolePermissionSettingRequest;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;

/**
 * 经销商端后台角色管理熔断器
 */
@Component
public class SalesRolesServiceFallBack implements SalesRolesService {

  @Override public ResponseData<Page<SalesRole>> index(int page, int size, IndexSalesRolesRequest request) {
    return new ResponseData<>(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData search(Long userId) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData addRoles(AddSalesRolesRequest request) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData update(Long roleId, UpdateSalesRolesRequest request) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData delete(Long roleId) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData setRolePermission(Long roleId, SalesRolePermissionSettingRequest request) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }
}
