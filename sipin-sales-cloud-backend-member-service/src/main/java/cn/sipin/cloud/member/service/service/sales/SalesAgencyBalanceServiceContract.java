package cn.sipin.cloud.member.service.service.sales;

import com.baomidou.mybatisplus.service.IService;
import cn.sipin.cloud.member.pojo.pojo.salesAgency.SalesAgencyBalance;

/**
 * <p>
 * 经销商余额表 服务类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface SalesAgencyBalanceServiceContract extends IService<SalesAgencyBalance> {

  /**
   * 修改金额
   * @param salesAgencyBalance
   * @return
   */
  Boolean updateBalance(SalesAgencyBalance salesAgencyBalance);
}
