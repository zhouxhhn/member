package cn.sipin.cloud.member.service.mapper.sales;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import cn.sipin.cloud.member.pojo.pojo.salesShop.SalesShop;
import cn.sipin.cloud.member.pojo.request.sales.backend.shop.index.IndexShopRequest;

/**
 * <p>
 * 门店表 Mapper 接口
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface SalesShopMapper extends BaseMapper<SalesShop> {

  List<SalesShop> selectShopPage(@Param("limit") Integer limit, @Param("offset") Integer offset, @Param("code") String code, @Param("request") IndexShopRequest indexShopRequest);



}
