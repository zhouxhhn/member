package cn.sipin.cloud.member.service.service.sales;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import cn.sipin.cloud.member.pojo.pojo.salesUser.SalesUser;
import cn.sipin.cloud.member.pojo.request.sales.backend.user.addUser.AddUserRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.user.indexUser.IndexUserRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.user.updateUser.UpdateUserRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.editUserPwd.SalesEditUserPasswordRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.user.FrontUpdateUserRequest;
import cn.sipin.cloud.member.pojo.response.sales.backend.user.indexUser.IndexUserResponse;
import cn.siyue.platform.base.ResponseData;

/**
 * <p>
 * 门店管理员 服务类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface SalesUserServiceContract extends IService<SalesUser> {

  /**
   * 登录
   *
   * @param username 用户名
   * @param password 密码
   * @param shopcode 门店
   * @return 返回登录成功的信息
   */
  ResponseData userLogin(String username, String password, String shopcode);

  /**
   * 登出
   * @return 返回登出错误
   */
  ResponseData userLogout(String token);

  /**
   * 根据用户id返回用户信息
   * @return 返回用户信息
   */
  ResponseData getUserByToken(Long userId);

  /**
   * 获取指定门店的管理员列表
   */
  ResponseData<Page<IndexUserResponse>> indexUser(int page, int size, IndexUserRequest request);

  /**
   * 查找单个管理员信息
   */
  ResponseData searchUser(Long userId);

  /**
   * 新增门店管理员的接口
   */
  ResponseData addUser(AddUserRequest addUserRequest);

  /**
   * 修改门店管理员的接口
   */
  ResponseData updateUser(Long userId, UpdateUserRequest updateUserRequest);

  /**
   * 设置状态
   */
  ResponseData setStatus(Long userId);


  /**
   * 修改个人密码
   */
  ResponseData editUserPwd(SalesEditUserPasswordRequest request);

  /**
   * 前台修改门店员工的接口
   */
  ResponseData frontUpdateUser(Long userId, FrontUpdateUserRequest request);

}
