/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.client.callback.sales;

import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.stereotype.Component;
import cn.sipin.cloud.member.client.service.sales.SalesAgencyService;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.addAgency.AddAgencyRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.indexAgency.IndexAgencyRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.updateAgency.UpdateAgencyRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.updateBalance.SalesUpdateBalanceRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.updateOuterCode.SalesUpdateOuterCodeRequest;
import cn.sipin.cloud.member.pojo.response.sales.backend.agency.index.IndexSalesAgencyResponse;
import cn.sipin.cloud.member.pojo.response.sales.backend.agency.indexAgency.SalesAgencyIndexResponse;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;

/**
 * 经销商熔断器
 */
@Component
public class SalesAgencyServiceFallBack implements SalesAgencyService {

  @Override public ResponseData<Page<SalesAgencyIndexResponse>> indexAgency(int page, int size, IndexAgencyRequest indexAgencyRequest) {
    return new ResponseData<>(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData<Page<IndexSalesAgencyResponse>> index() {
    return new ResponseData<>(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData searchAgency(Long agencyId) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData deleteAgency(Long agencyId) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData addAgency(AddAgencyRequest addAgencyRequest) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData updateAgency(Long agencyId, UpdateAgencyRequest updateAgencyRequest) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData updateBalance(Long agencyId, SalesUpdateBalanceRequest request) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData getBalance(Long agencyId, int page, int size) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData updateOuterCode(Long id, SalesUpdateOuterCodeRequest request) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData getDiscount(String agencyCode) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }
}
