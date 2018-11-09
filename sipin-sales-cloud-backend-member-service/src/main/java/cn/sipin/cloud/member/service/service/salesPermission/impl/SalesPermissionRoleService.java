package cn.sipin.cloud.member.service.service.salesPermission.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.sipin.cloud.member.pojo.constants.MemberConstants;
import cn.sipin.cloud.member.pojo.pojo.salesPermission.SalesPermissionRole;
import cn.sipin.cloud.member.pojo.pojo.salesPermission.SalesRole;
import cn.sipin.cloud.member.pojo.pojo.salesUser.SalesUser;
import cn.sipin.cloud.member.pojo.request.salesPermission.rolesPermission.setRolePermission.SalesRolePermissionSettingRequest;
import cn.sipin.cloud.member.service.mapper.salesPermission.SalesPermissionRoleMapper;
import cn.sipin.cloud.member.service.service.salesPermission.SalesPermissionRoleServiceContract;
import cn.sipin.cloud.member.service.util.JsonTokenUtil;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;

/**
 * <p>
 * 经销商端权限角色对应表 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class SalesPermissionRoleService extends ServiceImpl<SalesPermissionRoleMapper, SalesPermissionRole> implements SalesPermissionRoleServiceContract {

  private JsonTokenUtil jsonTokenUtil;

  @Autowired
  public SalesPermissionRoleService(JsonTokenUtil jsonTokenUtil){
    this.jsonTokenUtil = jsonTokenUtil;
  }

  /**
   * 角色授权
   */

  @Override
  @Transactional(rollbackFor = Exception.class)
  public ResponseData setRolePermission(Long roleId,SalesRole salesRole,SalesUser salesUser,SalesRolePermissionSettingRequest request) {


    if(MemberConstants.ROLE_PUBLIC == salesRole.getType()){
      //公有的类型时，只有系统管理员才能新增
      if(MemberConstants.SALES_USER_TYPE_SYSTEM != salesUser.getType()){
        return ResponseData.build(ResponseBackCode.ERROR_PERMISSION_DENIED.getValue(), ResponseBackCode.ERROR_PERMISSION_DENIED.getMessage(),"无权限，不能为公有角色授权");
      }
    }

    Long groupId = request.getGroupId();
    List<Long> actionsIds = request.getPermissionActionIds();
    List<SalesPermissionRole> addPermissionRoleList = new ArrayList<>();

    if (actionsIds != null && actionsIds.size() > 0) {
      for (int i = 0,size = actionsIds.size(); i< size; i++){
        SalesPermissionRole salesPermissionRole = new SalesPermissionRole();
        salesPermissionRole.setPermissionGroupId(groupId);
        salesPermissionRole.setPermissionId(actionsIds.get(i));
        salesPermissionRole.setRoleId(roleId);
        addPermissionRoleList.add(salesPermissionRole);
      }
    }
    //该组所有权限都取消
    Map<String,Object> map = new HashMap();
    map.put("role_id",roleId);
    map.put("permission_group_id",groupId);
    deleteByMap(map);
    if(addPermissionRoleList.size() > 0){
      insertBatch(addPermissionRoleList);
    }
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(), ResponseBackCode.SUCCESS.getMessage());
  }
}
