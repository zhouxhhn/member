package cn.sipin.cloud.member.service.service.sales.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.sipin.cloud.member.pojo.constants.SalesConstants;
import cn.sipin.cloud.member.pojo.pojo.salesAgency.SalesAgency;
import cn.sipin.cloud.member.pojo.pojo.salesAgency.SalesAgencyBalance;
import cn.sipin.cloud.member.service.mapper.sales.SalesAgencyBalanceMapper;
import cn.sipin.cloud.member.service.service.sales.SalesAgencyBalanceServiceContract;
import cn.sipin.cloud.member.service.service.sales.SalesAgencyServiceContract;

/**
 * <p>
 * 经销商余额表 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class SalesAgencyBalanceServiceImpl extends ServiceImpl<SalesAgencyBalanceMapper, SalesAgencyBalance> implements SalesAgencyBalanceServiceContract {

  @Autowired
  private SalesAgencyServiceContract salesAgencyService;


  @Override
  @Transactional(rollbackFor = Exception.class)
  public Boolean updateBalance(SalesAgencyBalance salesAgencyBalance) {

    //修改经销商的总金额
    Map map = new HashMap();
     map.put("code", salesAgencyBalance.getCode());
    List<SalesAgency> salesAgencyList = salesAgencyService.selectByMap(map);
    BigDecimal balance = new BigDecimal("0.0");
    SalesAgency salesAgency;
    if(salesAgencyList != null){
      salesAgency = salesAgencyList.get(0);
      balance = salesAgency.getBalance();
      if(SalesConstants.BALANCE_INCREASE.equals(salesAgencyBalance.getBusinessType())){
        //新增
        balance.add(salesAgencyBalance.getBalance());
      }else{
        //减少
        balance.subtract(salesAgencyBalance.getBalance());
      }
      salesAgency.setBalance(balance);
      salesAgencyService.updateById(salesAgency);

      //新增余额表的总金额
      salesAgencyBalance.setTotalBalance(balance);
      return insert(salesAgencyBalance);
    }
    return false;
  }
}
