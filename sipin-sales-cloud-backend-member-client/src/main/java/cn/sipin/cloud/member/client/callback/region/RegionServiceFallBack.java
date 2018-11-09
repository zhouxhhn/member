/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.client.callback.region;

import org.springframework.stereotype.Component;
import cn.sipin.cloud.member.client.service.region.RegionService;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;

/**
 * 省市区地区熔断器
 */
@Component
public class RegionServiceFallBack implements RegionService {

  @Override
  public ResponseData index() {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }
}
