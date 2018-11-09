package cn.sipin.cloud.member.service.service.sales.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.sipin.cloud.member.pojo.constants.MemberConstants;
import cn.sipin.cloud.member.pojo.pojo.printSetting.PrintSetting;
import cn.sipin.cloud.member.pojo.pojo.salesPermission.SalesPermissionAction;
import cn.sipin.cloud.member.pojo.pojo.salesPermission.SalesRole;
import cn.sipin.cloud.member.pojo.pojo.salesPermission.SalesUserRole;
import cn.sipin.cloud.member.pojo.pojo.salesUser.SalesUser;
import cn.sipin.cloud.member.pojo.pojo.salesUser.SalesUserCompose;
import cn.sipin.cloud.member.pojo.request.sales.backend.user.addUser.AddUserRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.user.indexUser.IndexUserRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.user.updateUser.UpdateUserRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.editUserPwd.SalesEditUserPasswordRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.user.FrontUpdateUserRequest;
import cn.sipin.cloud.member.pojo.response.sales.backend.user.indexUser.IndexUserResponse;
import cn.sipin.cloud.member.pojo.response.sales.backend.user.searchUser.SearchUserResponse;
import cn.sipin.cloud.member.pojo.response.salesPermission.roles.searchUser.SalesUserRolesResponse;
import cn.sipin.cloud.member.service.mapper.sales.SalesUserMapper;
import cn.sipin.cloud.member.service.service.printSetting.PrintSettingServiceContract;
import cn.sipin.cloud.member.service.service.sales.SalesUserServiceContract;
import cn.sipin.cloud.member.service.service.salesPermission.SalesPermissionActionServiceContract;
import cn.sipin.cloud.member.service.service.salesPermission.SalesRoleServiceContract;
import cn.sipin.cloud.member.service.service.salesPermission.SalesUserRoleServiceContract;
import cn.sipin.cloud.member.service.util.GenerateRandomUtil;
import cn.sipin.cloud.member.service.util.JsonTokenUtil;
import cn.sipin.cloud.member.service.util.TokenProccessor;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import cn.siyue.platform.exceptions.exception.RequestException;

/**
 * <p>
 * 门店管理员 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class SalesUserServiceImpl extends ServiceImpl<SalesUserMapper, SalesUser> implements SalesUserServiceContract {

  private JedisClusterServiceImpl jedisClusterService;

  private SalesUserRoleServiceContract userRoleService;

  private JsonTokenUtil jsonTokenUtil;

  private PrintSettingServiceContract printSettingService;

  private SalesPermissionActionServiceContract salesPermissionActionService;



  @Autowired
  public SalesUserServiceImpl(JedisClusterServiceImpl jedisClusterService,
                              SalesUserRoleServiceContract userRoleService,
                              JsonTokenUtil jsonTokenUtil,SalesPermissionActionServiceContract salesPermissionActionService,
                              PrintSettingServiceContract printSettingService
                             ){
    this.jedisClusterService = jedisClusterService;
    this.userRoleService = userRoleService;
    this.jsonTokenUtil = jsonTokenUtil;
    this.printSettingService = printSettingService;
    this.salesPermissionActionService = salesPermissionActionService;
  }

  /**
   * 登录
   *
   * @param password 密码
   * @param shopcode 门店
   */
  @Override
  public ResponseData userLogin(String usercode, String password, String shopcode) {
    try {
      //查找用户名
      Map<String, Object> map = new HashMap<>();
      if(shopcode !=null && !"".equals(shopcode)){
         map.put("shop_code", shopcode);
      }
      map.put("empno", usercode); //普通用户登录
      map.put("status",0);

      List<SalesUser> list = selectByMap(map);
      if (list == null || list.size() == 0) {
        return ResponseData.build(
            ResponseBackCode.ERROR_USER_NOT_EXIST.getValue(),
            ResponseBackCode.ERROR_USER_NOT_EXIST.getMessage()
        );
      }
      SalesUser salesUser = list.get(0);

      //如果是普通用户必须要选门店
      if(MemberConstants.SALES_USER_TYPE_SYSTEM != salesUser.getType()){
        if(shopcode ==null || "".equals(shopcode)){
          return ResponseData.build(
              ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
              ResponseBackCode.ERROR_PARAM_INVALID.getMessage(),"请选择门店"
          );
        }
      }

      //比对密码
      if (!BCrypt.checkpw(password, salesUser.getPassword())) {
        return ResponseData.build(
            ResponseBackCode.ERROR_USER_NOT_EXIST.getValue(),
            ResponseBackCode.ERROR_USER_NOT_EXIST.getMessage()
        );
      }

      //生成token
      String token = TokenProccessor.getInstance().makeToken(usercode);

      //保存之前对密码清空
      jedisClusterService.set(MemberConstants.REDIS_USER_SESSION_KEY + ":" + token, salesUser.getId() + "");

      //设置session的过期时间
      jedisClusterService.expire(MemberConstants.REDIS_USER_SESSION_KEY + ":" + token, MemberConstants.SSO_SESSION_EXPIRE);

      //返回token
      SalesUserCompose salesUserCompose = new SalesUserCompose();

      //获取该用户的所有权限列表数据
      List<SalesPermissionAction> salesPermissionActionList = salesPermissionActionService.hasPermission(salesUser.getId());
      salesUser.setPermissionActionList(salesPermissionActionList);
      //返回给前台时去掉密码
      salesUser.setPassword(null);
      salesUserCompose.setSalesUser(salesUser);
      salesUserCompose.setToken(token);

      //查询打印设置信息
      if(shopcode !=null && !"".equals(shopcode)){

        Map<String,Object> printMap = new HashMap<>();
        printMap.put("shop_code",shopcode);
        List<PrintSetting> printSettingList =  printSettingService.selectByMap(printMap);
        if(printSettingList != null && printSettingList.size() >0){
          salesUserCompose.setPrintSetting(printSettingList.get(0));
        }
      }
      return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage(), salesUserCompose);
    }catch (Exception e){
      throw new RequestException(ResponseBackCode.ERROR_FAIL.getValue(), e.getMessage());
    }
  }

  /**
   * 登出
   */
  @Override
  public ResponseData userLogout(String token) {
    jedisClusterService.del(MemberConstants.REDIS_USER_SESSION_KEY+ ":" +token);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage());
  }

  @Override
  public ResponseData getUserByToken(Long userId) {
    Map map = baseMapper.getUserInfoByToken(userId);

    //返回token
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage(),map);
  }

  @Override
  public ResponseData<Page<IndexUserResponse>> indexUser(int page, int size, IndexUserRequest request) {

    //所属门店
    Long userId = jsonTokenUtil.getCurrentUserId();
    SalesUser user =  selectById(userId);

    Page userPage = new Page<SalesUser>(page, size);
    userPage.setAsc(false);
    List<SalesUser> salesUserList;
    List<SalesUser> salesUserTotalList;
    if(MemberConstants.SALES_USER_TYPE_SHOP == user.getType()){
      //如果是门店管理员只能查找自己的门店列表
      salesUserList = baseMapper.getUserListByShop(userPage.getLimit(), userPage.getOffset(),user.getShopCode(),request);
      salesUserTotalList = baseMapper.getUserListByShop(null, null,user.getShopCode(),request);
    }else{
      //如果是系统管理员，显示它所有门店的列表
      salesUserList = baseMapper.getUserInfoListBySystem(userPage.getLimit(), userPage.getOffset(),request);
      salesUserTotalList = baseMapper.getUserInfoListBySystem(null, null,request);
    }

    if(salesUserList != null){
      List<IndexUserResponse> responses = new ArrayList<>();
      for (int i = 0,length = salesUserList.size();i < length; i++){
        IndexUserResponse response = new IndexUserResponse();
        BeanUtils.copyProperties(salesUserList.get(i),response);
        responses.add(response);
      }
      userPage.setRecords(responses);
      userPage.setTotal(salesUserTotalList.size());
    }
    return new ResponseData<>(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage(), userPage);
  }

  @Override
  public ResponseData searchUser(Long userId) {
    SalesUser salesUser = selectById(userId);
    if(salesUser == null){
      return ResponseData.build(ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getValue(),
                                ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getMessage(),"传入的用户id有误，找不到该用户");
    }
    SearchUserResponse response = new SearchUserResponse();
    BeanUtils.copyProperties(salesUser, response);
    Map map = baseMapper.getUserInfoByToken(salesUser.getId());
    response.setShopName((String)map.get("shopName"));

    //查找

    //查找角色
    List<SalesRole> salesRoleList = baseMapper.selectRolesByUserId(userId);
    List<SalesUserRolesResponse> salesUserRolesResponses = new ArrayList<>();
    SalesUserRolesResponse userRolesResponse = null;
    if(salesRoleList != null && salesRoleList.size() > 0){
      for (SalesRole salesRole: salesRoleList){
        userRolesResponse = new SalesUserRolesResponse();
        BeanUtils.copyProperties(salesRole, userRolesResponse);
        salesUserRolesResponses.add(userRolesResponse);
      }
    }
    response.setRoles(salesUserRolesResponses);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage(), response);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public ResponseData addUser(AddUserRequest addUserRequest) {

    //判断工号是否重复
    if(judgetEmpno(addUserRequest.getEmpno(),addUserRequest.getShopCode())){
      return ResponseData.build(ResponseBackCode.ERROR_PARAM_INVALID.getValue(),"工号重复，不能新增","工号重复，不能新增");
    }

      //对密码加密，进行保存
    addUserRequest.setPassword(BCrypt.hashpw(addUserRequest.getPassword(), BCrypt.gensalt()));

      //保存门店管理员
    SalesUser user = new SalesUser();


    BeanUtils.copyProperties(addUserRequest, user);
    String code = generateUserCode();
    user.setCode(code);
    insert(user);

    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage());
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public ResponseData updateUser(Long userId, UpdateUserRequest updateUserRequest) {

      //判断是否存在该用户
      SalesUser storageSalesUser = selectById(userId);
      if (storageSalesUser == null) {
        return ResponseData.build(ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getValue(),
            ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getMessage(),"传入的用户id有误，找不到该用户");
      }
     //类型
      storageSalesUser.setType(updateUserRequest.getType());
     //所属门店
      storageSalesUser.setShopCode(updateUserRequest.getShopCode());
     //姓名
      storageSalesUser.setName(updateUserRequest.getName());
     //工号
      if(!storageSalesUser.getEmpno().equals(updateUserRequest.getEmpno())){
        if(judgetEmpno(updateUserRequest.getEmpno(),updateUserRequest.getShopCode())){
          return ResponseData.build(ResponseBackCode.ERROR_PARAM_INVALID.getValue(),ResponseBackCode.ERROR_PARAM_INVALID.getMessage(),"工号重复，不能新增");
        }
        storageSalesUser.setEmpno(updateUserRequest.getEmpno());
      }

     //密码
     if(updateUserRequest.getPassword() != null && !"".equals(updateUserRequest.getPassword())){
       storageSalesUser.setPassword(BCrypt.hashpw(updateUserRequest.getPassword(), BCrypt.gensalt()));
     }

     updateById(storageSalesUser);


    //删除角色表
    Map<String,Object> map = new HashMap<>();
    map.put("user_id",storageSalesUser.getId());
    userRoleService.deleteByMap(map);

    //插入到角色表
    List<Long> userRoles = updateUserRequest.getRoles();
    List<SalesUserRole> list = new ArrayList<>();
    int size = userRoles.size();
    for (int i = 0; i < size; i++) {
      SalesUserRole role = new SalesUserRole();
      role.setUserId(storageSalesUser.getId());
      role.setRoleId(userRoles.get(i));
      list.add(role);
    }
    userRoleService.insertBatch(list);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage());
  }

  /**
   * 设置状态
   */
  @Override
  public ResponseData setStatus(Long userId) {

    //查找经销商
    SalesUser user = selectById(userId);
    if(user == null){
      return ResponseData.build(ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getValue(),
                                ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getMessage(), "传入的用户id有误，找不到对应的用户");
    }

    if(MemberConstants.SALES_USER_STATUS_VALID == user.getStatus()){
      user.setStatus(MemberConstants.SALES_USER_STATUS_INVALID);
    }else{
      user.setStatus(MemberConstants.SALES_USER_STATUS_VALID);
    }

    updateById(user);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),
                              ResponseBackCode.SUCCESS.getMessage());
  }

  @Override
  public ResponseData editUserPwd(SalesEditUserPasswordRequest request) {

    //根据token获取用户对象
    Long userId = jsonTokenUtil.getCurrentUserId();
    SalesUser salesUser = selectById(userId);

    //旧密码比对
    if (!BCrypt.checkpw(request.getOldPassword(), salesUser.getPassword())) {
      return ResponseData.build(ResponseBackCode.ERROR_USER_NOT_EXIST.getValue(), "旧密码输入错误");
    }

    //两个新密码不能为空
    if(request.getNewPassword() == null || request.getNewPassword().isEmpty() || "".equals(request.getNewPassword().trim())){
      return ResponseData.build(ResponseBackCode.ERROR_PARAM_EMPTY.getValue(), "新密码必填，不能为空");
    }
    if(request.getRenewPassword() == null || request.getRenewPassword().isEmpty() || "".equals(request.getRenewPassword().trim())){
      return ResponseData.build(ResponseBackCode.ERROR_PARAM_EMPTY.getValue(), "重复输入的新密码必填，不能为空");
    }
    //两个新密码是否相等
    if(!request.getNewPassword().equals(request.getRenewPassword())){
      return ResponseData.build(ResponseBackCode.ERROR_PARAM_EMPTY.getValue(), "输入两次的新密码不相同，不能修改密码");
    }
    //对密码加密，进行保存
    salesUser.setPassword(BCrypt.hashpw(request.getNewPassword(), BCrypt.gensalt()));
    updateById(salesUser);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),
                              ResponseBackCode.SUCCESS.getMessage());
  }

  /**
   * 前台修改门店员工的接口
   */
  @Override
  public ResponseData frontUpdateUser(Long userId, FrontUpdateUserRequest request) {

    //判断是否存在该用户
    SalesUser storageSalesUser = selectById(userId);
    if (storageSalesUser == null) {
      return ResponseData.build(ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getValue(),"传入的参数有误，找不到该用户");
    }

    //工号
    if(request.getEmpno() != null && !"".equals(request.getEmpno().trim())){
      storageSalesUser.setEmpno(request.getEmpno());
    }

    //姓名
    if(request.getName() != null && !"".equals(request.getName().trim())){
      storageSalesUser.setName(request.getName());
    }

    //登录密码
    if(request.getPassword() != null && !"".equals(request.getPassword().trim())){
      storageSalesUser.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
    }
    updateById(storageSalesUser);

    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),
                              ResponseBackCode.SUCCESS.getMessage());
  }

  private String generateUserCode(){

    String code = "M0000001";
    List<SalesUser> list = selectList(new EntityWrapper<>());
    Map<String,Long> map = new HashMap<>();
    if(list != null && list.size() > 0){
      for (SalesUser salesUser:list){
        map.put(salesUser.getCode(),salesUser.getId());
      }
      String generateCode = "M" + GenerateRandomUtil.generateCode(7);
      //生成code,判断是否重复
      while (map.get(generateCode) != null){
        generateCode = "M" + GenerateRandomUtil.generateCode(7);
      }
      code = generateCode;
    }
    return code;
  }

  /**
   * 判断门店内工号是否重复
   */
  private boolean judgetEmpno(String empno,String shopCode){
    Map<String,Object> map = new HashMap<>();
    map.put("empno",empno);
    map.put("shop_code",shopCode);
    List<SalesUser> list =  selectByMap(map);
    return list != null && list.size()>0 ;
  }
}
