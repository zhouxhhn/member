package cn.sipin.cloud.member.service.mapper.sales;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import cn.sipin.cloud.member.pojo.pojo.salesAgency.SalesAgency;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.indexAgency.IndexAgencyRequest;

/**
 * <p>
 * 经销商表 Mapper 接口
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface SalesAgencyMapper extends BaseMapper<SalesAgency> {

  List<SalesAgency> selectAgencyPage(@Param("limit") Integer limit, @Param("offset") Integer offset, @Param("request") IndexAgencyRequest indexAgencyRequest);

  SalesAgency getAgencyInfo(Long userId);
}
