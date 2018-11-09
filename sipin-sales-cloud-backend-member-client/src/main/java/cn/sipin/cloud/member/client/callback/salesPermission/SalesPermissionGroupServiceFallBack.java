/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.client.callback.salesPermission;

import com.baomidou.mybatisplus.plugins.Page;

import org.springframework.stereotype.Component;
import cn.sipin.cloud.member.client.service.salesPermission.SalesPermissionGroupService;
import cn.sipin.cloud.member.pojo.response.salesPermission.permissionGroup.indexAction.SalesPermissionActionResponse;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;

/**
 * 经销商端后台角色管理熔断器
 */
@Component
public class SalesPermissionGroupServiceFallBack implements SalesPermissionGroupService {

  @Override public ResponseData indexFrontGroup(int page, int size) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData indexBackGroup(int page, int size) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData<Page<SalesPermissionActionResponse>> indexAction(Long roleId, Long groupId) {
    return new ResponseData<>(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }
}
