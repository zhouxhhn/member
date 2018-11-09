/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.service.controller.salesPermission;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import cn.sipin.cloud.member.pojo.constants.MemberConstants;
import cn.sipin.cloud.member.pojo.pojo.salesPermission.SalesPermissionAction;
import cn.sipin.cloud.member.service.service.sales.impl.JedisClusterServiceImpl;
import cn.sipin.cloud.member.service.service.salesPermission.SalesPermissionActionServiceContract;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import cn.siyue.platform.httplog.annotation.LogAnnotation;

/**
 * 经销商端权限认证接口
 */
@RestController
@RequestMapping(path = "/sales/authority", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SalesAuthorityController {

   private SalesPermissionActionServiceContract salesPermissionActionService;

   private JedisClusterServiceImpl jedisClusterService;

   @Autowired
   public SalesAuthorityController(SalesPermissionActionServiceContract salesPermissionActionService,
                                   JedisClusterServiceImpl jedisClusterService ){
     this.salesPermissionActionService = salesPermissionActionService;
     this.jedisClusterService = jedisClusterService;
   }


  /**
   * 前台查找权限组列表
   */
  @LogAnnotation
  @GetMapping("/judgetAuthority")
  public ResponseData judgetAuthority() {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    String uri = request.getParameter("uri");
    Map<String,Object> map = new HashMap<>();
    map.put("action_url",uri);
    List<SalesPermissionAction> salesPermissionActionList =  salesPermissionActionService.selectByMap(map);
    //白名单
    if(salesPermissionActionList == null || salesPermissionActionList.size() == 0){
      return ResponseData.build(ResponseBackCode.SUCCESS.getValue(), ResponseBackCode.SUCCESS.getMessage());
    }

     //获取token
    String token = request.getHeader("token");
    if(token == null || "".equals(token)){
      return ResponseData.build(ResponseBackCode.ERROR_TOKEN_TIMEOUT_CODE.getValue(), ResponseBackCode.ERROR_TOKEN_TIMEOUT_CODE.getMessage(),"没有token,未登录请登录");
    }

    //判断token合法性
    String json = jedisClusterService.get(MemberConstants.REDIS_USER_SESSION_KEY + ":" + token);
    if(json == null || "".equals(json)){
      return ResponseData.build(ResponseBackCode.ERROR_TOKEN_TIMEOUT_CODE.getValue(), ResponseBackCode.ERROR_TOKEN_TIMEOUT_CODE.getMessage(),"token无效");
    }

    //判断该用户是否有权限访问该地址
    Long userId = Long.parseLong(json);

    //获取该用户的所有权限
    List<SalesPermissionAction> permissionActionList = salesPermissionActionService.hasPermission(userId);
    if(permissionActionList != null && permissionActionList.size() > 0){
      boolean hasPermission = false;
      for(SalesPermissionAction salesPermissionAction:permissionActionList){
        if(salesPermissionAction.getActionUrl().equals(uri)){
          hasPermission = true;
        }
      }

      if(!hasPermission){
        return ResponseData.build(ResponseBackCode.ERROR_PERMISSION_DENIED.getValue(), ResponseBackCode.ERROR_PERMISSION_DENIED.getMessage());
      }
      //更新token和该用的url到redis中，以便authority从中查找相应的数据进行判断
      jedisClusterService.expire(MemberConstants.REDIS_USER_SESSION_KEY + ":" + token,  MemberConstants.SSO_SESSION_EXPIRE);
      return ResponseData.build(ResponseBackCode.SUCCESS.getValue(), ResponseBackCode.SUCCESS.getMessage());
    }
    return ResponseData.build(ResponseBackCode.ERROR_PERMISSION_DENIED.getValue(), ResponseBackCode.ERROR_PERMISSION_DENIED.getMessage());
  }
}
