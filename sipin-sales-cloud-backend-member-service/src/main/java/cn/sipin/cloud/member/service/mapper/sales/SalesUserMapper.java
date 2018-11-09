package cn.sipin.cloud.member.service.mapper.sales;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

import cn.sipin.cloud.member.pojo.pojo.salesPermission.SalesRole;
import cn.sipin.cloud.member.pojo.pojo.salesUser.SalesUser;
import cn.sipin.cloud.member.pojo.request.sales.backend.user.indexUser.IndexUserRequest;

/**
 * <p>
 * 门店员工表 Mapper 接口
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface SalesUserMapper extends BaseMapper<SalesUser> {



  List<SalesUser> getUserListByShop(@Param("limit") Integer limit, @Param("offset") Integer offset, @Param("shopCode") String shopCode, @Param("request") IndexUserRequest request);

  List<SalesUser> getUserInfoListBySystem(
      @Param("limit") Integer limit, @Param("offset") Integer offset, @Param("request") IndexUserRequest
      request
  );

  List<SalesRole> selectRolesByUserId(@Param("userId") Long userId);

  Map getUserInfoByToken(Long userId);

}
