/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.client.controller.sales.front;

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

import java.util.List;

import javax.validation.Valid;

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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 门店消费者
 */
@Api(tags = "经销商端_前台_员工管理接口")
@RequestMapping(path = "/sales/front/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class SalesFrontUserController {

  private SalesFrontUserService salesFrontUserService;

  @Autowired
  public SalesFrontUserController(SalesFrontUserService salesFrontUserService){
    this.salesFrontUserService = salesFrontUserService;
  }

  /**
   * 获取所有管理员列表
   *
   * @param page 页码
   * @param size 每页显示页码的大小
   * @return 角色列表信息
   */
  @ApiOperation(nickname = "salesFrontUserIndexUser",value = "获取员工列表接口")
  @GetMapping("/indexUser")
  public ResponseData<Page<IndexUserResponse>> indexUser(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size
  ) {
    return salesFrontUserService.indexUser(page, size);
  }

  /**
   * 查找单个管理员
   * @param userId 管理员code
   * @return 返回查找信息
   */
  @ApiOperation(nickname = "salesFrontUserSearchUser",value = "获取指定具体某个管理员信息的接口")
  @GetMapping("/searchUser/{userId}")
  public ResponseData searchUser(@PathVariable Long userId) {
    return salesFrontUserService.searchUser(userId);
  }

  /**
   * 新增员工
   */
  @ApiOperation(nickname = "salesFrontUserAddUser",value = "新增员工的接口")
  @PostMapping("/addUser")
  public ResponseData addUser(@RequestBody @Valid FrontAddUserRequest addUserRequest, BindingResult result) {
    //请求的数据参数格式不正确
    if (result.hasErrors()) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage(),result.getAllErrors()
      );
    }
    return salesFrontUserService.addUser(addUserRequest);
  }

  /**
   * 更新管理员信息
   * @param updateUserRequest 管理员信息
   * @return 更新信息返回
   */
  @ApiOperation(nickname = "salesFrontUserUpdateUser",value = "修改员工的接口")
  @PutMapping("/updateUser/{userId}")
  public ResponseData updateUser(@PathVariable Long userId,
                                 @RequestBody @Valid FrontUpdateUserRequest updateUserRequest, BindingResult result
  ) {
    //请求的数据参数格式不正确
    if (result.hasErrors()) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage()
      );
    }
    return salesFrontUserService.updateUser(userId,updateUserRequest);
  }

  /**
   * 设置状态
   */
  @ApiOperation(nickname = "salesFrontUserSetStatus",value = "设置员工的状态接口")
  @PutMapping("/setStatus/{userId}")
  public ResponseData setStatus(@PathVariable Long userId) {
    return salesFrontUserService.setStatus(userId);
  }


  /**
   * 员工角色授权
   */
  @ApiOperation(nickname = "salesFrontUserSetRole",value = "员工角色授权")
  @PutMapping("/setRole/{userId}")
  public ResponseData setRole(@PathVariable Long userId, @RequestBody @Valid FrontSetRoleRequest request
      , BindingResult result) {
    //请求的数据参数格式不正确
    if (result.hasErrors()) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage()
      );
    }
    return salesFrontUserService.setRole(userId,request);
  }

}
