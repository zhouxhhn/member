/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.client.callback.sales.front;

import com.baomidou.mybatisplus.plugins.Page;

import org.springframework.stereotype.Component;

import java.util.List;

import cn.sipin.cloud.member.client.service.sales.SalesUserService;
import cn.sipin.cloud.member.client.service.sales.front.SalesFrontUserService;
import cn.sipin.cloud.member.pojo.request.sales.backend.user.addUser.AddUserRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.user.indexUser.IndexUserRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.user.updateUser.UpdateUserRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.user.userLogin.SalesUserLoginRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.editUserPwd.SalesEditUserPasswordRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.user.FrontAddUserRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.user.FrontSetRoleRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.user.FrontUpdateUserRequest;
import cn.sipin.cloud.member.pojo.response.sales.backend.user.indexUser.IndexUserResponse;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;

/**
 * 门店管理员熔断器
 */
@Component
public class SalesFrontUserServiceFallBack implements SalesFrontUserService {

  @Override public ResponseData<Page<IndexUserResponse>>  indexUser(int page, int size) {
    return new ResponseData<>(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData searchUser(Long userId) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData setStatus(Long userId) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData addUser(FrontAddUserRequest addUserRequest) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData updateUser(Long userId, FrontUpdateUserRequest updateUserRequest) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData setRole(Long userId, FrontSetRoleRequest request) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }
}
