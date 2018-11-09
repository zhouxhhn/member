/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.service.controller.sales.front;

import com.baomidou.mybatisplus.plugins.Page;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import cn.sipin.cloud.member.pojo.constants.MemberConstants;
import cn.sipin.cloud.member.pojo.pojo.salesUser.SalesUser;
import cn.sipin.cloud.member.pojo.request.sales.backend.user.addUser.AddUserRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.user.indexUser.IndexUserRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.user.updateUser.UpdateUserRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.user.FrontAddUserRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.user.FrontSetRoleRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.user.FrontUpdateUserRequest;
import cn.sipin.cloud.member.pojo.response.sales.backend.user.indexUser.IndexUserResponse;
import cn.sipin.cloud.member.service.service.sales.SalesUserServiceContract;
import cn.sipin.cloud.member.service.util.JsonTokenUtil;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.httplog.annotation.LogAnnotation;

/**
 * 经销商前台员工管理controller
 */
@RestController
@RequestMapping(path = "/sales/front/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SalesFrontUserController {


  private SalesUserServiceContract salesUserService;

  private JsonTokenUtil jsonTokenUtil;

  @Autowired
  public SalesFrontUserController(SalesUserServiceContract salesUserService,
                                  JsonTokenUtil jsonTokenUtil){
    this.salesUserService = salesUserService;
    this.jsonTokenUtil = jsonTokenUtil;
  }


  /**
   * 获取指定门店的员工列表
   * @param page 页码
   * @param size 每页显示页码的大小
   * @return 员工列表
   */
  @LogAnnotation
  @GetMapping("/indexUser")
  public ResponseData<Page<IndexUserResponse>> indexUser(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size) {
    Long userId = jsonTokenUtil.getCurrentUserId();
    SalesUser salesUser =  salesUserService.selectById(userId);
    IndexUserRequest request = new IndexUserRequest();
    request.setShopCode(salesUser.getShopCode());
    return salesUserService.indexUser(page,size,request);
  }

  /**
   * 新增门店管理员的接口
   * @param addUserRequest 管理员信息
   * @return 生成信息返回
   */
  @LogAnnotation
  @PostMapping("/addUser")
  public ResponseData addUser(@RequestBody FrontAddUserRequest addUserRequest) {
    //根据token获取用户信息
    Long userId = jsonTokenUtil.getCurrentUserId();
    SalesUser salesUser =  salesUserService.selectById(userId);

    //封装AddUserRequest对象
    AddUserRequest request = new AddUserRequest();
    BeanUtils.copyProperties(addUserRequest,request);
    request.setType(MemberConstants.SALES_USER_TYPE_SHOP);
    request.setShopCode(salesUser.getShopCode());

    //调用保存员工方法
    return salesUserService.addUser(request);
  }

  /**
   * 修改门店管理员的接口
   * @param updateUserRequest 管理员信息
   * @return 生成信息返回
   */
  @LogAnnotation
  @PutMapping("/updateUser/{userId}")
  public ResponseData updateUser(@PathVariable Long userId,@RequestBody FrontUpdateUserRequest updateUserRequest) {
    return salesUserService.frontUpdateUser(userId,updateUserRequest);
  }

  /**
   * 设置员工禁用与启用状态
   * @param userId 管理员code
   */
  @LogAnnotation
  @PutMapping("/setStatus/{userId}")
  public ResponseData setStatus(@PathVariable Long userId) {
    return salesUserService.setStatus(userId);
  }

  /**
   * 查找单个管理员信息
   * @param userId 单个管理员id
   * @return 返回查找信息
   */
  @LogAnnotation
  @GetMapping("/searchUser/{userId}")
  public ResponseData searchUser(@PathVariable Long userId) {
    return salesUserService.searchUser(userId);
  }

  @LogAnnotation
  @PutMapping("/setRole/{userId}")
  public ResponseData setRole(@PathVariable Long userId,@RequestBody FrontSetRoleRequest request) {
    //根据token获取用户信息
    SalesUser salesUser =  salesUserService.selectById(userId);

    UpdateUserRequest updateUserRequest = new UpdateUserRequest();

    //工号
    updateUserRequest.setEmpno(salesUser.getEmpno());

    //名称
    updateUserRequest.setName(salesUser.getName());

    //类型
    updateUserRequest.setType(MemberConstants.SALES_USER_TYPE_SHOP);

    //门店code
    updateUserRequest.setShopCode(salesUser.getShopCode());

    //角色ids
    updateUserRequest.setRoles(request.getRoles());

    //调用更新用户接口
    return salesUserService.updateUser(userId,updateUserRequest);
  }


}
