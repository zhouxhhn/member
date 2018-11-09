package cn.sipin.cloud.member.service.mapper.region;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import cn.sipin.cloud.member.pojo.pojo.region.Region;

/**
 * <p>
 * 行政区 Mapper 接口
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface RegionMapper extends BaseMapper<Region> {

  List<Region> selectByRegionId(@Param("regionIds") List<Long> regionIds);
}
