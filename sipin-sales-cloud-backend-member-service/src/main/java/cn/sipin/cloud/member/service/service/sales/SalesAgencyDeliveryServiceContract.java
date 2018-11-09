package cn.sipin.cloud.member.service.service.sales;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import cn.sipin.cloud.member.pojo.pojo.salesAgency.SalesAgencyDelivery;
import cn.sipin.cloud.member.pojo.request.sales.backend.delivery.addAddress.SalesAgencyDeliveryRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.delivery.updateAddress.UpdateSalesAgencyDeliveryRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.delivery.addAddress.SaveSalesAgencyFrontDeliveryRequest;
import cn.sipin.cloud.member.pojo.response.sales.backend.delivery.SalesAgencyDeliveryResponse;
import cn.siyue.platform.base.ResponseData;

/**
 * <p>
 * 经销商配送信息表 服务类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface SalesAgencyDeliveryServiceContract extends IService<SalesAgencyDelivery> {

  /**
   * 设置默认地址
   */
   ResponseData setDefaultAddress(Long addressId);

  /**
   * 后台新增地址
   */
  ResponseData addAddress(SalesAgencyDeliveryRequest request);

  /**
   * 前台新增地址
   */
  ResponseData frontAddAddress(SaveSalesAgencyFrontDeliveryRequest request);

  /**
   * 更新地址
   */
  ResponseData updateAddress(Long addressId, UpdateSalesAgencyDeliveryRequest request);

  /**
   * 删除地址
   */
  ResponseData deleteAddress(Long addressId);

  /**
   * 查询单个地址
   */
  ResponseData<SalesAgencyDeliveryResponse> searchAddress(Long addressId);

  /**
   * 后台查询指定门店的地址
   */
  ResponseData<Page> index(Long shopId, int page, int size);

  /**
   * 前台查询指定门店的地址
   */
  ResponseData<Page> searchAllAddress(int page, int size);
}
