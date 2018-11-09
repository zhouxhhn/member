package cn.sipin.cloud.member.service.mapper.salesPermission;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import cn.sipin.cloud.member.pojo.pojo.salesPermission.SalesPermissionAction;

/**
 * <p>
 * 经销商端权限表 Mapper 接口
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface SalesPermissionActionMapper extends BaseMapper<SalesPermissionAction> {

  List<SalesPermissionAction> hasPermission(@Param("userId") Long userId);


}
