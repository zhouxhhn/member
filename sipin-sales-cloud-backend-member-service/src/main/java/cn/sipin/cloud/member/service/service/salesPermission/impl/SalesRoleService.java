package cn.sipin.cloud.member.service.service.salesPermission.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.sipin.cloud.member.pojo.constants.MemberConstants;
import cn.sipin.cloud.member.pojo.pojo.salesPermission.SalesRole;
import cn.sipin.cloud.member.pojo.pojo.salesShop.SalesShop;
import cn.sipin.cloud.member.pojo.pojo.salesUser.SalesUser;
import cn.sipin.cloud.member.pojo.request.salesPermission.frontRoles.add.AddSalesFrontRolesRequest;
import cn.sipin.cloud.member.pojo.request.salesPermission.frontRoles.update.UpdateSalesFrontRolesRequest;
import cn.sipin.cloud.member.pojo.request.salesPermission.roles.add.AddSalesRolesRequest;
import cn.sipin.cloud.member.pojo.request.salesPermission.roles.index.IndexSalesRolesRequest;
import cn.sipin.cloud.member.pojo.request.salesPermission.roles.update.UpdateSalesRolesRequest;
import cn.sipin.cloud.member.pojo.response.sales.backend.user.indexUser.IndexUserResponse;
import cn.sipin.cloud.member.pojo.response.salesPermission.roles.search.SearchSalesRolesResponse;
import cn.sipin.cloud.member.service.mapper.salesPermission.SalesRoleMapper;
import cn.sipin.cloud.member.service.service.sales.SalesShopServiceContract;
import cn.sipin.cloud.member.service.service.sales.SalesUserServiceContract;
import cn.sipin.cloud.member.service.service.salesPermission.SalesPermissionRoleServiceContract;
import cn.sipin.cloud.member.service.service.salesPermission.SalesRoleServiceContract;
import cn.sipin.cloud.member.service.service.salesPermission.SalesUserRoleServiceContract;
import cn.sipin.cloud.member.service.util.GenerateRandomUtil;
import cn.sipin.cloud.member.service.util.JsonTokenUtil;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;

/**
 * <p>
 * 经销商角色表 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class SalesRoleService extends ServiceImpl<SalesRoleMapper, SalesRole> implements SalesRoleServiceContract {

  private JsonTokenUtil jsonTokenUtil;

  private SalesUserServiceContract salesUserService;

  private SalesShopServiceContract salesShopService;

  private SalesPermissionRoleServiceContract salesPermissionRoleService;

  private SalesUserRoleServiceContract salesUserRoleService;

  @Autowired
  public SalesRoleService(JsonTokenUtil jsonTokenUtil,SalesUserServiceContract salesUserService,
                          SalesShopServiceContract salesShopService,
                          SalesPermissionRoleServiceContract salesPermissionRoleService,
                          SalesUserRoleServiceContract salesUserRoleService){
    this.jsonTokenUtil = jsonTokenUtil;
    this.salesUserService = salesUserService;
    this.salesShopService = salesShopService;
    this.salesPermissionRoleService = salesPermissionRoleService;
    this.salesUserRoleService = salesUserRoleService;
  }

  /**
   * 查找所有角色列表
   */
  @Override
  public ResponseData<Page<SalesRole>> index(int page, int size, IndexSalesRolesRequest request) {
    Page userPage = new Page<SalesShop>(page, size);
    userPage.setAsc(false);
    Long userId = jsonTokenUtil.getCurrentUserId();
    SalesUser salesUser = salesUserService.selectById(userId);

    List<SalesRole> salesRoleList=null;
    List<SalesRole> salesRoleTotalList=null;
    Long shopId = null;
    if(MemberConstants.SALES_USER_TYPE_SYSTEM != salesUser.getType()){
       shopId = getShopId(userId);
      //门店管理员只能查看自己门店的角色，(公有的角色和自己门店私有的角色)
    }

    //如果是系统管理员给指定用户授权时，不能查看系统管理员角色
    if(MemberConstants.SALES_USER_TYPE_SYSTEM == salesUser.getType()){
      if(request.getScope() != null){
        Map<String,Object> map = new HashMap<>();
        map.put("name",request.getScope());
        List<SalesShop> shopList = salesShopService.selectByMap(map);
        if(shopList != null && !shopList.isEmpty()){
          shopId = shopList.get(0).getId();
          request.setScope(null);
        }
      }
    }


    salesRoleList = baseMapper.selectRolesPage(userPage.getLimit(), userPage.getOffset(), shopId,request);
    salesRoleTotalList = baseMapper.selectRolesPage(null, null, shopId,request);

    userPage.setRecords(salesRoleList);
    if(salesRoleTotalList != null && salesRoleTotalList.size() >0){
      userPage.setTotal(salesRoleTotalList.size());
    }

    return new ResponseData<>(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage(), userPage);
  }

  /**
   * 查找单个角色
   */
  @Override
  public ResponseData search(Long roleId) {
    SalesRole salesRole = selectById(roleId);
    if(salesRole == null) {
      return ResponseData.build(ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getValue(), ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getMessage(),"传入的门店id有误，找不到角色");
    }
    SearchSalesRolesResponse response = new SearchSalesRolesResponse();
    BeanUtils.copyProperties(salesRole,response);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage(),response);

  }

  /**
   * 新增角色
   */
  @Override
  public ResponseData addRoles(AddSalesRolesRequest request) {
    //创建者
    Long userId = jsonTokenUtil.getCurrentUserId();

    if(MemberConstants.ROLE_PUBLIC == request.getType() || MemberConstants.ROLE_SYSTEM == request.getType()){
      //公有的类型和系统角色时，只有系统管理员才能新增
      SalesUser salesUser = salesUserService.selectById(userId);
      if(MemberConstants.SALES_USER_TYPE_SYSTEM != salesUser.getType()){
        return ResponseData.build(ResponseBackCode.ERROR_PERMISSION_DENIED.getValue(), ResponseBackCode.ERROR_PERMISSION_DENIED.getMessage(),"无权限，不能创建公有角色和系统管理员角色");
      }
    }

    //角色名不能重复
    String roleName = request.getName();
    Map<String,Object> map = new HashMap<>();
    map.put("name",roleName);
    List<SalesRole> salesRoleList = selectByMap(map);
    if(salesRoleList != null && salesRoleList.size() > 0){
      return ResponseData.build(ResponseBackCode.ERROR_OBJECT_EXIST.getValue(), ResponseBackCode.ERROR_OBJECT_EXIST.getMessage(),"角色名已重复，不能创建");
    }


    SalesRole salesRole = new SalesRole();
    BeanUtils.copyProperties(request,salesRole);
    //生成角色帐号
    String code = generateRolesCode();
    salesRole.setCode(code);


    salesRole.setCreator(userId);

    //如果是私有
    if(MemberConstants.ROLE_PRIVATE == request.getType()){
      Long shopId = getShopId(userId);
      if(shopId != null){
        salesRole.setScope(shopId);
      }
    }

    //插入到角色表
    insert(salesRole);

    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(), ResponseBackCode.SUCCESS.getMessage());

  }

  /**
   * 修改角色
   */
  @Override
  public ResponseData update(Long roleId, UpdateSalesRolesRequest request) {

    //创建者
    Long userId = jsonTokenUtil.getCurrentUserId();

    if(MemberConstants.ROLE_PUBLIC == request.getType()){
      //公有的类型时，只有系统管理员才能新增
      SalesUser salesUser = salesUserService.selectById(userId);
      if(MemberConstants.SALES_USER_TYPE_SYSTEM != salesUser.getType()){
        return ResponseData.build(ResponseBackCode.ERROR_PERMISSION_DENIED.getValue(), ResponseBackCode.ERROR_PERMISSION_DENIED.getMessage(),"无权限，不能对公有角色进行修改");
      }
    }


    SalesRole salesRole = selectById(roleId);
    if(salesRole == null) {
      return ResponseData.build(ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getValue(), ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getMessage(),"传入的id有误，该角色不存在");
    }

    //查询角色名是否重复
    if(!salesRole.getName().equals(request.getName().trim())){
      Map<String,Object> map = new HashMap<>();
      map.put("name",request.getName());
      List<SalesRole> salesRoleList = selectByMap(map);
      if(salesRoleList != null && salesRoleList.size() > 0){
        return ResponseData.build(ResponseBackCode.ERROR_OBJECT_EXIST.getValue(), ResponseBackCode.ERROR_OBJECT_EXIST.getMessage(),"角色名已重复，不能创建");
      }
    }

    BeanUtils.copyProperties(request,salesRole);

    salesRole.setCreator(userId);

    //如果是私有
    if(MemberConstants.ROLE_PRIVATE == request.getType()){
      Long shopId = getShopId(userId);
      if(shopId != null){
        salesRole.setScope(shopId);
      }

    }

    updateById(salesRole);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage() );
  }


  @Override
  @Transactional(rollbackFor = Exception.class)
  public ResponseData delete(Long roleId) {

    SalesRole salesRole = selectById(roleId);
    if(salesRole == null){
      return ResponseData.build(ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getValue(), ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getMessage(),"传入的id有误，该角色不存在，无法进行删除");
    }

    //创建者
    Long userId = jsonTokenUtil.getCurrentUserId();

    if(MemberConstants.ROLE_PUBLIC == salesRole.getType()){
      //公有的类型时，只有系统管理员才能新增
      SalesUser salesUser = salesUserService.selectById(userId);
      if(MemberConstants.SALES_USER_TYPE_SYSTEM != salesUser.getType()){
        return ResponseData.build(ResponseBackCode.ERROR_PERMISSION_DENIED.getValue(), ResponseBackCode.ERROR_PERMISSION_DENIED.getMessage(),"无权限，不能对公有角色进行删除");
      }
    }

    //删除sales_permission_role表中的数据
    Map<String,Object> permissionMap = new HashMap<>();
    permissionMap.put("role_id",roleId);
    salesPermissionRoleService.deleteByMap(permissionMap);

    //删除sales_user_role中的数据
    permissionMap = new HashMap<>();
    permissionMap.put("role_id",roleId);
    salesUserRoleService.deleteByMap(permissionMap);

    //删除sales_role中的数据
    deleteById(roleId);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage() );
  }

  @Override
  public List<SalesRole> selectRolesByUserId(Long userId) {
    return baseMapper.selectRolesByUserId(userId);
  }

  /**
   * 前台查找所有角色列表
   */
  @Override
  public ResponseData<Page<SalesRole>> frontIndex(int page, int size) {
    Page userPage = new Page<SalesRole>(page, size);
    userPage.setAsc(false);
    Long userId = jsonTokenUtil.getCurrentUserId();
    Long shopId =  getShopId(userId);

    List<SalesRole> salesRoleList = baseMapper.selectfrontRolesPage(userPage.getLimit(), userPage.getOffset(), shopId);
    List<SalesRole> salesRoleTotalList = baseMapper.selectfrontRolesPage(null, null, shopId);

    userPage.setRecords(salesRoleList);
    if(salesRoleTotalList != null && salesRoleTotalList.size() >0){
      userPage.setTotal(salesRoleTotalList.size());
    }
    return new ResponseData<>(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage(), userPage);
  }

  /**
   * 前台新增角色
   */
  @Override
  public ResponseData frontAddRoles(AddSalesFrontRolesRequest request) {

    AddSalesRolesRequest addSalesRolesRequest = new AddSalesRolesRequest();
    BeanUtils.copyProperties(request,addSalesRolesRequest);

    //设置子系统--前台
    addSalesRolesRequest.setChildrenSystem(MemberConstants.ROLE_FRONT);

    //设置类型--私有
    addSalesRolesRequest.setType(MemberConstants.ROLE_PRIVATE);

    //设置门店id
    Long userId = jsonTokenUtil.getCurrentUserId();
    Long shopId = getShopId(userId);
    addSalesRolesRequest.setScope(shopId);
    return addRoles(addSalesRolesRequest);
  }

  /**
   * 前台修改角色
   */
  @Override
  public ResponseData frontUpdate(Long roleId, UpdateSalesFrontRolesRequest request) {

    //查找角色
    SalesRole role =  selectById(roleId);
    if(role == null){
      return ResponseData.build(ResponseBackCode.ERROR_OBJECT_EXIST.getValue(),"该角色不存在，不能修改");
    }

    //设置状态
    if(request.getStatus() != null){
      role.setStatus(request.getStatus());
    }

    //设置角色名称
    if(request.getName() != null && !"".equals(request.getName().trim()) && !role.getName().equals(request.getName())){
        Map<String,Object> map = new HashMap<>();
        map.put("name",request.getName().trim());
        List<SalesRole> salesRoleList = selectByMap(map);
        if(salesRoleList != null && salesRoleList.size() > 0){
          return ResponseData.build(ResponseBackCode.ERROR_OBJECT_EXIST.getValue(), ResponseBackCode.ERROR_OBJECT_EXIST.getMessage(),"角色名已重复，不能修改");
        }
      role.setName(request.getName());
    }
    updateById(role);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage());
  }

  private Long getShopId(Long userId){
    //如果是超级管理员，必须要传入门店id
    Long shopId = null;
    SalesUser salesUser = salesUserService.selectById(userId);
    if(MemberConstants.SALES_USER_TYPE_SYSTEM != salesUser.getType()){
      //如果是门店管理员，则不需要传入门店id
      String shopCode = salesUser.getShopCode();
      Map<String,Object> shopMap = new HashMap<>();
      shopMap.put("code",shopCode);
      List<SalesShop> shopList =  salesShopService.selectByMap(shopMap);
      if(shopList != null && shopList.size() > 0){
        SalesShop salesShop = shopList.get(0);
        shopId = salesShop.getId();
      }
    }
    return shopId;
  }

  /**
   * 生成5位数的code
   * 例：A00001
   */
  private String generateRolesCode(){

    String code = "R00001";
    List<SalesRole> list = selectList(new EntityWrapper<>());
    Map<String,Long> map = new HashMap<>();
    if(list != null && list.size() > 0){
      for (SalesRole salesRole:list){
        map.put(salesRole.getCode(),salesRole.getId());
      }
      String generateCode = "R" + GenerateRandomUtil.generateCode(5);
      //生成code,判断是否重复
      while (map.get(generateCode) != null){
        generateCode = "R"+GenerateRandomUtil.generateCode(5);
      }
      code = generateCode;
    }
    return code;
  }
}
