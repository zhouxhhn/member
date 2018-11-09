/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.client.controller.sales.backend;

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
import cn.sipin.cloud.member.client.service.sales.SalesUserService;
import cn.sipin.cloud.member.pojo.request.sales.backend.user.addUser.AddUserRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.user.indexUser.IndexUserRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.user.updateUser.UpdateUserRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.user.userLogin.SalesUserLoginRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.editUserPwd.SalesEditUserPasswordRequest;
import cn.sipin.cloud.member.pojo.response.sales.backend.user.indexUser.IndexUserResponse;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 门店消费者
 */
@Api(tags = "经销商端_门店_管理员接口")
@RequestMapping(path = "/sales/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class SalesUserController {

  private SalesUserService salesUserService;

  @Autowired
  public SalesUserController(SalesUserService salesUserService){
    this.salesUserService = salesUserService;
  }

  /**
   * 获取所有管理员列表
   *
   * @param page 页码
   * @param size 每页显示页码的大小
   * @return 角色列表信息
   */
  @ApiOperation(nickname = "salesUserIndexUser",value = "获取指定门店的管理员列表接口")
  @GetMapping("/indexUser")
  public ResponseData<Page<IndexUserResponse>> indexUser(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size,
      @Valid IndexUserRequest request
  ) {
    return salesUserService.indexUser(page, size, request);
  }

  /**
   * 查找单个管理员
   * @param userId 管理员code
   * @return 返回查找信息
   */
  @ApiOperation(nickname = "salesUserSearchUser",value = "获取指定具体某个管理员信息的接口")
  @GetMapping("/searchUser/{userId}")
  public ResponseData searchShop(@PathVariable Long userId) {
    return salesUserService.searchUser(userId);
  }

  /**
   * 管理员端代码的生成
   *
   * @param addUserRequest 管理员信息
   * @return 生成信息返回
   */
  @ApiOperation(nickname = "salesUserAddUser",value = "新增门店管理员的接口")
  @PostMapping("/addUser")
  public ResponseData addShop(@RequestBody @Valid AddUserRequest addUserRequest, BindingResult result) {
    //请求的数据参数格式不正确
    if (result.hasErrors()) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage(),result.getAllErrors()
      );
    }
    return salesUserService.addUser(addUserRequest);
  }

  /**
   * 更新管理员信息
   * @param updateUserRequest 管理员信息
   * @return 更新信息返回
   */
  @ApiOperation(nickname = "salesUserUpdateUser",value = "修改门店管理员的接口")
  @PutMapping("/updateUser/{userId}")
  public ResponseData updateUser(@PathVariable Long userId,
                                 @RequestBody @Valid UpdateUserRequest updateUserRequest, BindingResult result
  ) {
    //请求的数据参数格式不正确
    if (result.hasErrors()) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage()
      );
    }
    return salesUserService.updateUser(userId,updateUserRequest);
  }

  /**
   * 设置状态
   */
  @ApiOperation(nickname = "salesUserSetStatus",value = "设置管理员的状态接口")
  @PutMapping("/setStatus/{userId}")
  public ResponseData setStatus(@PathVariable Long userId) {
    return salesUserService.setStatus(userId);
  }

  /**
   * 登录
   * @return 返回登录后的信息
   */
  @ApiOperation(nickname = "salesUserUserLogin",value = "门店管理员登录接口")
  @PostMapping(value = "/userLogin")
  public ResponseData userLogin(@RequestBody @Valid SalesUserLoginRequest userLoginRequest) {
    return salesUserService.userLogin(userLoginRequest);
  }

  @ApiOperation(nickname = "salesUserUserLogout",value = "门店管理员登出接口")
  @PostMapping(value = "/userLogout")
  public ResponseData userLogout() {
    return salesUserService.userLogout();
  }

  @ApiOperation(nickname = "salesUserGetUserByToken",value = "根据token获取管理员信息")
  @GetMapping(value = "/getUserByToken")
  public ResponseData getUserByToken() {
    return salesUserService.getUserByToken();
  }

  @ApiOperation(nickname = "salesUserSearchShop",value = "查询所有门店列表")
  @GetMapping("/searchShop")
  public ResponseData searchShop(@RequestParam(value = "type",required = false,defaultValue = "all") String type,
                                 @RequestParam(value = "page", required = false,defaultValue = "1") int page,
                                 @RequestParam(value = "size", required = false,defaultValue = "15") int size) {
    return salesUserService.searchShop(type, page, size);
  }

    /**
     * 修改个人密码
     */
  @ApiOperation(nickname = "salesUserEditUserPwd",value = "修改个人密码")
  @PutMapping("/editUserPwd")
  public ResponseData editUserPwd(@RequestBody @Valid SalesEditUserPasswordRequest request
      , BindingResult result) {
    //请求的数据参数格式不正确
    if (result.hasErrors()) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage()
      );
    }
    return salesUserService.editUserPwd(request);
  }

}
