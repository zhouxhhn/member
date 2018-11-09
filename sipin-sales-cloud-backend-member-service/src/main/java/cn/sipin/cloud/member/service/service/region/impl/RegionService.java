package cn.sipin.cloud.member.service.service.region.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import cn.sipin.cloud.member.pojo.pojo.region.Region;
import cn.sipin.cloud.member.service.mapper.region.RegionMapper;
import cn.sipin.cloud.member.service.service.region.RegionServiceContract;

/**
 * <p>
 * 行政区 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class RegionService extends ServiceImpl<RegionMapper, Region> implements RegionServiceContract {

  /**
   * 构造树形结构的地区
   * @param nodes 所有地区信息
   */
  @Override
  public List<Region> build(List<Region> nodes) {

    if (nodes == null) {
      return null;
    }
    List<Region> topNodes = new ArrayList<>();

    for (Region children : nodes) {

      Long pid = children.getParentId();
      if (pid == 0) {
        topNodes.add(children);
        continue;
      }
      for (Region parent : nodes) {
        Long id = parent.getId();
        if (id != null && id.equals(pid)) {
          parent.getChildren().add(children);
          continue;
        }
      }
    }
    return topNodes;
  }

  @Override
  public List<Region> selectByRegionId(List<Long> regionIds) {
   return baseMapper.selectByRegionId(regionIds);
  }

  /**
   * 获取：省市区+详细地址
   */
  @Override
  public String getFullAddress(Long provinceCode,Long cityCode,Long districtCode,String address) {
    List<Long> rigionCode = new ArrayList<>();
    rigionCode.add(provinceCode);
    rigionCode.add(cityCode);
    rigionCode.add(districtCode);

    //设置省市区的名字
    List<Region> regionList = selectByRegionId(rigionCode);

    String proviceName ="";
    String cityName ="";
    String districtName ="";
    if(regionList != null && regionList.size() > 0){
      for(Region region:regionList){
        if(provinceCode.equals(region.getId())){
          proviceName=region.getName();
        }else if(cityCode.equals(region.getId())){
          cityName=region.getName();
        }else{
          districtName = region.getName();
        }
      }
    }
    //传入的省市区code正确的
    if(!"".equals(proviceName) && !"".equals(cityName) && !"".equals(districtName)){
      return proviceName+cityName+districtName+address;
    }else{
      //传入的省市区code错误的
      return null;
    }

  }
}
