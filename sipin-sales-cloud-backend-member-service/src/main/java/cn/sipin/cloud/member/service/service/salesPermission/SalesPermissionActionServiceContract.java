package cn.sipin.cloud.member.service.service.salesPermission;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import java.util.List;
import cn.sipin.cloud.member.pojo.pojo.salesPermission.SalesPermissionAction;
import cn.sipin.cloud.member.pojo.response.salesPermission.permissionGroup.indexAction.SalesPermissionActionResponse;
import cn.siyue.platform.base.ResponseData;

/**
 * <p>
 * 经销商端权限表 服务类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface SalesPermissionActionServiceContract extends IService<SalesPermissionAction> {

  /**
   * 查找指定权限组对应的权限列表
   */
  ResponseData<Page<SalesPermissionActionResponse>> indexAction(Long roleId, Long groupId);

  /**
   * 判断是否有权限
   */
  List<SalesPermissionAction> hasPermission(Long userId);

}
