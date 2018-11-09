/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.client.callback.sales;

import com.baomidou.mybatisplus.plugins.Page;

import org.springframework.stereotype.Component;
import cn.sipin.cloud.member.client.service.sales.SalesShopService;
import cn.sipin.cloud.member.pojo.request.sales.backend.shop.addShop.AddShopRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.shop.index.IndexShopRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.shop.updateShop.UpdateShopRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.shop.updateSourceIdRequest.SalesUpdateSourceIdRequest;
import cn.sipin.cloud.member.pojo.response.sales.backend.shop.index.IndexShopResponse;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;

/**
 * 经销商熔断器
 */
@Component
public class SalesShopServiceFallBack implements SalesShopService {

  @Override public ResponseData<Page<IndexShopResponse>> index(int page, int size, IndexShopRequest request) {
    return new ResponseData<>(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );  }

  @Override public ResponseData searchShop(Long shopId) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData deleteShop(Long shopId) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData addShop(AddShopRequest addShopRequest) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData updateShop(Long shopId, UpdateShopRequest updateShopRequest) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override
  public ResponseData updateSourceId(Long shopId, SalesUpdateSourceIdRequest request) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }
}
