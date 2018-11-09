package cn.sipin.cloud.member.service.service.salesPermission.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.sipin.cloud.member.pojo.pojo.salesPermission.SalesPermissionAction;
import cn.sipin.cloud.member.pojo.pojo.salesPermission.SalesPermissionRole;
import cn.sipin.cloud.member.pojo.response.salesPermission.permissionGroup.indexAction.SalesPermissionActionResponse;
import cn.sipin.cloud.member.service.mapper.salesPermission.SalesPermissionActionMapper;
import cn.sipin.cloud.member.service.service.salesPermission.SalesPermissionActionServiceContract;
import cn.sipin.cloud.member.service.service.salesPermission.SalesPermissionRoleServiceContract;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;

/**
 * <p>
 * 经销商端权限表 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class SalesPermissionActionService extends ServiceImpl<SalesPermissionActionMapper, SalesPermissionAction> implements SalesPermissionActionServiceContract {

  private SalesPermissionRoleServiceContract salesPermissionRoleService;

  @Autowired
  public SalesPermissionActionService(SalesPermissionRoleServiceContract salesPermissionRoleService){
    this.salesPermissionRoleService = salesPermissionRoleService;
  }

  @Override
  public ResponseData<Page<SalesPermissionActionResponse>> indexAction(Long roleId, Long groupId) {
    Page userPage = new Page<SalesPermissionAction>();
    Map<String,Object> map = new HashMap<>();
    map.put("group_id",groupId);
    List<SalesPermissionAction> actionList = selectByMap(map);
    if(actionList != null && actionList.size() > 0){

      Map<Long,Long> permissionedMap = new HashMap<>();
      //查找授权的权限
      Map<String,Object> permissionMap = new HashMap();
      permissionMap.put("role_id",roleId);
      permissionMap.put("permission_group_id",groupId);
      List<SalesPermissionRole> roleList = salesPermissionRoleService.selectByMap(permissionMap);
      if(roleList != null && roleList.size() > 0){
        for (SalesPermissionRole role:roleList) {
          permissionedMap.put(role.getPermissionId(),role.getPermissionGroupId());
        }
      }

      //设置状态
      List<SalesPermissionActionResponse> responseList = new ArrayList<>();
      for (SalesPermissionAction salesPermissionAction:actionList){
        SalesPermissionActionResponse response = new SalesPermissionActionResponse();
        BeanUtils.copyProperties(salesPermissionAction, response);
        if(permissionedMap.get(response.getId()) != null){
          response.setStatus(1);
        }
        responseList.add(response);
      }
      userPage.setRecords(responseList);
      userPage.setTotal(responseList.size());
    }
    return new ResponseData<>(ResponseBackCode.SUCCESS.getValue(), ResponseBackCode.SUCCESS.getMessage(), userPage);

  }

  @Override
  public List<SalesPermissionAction> hasPermission(Long userId) {
    return baseMapper.hasPermission(userId);
  }
}
