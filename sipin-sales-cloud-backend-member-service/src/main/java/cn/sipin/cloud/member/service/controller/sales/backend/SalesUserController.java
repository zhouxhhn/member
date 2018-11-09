/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.service.controller.sales.backend;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import cn.sipin.cloud.member.pojo.pojo.salesUser.SalesUser;
import cn.sipin.cloud.member.pojo.request.sales.backend.user.addUser.AddUserRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.user.indexUser.IndexUserRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.user.updateUser.UpdateUserRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.user.userLogin.SalesUserLoginRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.editUserPwd.SalesEditUserPasswordRequest;
import cn.sipin.cloud.member.pojo.response.sales.backend.user.index.IndexResponse;
import cn.sipin.cloud.member.pojo.response.sales.backend.user.indexUser.IndexUserResponse;
import cn.sipin.cloud.member.service.service.sales.SalesShopServiceContract;
import cn.sipin.cloud.member.service.service.sales.SalesUserServiceContract;
import cn.sipin.cloud.member.service.util.JsonTokenUtil;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import cn.siyue.platform.httplog.annotation.LogAnnotation;

/**
 * 门店管理员管理
 */
@RestController
@RequestMapping(path = "/sales/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SalesUserController {

  private SalesUserServiceContract salesUserService;

  private SalesShopServiceContract salesShopService;

  private JsonTokenUtil jsonTokenUtil;

  @Autowired
  public SalesUserController(SalesUserServiceContract salesUserService, SalesShopServiceContract salesShopService,
                             JsonTokenUtil jsonTokenUtil){
    this.salesUserService = salesUserService;
    this.jsonTokenUtil = jsonTokenUtil;
    this.salesShopService = salesShopService;
  }

  /**
   * 登录
   */
  @LogAnnotation
  @PostMapping(value = "/userLogin")
  public ResponseData userLogin(@RequestBody SalesUserLoginRequest userLoginRequest) {
    return salesUserService.userLogin(userLoginRequest.getUserCode(), userLoginRequest.getPassword(), userLoginRequest.getShopCode());
  }

  /**
   * 登出
   */
  @LogAnnotation
  @PostMapping(value = "/userLogout")
  public ResponseData userLogout(HttpServletRequest request) {
    return salesUserService.userLogout(request.getHeader("token"));
  }

  /**
   * 获取指定门店的管理员列表
   * @param page 页码
   * @param size 每页显示页码的大小
   * @return 角色列表信息
   */
  @LogAnnotation
  @PostMapping("/indexUser")
  public ResponseData<Page<IndexUserResponse>> indexUser(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size,
      @RequestBody IndexUserRequest request) {
      return salesUserService.indexUser(page,size,request);
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

  /**
   * 新增门店管理员的接口
   * @param addUserRequest 管理员信息
   * @return 生成信息返回
   */
  @LogAnnotation
  @PostMapping("/addUser")
  public ResponseData addUser(@RequestBody AddUserRequest addUserRequest) {
    return salesUserService.addUser(addUserRequest);
  }

  /**
   * 更新管理员信息
   * @param userId 管理员信息
   * @return 更新信息返回
   */
  @LogAnnotation
  @PutMapping("/updateUser/{userId}")
  public ResponseData updateUser(@PathVariable Long userId,@RequestBody UpdateUserRequest updateUserRequest) {
    return salesUserService.updateUser(userId,updateUserRequest);
  }

  /**
   * 设置门店管理员信息
   * @param userId 管理员code
   */
  @LogAnnotation
  @PutMapping("/setStatus/{userId}")
  public ResponseData setStatus(@PathVariable Long userId) {
    return salesUserService.setStatus(userId);
  }

  /**
   * 根据token获取用户id,门店id和经销商id
   */
  @LogAnnotation
  @GetMapping("/getUserByToken")
  public ResponseData getUserByToken() {
    Long userId = jsonTokenUtil.getCurrentUserId();
    return salesUserService.getUserByToken(userId);
  }

  /**
   * 查询所有门店
   */
  @LogAnnotation
  @GetMapping("/searchShop")
  public ResponseData searchShop(@RequestParam(value = "type",required = false,defaultValue = "all") String type,
                                 @RequestParam(value = "page", required = false,defaultValue = "1") int page,
                                 @RequestParam(value = "size", required = false,defaultValue = "15") int size) {
    return salesShopService.searchAllShop(type,page,size);
  }

  /**
   * 修改个人密码
   */
  @LogAnnotation
  @PutMapping("/editUserPwd")
  public ResponseData editUserPwd(@RequestBody SalesEditUserPasswordRequest request) {
    return salesUserService.editUserPwd(request);
  }

  @GetMapping("/index")
  public ResponseData index() {
    List<SalesUser> list = salesUserService.selectList(new EntityWrapper<>());
    List<IndexResponse> responseList = new ArrayList<>();
    if(list != null && !list.isEmpty()){
      for (int i = 0,size = list.size(); i < size; i++) {
        IndexResponse response = new IndexResponse();
        BeanUtils.copyProperties(list.get(i),response);
        responseList.add(response);
      }
    }
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(), ResponseBackCode.SUCCESS.getMessage(),responseList);
  }
}
