package cn.sipin.cloud.member.service.service.sales.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import cn.sipin.cloud.member.pojo.pojo.salesAgency.SalesAgencyAccountHistory;
import cn.sipin.cloud.member.service.mapper.sales.SalesAgencyAccountHistoryMapper;
import cn.sipin.cloud.member.service.service.sales.SalesAgencyAccountHistoryServiceContract;

/**
 * <p>
 * 经销商帐户历史表 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class SalesAgencyAccountHistoryService extends ServiceImpl<SalesAgencyAccountHistoryMapper, SalesAgencyAccountHistory> implements SalesAgencyAccountHistoryServiceContract {

}
