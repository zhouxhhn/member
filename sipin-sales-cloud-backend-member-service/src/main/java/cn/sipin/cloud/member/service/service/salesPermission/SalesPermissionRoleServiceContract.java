package cn.sipin.cloud.member.service.service.salesPermission;

import com.baomidou.mybatisplus.service.IService;
import cn.sipin.cloud.member.pojo.pojo.salesPermission.SalesPermissionRole;
import cn.sipin.cloud.member.pojo.pojo.salesPermission.SalesRole;
import cn.sipin.cloud.member.pojo.pojo.salesUser.SalesUser;
import cn.sipin.cloud.member.pojo.request.salesPermission.rolesPermission.setRolePermission.SalesRolePermissionSettingRequest;
import cn.siyue.platform.base.ResponseData;

/**
 * <p>
 * 经销商端权限角色对应表 服务类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface SalesPermissionRoleServiceContract extends IService<SalesPermissionRole> {

  /**
   * 角色授权
   */
  ResponseData setRolePermission(Long roleId, SalesRole salesRole, SalesUser salesUser,SalesRolePermissionSettingRequest request);

}
