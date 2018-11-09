package cn.sipin.cloud.member.service.service.salesPermission;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

import cn.sipin.cloud.member.pojo.pojo.salesPermission.SalesRole;
import cn.sipin.cloud.member.pojo.request.salesPermission.frontRoles.add.AddSalesFrontRolesRequest;
import cn.sipin.cloud.member.pojo.request.salesPermission.frontRoles.update.UpdateSalesFrontRolesRequest;
import cn.sipin.cloud.member.pojo.request.salesPermission.roles.add.AddSalesRolesRequest;
import cn.sipin.cloud.member.pojo.request.salesPermission.roles.index.IndexSalesRolesRequest;
import cn.sipin.cloud.member.pojo.request.salesPermission.roles.update.UpdateSalesRolesRequest;
import cn.siyue.platform.base.ResponseData;

/**
 * <p>
 * 经销商角色表 服务类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface SalesRoleServiceContract extends IService<SalesRole> {

  /**
   * 查找所有角色列表
   */
  ResponseData<Page<SalesRole>> index(int page, int size, IndexSalesRolesRequest request);

  /**
   * 查找单个角色
   */
  ResponseData search(Long roleId);

  /**
   * 新增角色
   */
  ResponseData addRoles(AddSalesRolesRequest request);


  /**
   * 修改角色
   */
  ResponseData update(Long roleId, UpdateSalesRolesRequest request);

  /**
   * 删除角色
   */
  ResponseData delete(Long roleId);

  /**
   * 根据用户id获取该用户的角色
   */
  List<SalesRole> selectRolesByUserId(Long userId);


  /**
   * 前台查找所有角色列表
   */
  ResponseData<Page<SalesRole>> frontIndex(int page, int size);


  /**
   * 前台新增角色
   */
  ResponseData frontAddRoles(AddSalesFrontRolesRequest request);

  /**
   * 前台修改角色
   */
  ResponseData frontUpdate(Long roleId, UpdateSalesFrontRolesRequest request);

}
