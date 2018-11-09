/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.client.callback.sales.front;

import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.stereotype.Component;
import cn.sipin.cloud.member.client.service.sales.front.SalesAgencyFrontDeliveryService;
import cn.sipin.cloud.member.pojo.request.sales.backend.delivery.updateAddress.UpdateSalesAgencyDeliveryRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.delivery.addAddress.SaveSalesAgencyFrontDeliveryRequest;
import cn.sipin.cloud.member.pojo.response.sales.backend.delivery.SalesAgencyDeliveryResponse;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;

/**
 * 经销商熔断器
 */
@Component
public class SalesAgencyFrontDeliveryServiceFallBack implements SalesAgencyFrontDeliveryService {

  @Override public ResponseData addAddress(SaveSalesAgencyFrontDeliveryRequest saveSalesAgencyFrontDeliveryRequest) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData updateAddress(Long addressId, UpdateSalesAgencyDeliveryRequest updateSalesAgencyDeliveryRequest) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );  }

  @Override public ResponseData setDefaultAddress(Long addressId) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );  }

  @Override public ResponseData deleteAddress(Long addressId) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );  }

  @Override public ResponseData<SalesAgencyDeliveryResponse> searchAddress(Long addressId) {
    return new ResponseData<>(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData<Page> index(int page, int size) {
    return new ResponseData<>(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );  }
}
