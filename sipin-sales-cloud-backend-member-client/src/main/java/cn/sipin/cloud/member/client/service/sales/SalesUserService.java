/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.client.service.sales;

import com.baomidou.mybatisplus.plugins.Page;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import cn.sipin.cloud.member.client.callback.sales.SalesUserServiceFallBack;
import cn.sipin.cloud.member.pojo.request.sales.backend.user.addUser.AddUserRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.user.indexUser.IndexUserRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.user.updateUser.UpdateUserRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.user.userLogin.SalesUserLoginRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.editUserPwd.SalesEditUserPasswordRequest;
import cn.sipin.cloud.member.pojo.response.sales.backend.user.indexUser.IndexUserResponse;
import cn.siyue.platform.base.ResponseData;

/**
 * 调用会员管理服务生产者的接口
 */
@FeignClient(name = "sales-member-service", path = "/sales/user",fallback = SalesUserServiceFallBack.class)
public interface SalesUserService {

  @RequestMapping(value = "/userLogin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData userLogin(SalesUserLoginRequest userLoginRequest);

  @RequestMapping(value = "/userLogout", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData userLogout();

  @RequestMapping(value = "/indexUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData<Page<IndexUserResponse>> indexUser(@RequestParam("page") int page, @RequestParam("size") int size, IndexUserRequest request);

  @RequestMapping(value = "/searchUser/{userId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData searchUser(@PathVariable(value = "userId") Long userId);

  @RequestMapping(value = "/setStatus/{userId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData setStatus(@PathVariable(value = "userId") Long userId);

  @RequestMapping(value = "/addUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData addUser(AddUserRequest addUserRequest);

  @RequestMapping(value = "/updateUser/{userId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData updateUser(@PathVariable(value = "userId") Long userId, UpdateUserRequest updateUserRequest);

  @RequestMapping(value = "/getUserByToken", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData getUserByToken();

  @RequestMapping(value = "/searchShop", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData searchShop(@RequestParam(value = "type") String type, @RequestParam(value = "page", required = false) int page, @RequestParam(value = "size", required = false) int size);

  @RequestMapping(value = "/editUserPwd", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData editUserPwd(SalesEditUserPasswordRequest request);
}
