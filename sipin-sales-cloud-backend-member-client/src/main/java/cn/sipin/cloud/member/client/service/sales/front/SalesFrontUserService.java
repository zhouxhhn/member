/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.client.service.sales.front;

import com.baomidou.mybatisplus.plugins.Page;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import cn.sipin.cloud.member.client.callback.sales.SalesUserServiceFallBack;
import cn.sipin.cloud.member.client.callback.sales.front.SalesFrontUserServiceFallBack;
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

/**
 * 调用前台会员管理服务生产者的接口
 */
@FeignClient(name = "sales-member-service", path = "/sales/front/user",fallback = SalesFrontUserServiceFallBack.class)
public interface SalesFrontUserService {

  @RequestMapping(value = "/indexUser", method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData<Page<IndexUserResponse>> indexUser(@RequestParam("page") int page, @RequestParam("size") int size);

  @RequestMapping(value = "/searchUser/{userId}", method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData searchUser(@PathVariable(value = "userId") Long userId);

  @RequestMapping(value = "/setStatus/{userId}", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData setStatus(@PathVariable(value = "userId") Long userId);

  @RequestMapping(value = "/addUser", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData addUser(FrontAddUserRequest addUserRequest);

  @RequestMapping(value = "/updateUser/{userId}", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData updateUser(@PathVariable(value = "userId") Long userId, FrontUpdateUserRequest updateUserRequest);

  @RequestMapping(value = "/setRole/{userId}", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData setRole(@PathVariable(value = "userId") Long userId, FrontSetRoleRequest request );
}
