package cn.sipin.cloud.member.service.service.sales.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import cn.sipin.cloud.member.pojo.pojo.salesAgency.SalesAgencyPaymentHistory;
import cn.sipin.cloud.member.service.mapper.sales.SalesAgencyPaymentHistoryMapper;
import cn.sipin.cloud.member.service.service.sales.SalesAgencyPaymentHistoryServiceContract;

/**
 * <p>
 * 经销商采购支付历史表 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class SalesAgencyPaymentHistoryService extends ServiceImpl<SalesAgencyPaymentHistoryMapper, SalesAgencyPaymentHistory> implements SalesAgencyPaymentHistoryServiceContract {

}
