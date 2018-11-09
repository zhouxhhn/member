package cn.sipin.cloud.member.service.service.sales;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import cn.sipin.cloud.member.pojo.pojo.salesAgency.SalesAgency;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.addAgency.AddAgencyRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.indexAgency.IndexAgencyRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.updateAgency.UpdateAgencyRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.updateBalance.SalesUpdateBalanceRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.updateOuterCode.SalesUpdateOuterCodeRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.PurchasePaymentRequest;
import cn.sipin.cloud.member.pojo.response.sales.backend.agency.indexAgency.SalesAgencyIndexResponse;
import cn.siyue.platform.base.ResponseData;

/**
 * <p>
 * 门店 服务类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface SalesAgencyServiceContract extends IService<SalesAgency> {

  /**
   * 新增经销商端信息
   */
  ResponseData addAgency(AddAgencyRequest addAgencyRequest);

  /**
   * 更新经销商端信息
   */
  ResponseData updateAgency(Long agencyId, UpdateAgencyRequest updateAgencyRequest);

  /**
   * 查找经销商信息
   */
  ResponseData<Page<SalesAgencyIndexResponse>> selectAgencyPage(Page<SalesAgencyIndexResponse> responsePage, IndexAgencyRequest indexAgencyRequest);

  /**
   * 修改余额
   */
  ResponseData updateBalance(Long agencyId, SalesUpdateBalanceRequest request);

  /**
   * 获取经销商信息
   */
  ResponseData getAgencyInfo();

  /**
   * 采购订单支付接口
   */
  ResponseData purchasePayment(PurchasePaymentRequest request);

  /**
   * 根据采购订单号查支付流水号即是否已扣款
   */
  ResponseData purchasePayment(String purchaseNo);

  /**
   * 修改外部编码
   */
  ResponseData updateOuterCode(Long agencyId, SalesUpdateOuterCodeRequest request);


}
