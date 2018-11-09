package cn.sipin.cloud.member.service.mapper.salesPermission;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import cn.sipin.cloud.member.pojo.pojo.salesPermission.SalesRole;
import cn.sipin.cloud.member.pojo.request.salesPermission.roles.index.IndexSalesRolesRequest;

/**
 * <p>
 * 经销商角色表 Mapper 接口
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface SalesRoleMapper extends BaseMapper<SalesRole> {


  List<SalesRole> selectRolesPage(@Param("limit") Integer limit, @Param("offset") Integer offset,@Param("shopId") Long shopId, @Param("request") IndexSalesRolesRequest request);

  List<SalesRole> selectRolesByUserId(@Param("id") Long id);

  /**
   * 前台查询角色列表
   */
  List<SalesRole> selectfrontRolesPage(@Param("limit") Integer limit, @Param("offset") Integer offset,@Param("shopId") Long shopId);


}
