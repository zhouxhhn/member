/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.service.service.salesPermission.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import cn.sipin.cloud.member.pojo.constants.MemberConstants;
import cn.sipin.cloud.member.pojo.pojo.salesPermission.SalesPermissionAction;
import cn.sipin.cloud.member.service.service.sales.impl.JedisClusterServiceImpl;
import cn.sipin.cloud.member.service.service.salesPermission.InitSalesRedisService;
import cn.sipin.cloud.member.service.service.salesPermission.SalesPermissionActionServiceContract;

@Service
public class InitSalesRedisServiceImpl implements InitSalesRedisService {

  private JedisClusterServiceImpl jedisClusterService;

  private SalesPermissionActionServiceContract salesPermissionActionService;

  @Autowired
  public InitSalesRedisServiceImpl(JedisClusterServiceImpl jedisClusterService,
                                   SalesPermissionActionServiceContract salesPermissionActionService){
    this.jedisClusterService = jedisClusterService;
    this.salesPermissionActionService = salesPermissionActionService;
  }

  /**
   * 把权限数据存储到redis中
   */
  @Override
  public boolean initSalesRedis() {

    try {
      List<SalesPermissionAction> permissionActionList = salesPermissionActionService.selectList(new EntityWrapper<>());
      if(permissionActionList != null && permissionActionList.size() > 0){
        for (SalesPermissionAction permissionAction:permissionActionList){
          String url = permissionAction.getActionUrl();
          jedisClusterService.set(MemberConstants.PERMISSION_URL + ":" + url, url);
        }
      }
      return true;
    }catch (Exception e){
      return false;
    }
  }
}
