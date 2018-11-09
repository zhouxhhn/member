package cn.sipin.cloud.member.service.service.sales.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.sipin.cloud.member.pojo.constants.MemberConstants;
import cn.sipin.cloud.member.pojo.pojo.salesAgency.SalesAgency;
import cn.sipin.cloud.member.pojo.pojo.salesAgency.SalesAgencyAccountHistory;
import cn.sipin.cloud.member.pojo.pojo.salesAgency.SalesAgencyPaymentHistory;
import cn.sipin.cloud.member.pojo.pojo.salesShop.SalesShop;
import cn.sipin.cloud.member.pojo.pojo.salesUser.SalesUser;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.addAgency.AddAgencyRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.indexAgency.IndexAgencyRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.updateAgency.UpdateAgencyRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.updateBalance.SalesUpdateBalanceRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.agency.updateOuterCode.SalesUpdateOuterCodeRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.PurchasePaymentRequest;
import cn.sipin.cloud.member.pojo.response.sales.backend.agency.indexAgency.SalesAgencyIndexResponse;
import cn.sipin.cloud.member.service.mapper.sales.SalesAgencyMapper;
import cn.sipin.cloud.member.service.service.sales.SalesAgencyAccountHistoryServiceContract;
import cn.sipin.cloud.member.service.service.sales.SalesAgencyPaymentHistoryServiceContract;
import cn.sipin.cloud.member.service.service.sales.SalesAgencyServiceContract;
import cn.sipin.cloud.member.service.service.sales.SalesShopServiceContract;
import cn.sipin.cloud.member.service.service.sales.SalesUserServiceContract;
import cn.sipin.cloud.member.service.util.GenerateRandomUtil;
import cn.sipin.cloud.member.service.util.JsonTokenUtil;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.PaymentBackCode;
import cn.siyue.platform.constants.ResponseBackCode;

/**
 * <p>
 * 经销商 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class SalesAgencyServiceImpl extends ServiceImpl<SalesAgencyMapper, SalesAgency> implements SalesAgencyServiceContract {

  private SalesShopServiceContract salesShopService;

  private SalesAgencyAccountHistoryServiceContract salesAgencyAccountHistoryService;

  private JsonTokenUtil jsonTokenUtil;

  private SalesUserServiceContract salesUserService;

  private SalesAgencyPaymentHistoryServiceContract salesAgencyPaymentHistoryService;

  @Autowired
  public SalesAgencyServiceImpl(SalesShopServiceContract salesShopService,
                                JsonTokenUtil jsonTokenUtil,SalesUserServiceContract salesUserService,
                                SalesAgencyAccountHistoryServiceContract salesAgencyAccountHistoryService,
                                SalesAgencyPaymentHistoryServiceContract salesAgencyPaymentHistoryService){
    this.salesShopService = salesShopService;
    this.salesAgencyAccountHistoryService = salesAgencyAccountHistoryService;
    this.jsonTokenUtil = jsonTokenUtil;
    this.salesUserService = salesUserService;
    this.salesAgencyPaymentHistoryService = salesAgencyPaymentHistoryService;
  }



  /**
   * 新增经销商端信息
   */
  @Override
  public ResponseData addAgency(AddAgencyRequest addAgencyRequest) {

    SalesAgency salesAgency = new SalesAgency();
    BeanUtils.copyProperties(addAgencyRequest, salesAgency);

    //生成经销商端帐号
    String code = generateAgencyCode();
    salesAgency.setCode(code);

    //插入到经销商表
    insert(salesAgency);

    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(), ResponseBackCode.SUCCESS.getMessage());
  }

  /**
   * 更新经销商端信息
   */
  @Override
  public ResponseData updateAgency(Long agencyId, UpdateAgencyRequest updateAgencyRequest) {
    SalesAgency salesAgency =  selectById(agencyId);
    if(salesAgency == null){
      return ResponseData.build(ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getValue(), ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getMessage(),"经销商id无效，系统找不到该经销商");
    }
    //名称
    if(updateAgencyRequest.getName() != null && !"".equals(updateAgencyRequest.getName())){
      salesAgency.setName(updateAgencyRequest.getName());
    }
    //折扣
    if(updateAgencyRequest.getDiscount() != null){
      salesAgency.setDiscount(updateAgencyRequest.getDiscount());
    }
    //等级
    if(updateAgencyRequest.getGrade() != null && !"".equals(updateAgencyRequest.getGrade())){
      salesAgency.setGrade(updateAgencyRequest.getGrade());
    }
    //通讯地址
    if(updateAgencyRequest.getAddress() != null && !"".equals(updateAgencyRequest.getAddress())){
      salesAgency.setAddress(updateAgencyRequest.getAddress());
    }
    //联系电话
    if(updateAgencyRequest.getPhone() != null && !"".equals(updateAgencyRequest.getPhone())){
      salesAgency.setPhone(updateAgencyRequest.getPhone());
    }
    //联系人
    if(updateAgencyRequest.getContacts() != null && !"".equals(updateAgencyRequest.getContacts())){
      salesAgency.setContacts(updateAgencyRequest.getContacts());
    }
    //备注
    if(updateAgencyRequest.getRemark() != null && !"".equals(updateAgencyRequest.getRemark())){
      salesAgency.setRemark(updateAgencyRequest.getRemark());
    }
    updateById(salesAgency);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(), ResponseBackCode.SUCCESS.getMessage());
  }

  @Override
  public ResponseData<Page<SalesAgencyIndexResponse>> selectAgencyPage(Page<SalesAgencyIndexResponse> responsePage, IndexAgencyRequest indexAgencyRequest) {

     List<SalesAgency> salesAgencyList = baseMapper.selectAgencyPage(responsePage.getLimit(), responsePage.getOffset(), indexAgencyRequest);


    List<SalesAgencyIndexResponse> responses = new ArrayList<>(salesAgencyList.size());
    if (salesAgencyList.size() > 0) {
      salesAgencyList.forEach(agency -> {
        SalesAgencyIndexResponse salesAgencyIndexResponse = new SalesAgencyIndexResponse();
        BeanUtils.copyProperties(agency, salesAgencyIndexResponse);
        Map<String,Object> map = new HashMap<>();
        map.put("agency_code",salesAgencyIndexResponse.getCode());
        List<SalesShop> shopList = salesShopService.selectByMap(map);
        salesAgencyIndexResponse.setShopResponseList(shopList);
        responses.add(salesAgencyIndexResponse);
      });
      responsePage.setRecords(responses);
      Integer total = (baseMapper.selectAgencyPage(null, null, indexAgencyRequest)).size();
      responsePage.setTotal(total);
      return new ResponseData<>(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage(),responsePage);
    }
    return new ResponseData<>(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage(),responsePage);
  }

  /**
   * 更新余额
   */
  @Transactional(rollbackFor = Exception.class)
  @Override
  public ResponseData updateBalance(Long agencyId, SalesUpdateBalanceRequest request) {
    SalesAgency salesAgency = selectById(agencyId);
    if(salesAgency == null){
      return  ResponseData.build(ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getValue(), ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getMessage(),"经销商不存在");
    }

    SalesAgencyAccountHistory accountHistory = new SalesAgencyAccountHistory();


    accountHistory.setAgencyId(agencyId);
    int histroyType = MemberConstants.SMALL_TYPE_PERSONAL_ADD;

    BigDecimal balance = request.getAmount();
    BigDecimal totalBalance = salesAgency.getBalance();

    if(MemberConstants.BALANCE_REDUCE.equals(request.getOperateType())){

      //减少
      totalBalance = totalBalance.subtract(balance);
      histroyType = MemberConstants.SMALL_TYPE_PERSONAL_REDUCE; }
    else{
      //增加
      totalBalance = totalBalance.add(balance);

    }

    Long userId = jsonTokenUtil.getCurrentUserId();
    SalesUser salesUser = salesUserService.selectById(userId);
    accountHistory.setOperator(salesUser.getName());

    //更新用户表里的字段
    salesAgency.setBalance(totalBalance);
    updateById(salesAgency);

    accountHistory.setTerminalBalance(totalBalance);
    accountHistory.setBalance(balance);
    accountHistory.setType(MemberConstants.TYPE_BALANCE);
    accountHistory.setSmallType(histroyType);
    String orderNo = GenerateRandomUtil.generateCode(10 - userId.toString().length());
    accountHistory.setOrderNo(System.currentTimeMillis()+userId+orderNo);

    salesAgencyAccountHistoryService.insert(accountHistory);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage());
  }

  @Override
  public ResponseData getAgencyInfo() {
    Long userId = jsonTokenUtil.getCurrentUserId();
    SalesAgency salesAgency = baseMapper.getAgencyInfo(userId);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage(),salesAgency);
  }

  /**
   * 采购订单支付接口
   */
  @Transactional(rollbackFor = Exception.class)
  @Override
  public ResponseData purchasePayment(PurchasePaymentRequest request) {

    //查找是否是重复提交单号，同一个单号不能重复扣款
    String orderNo = request.getPurchaseOrderNo();
    Map<String,Object> orderNoMap = new HashMap<>();
    orderNoMap.put("order_no",orderNo);
    List<SalesAgencyAccountHistory> accountHistoryList =  salesAgencyAccountHistoryService.selectByMap(orderNoMap);
    if(accountHistoryList != null && accountHistoryList.size() > 0){
      return ResponseData.build(PaymentBackCode.DUPLICATE_TRANSACTION.getValue(), PaymentBackCode.DUPLICATE_TRANSACTION.getMessage(),"该订单不能重复扣款");
    }

    Long userId = jsonTokenUtil.getCurrentUserId();
    ResponseData responseData = salesUserService.getUserByToken(userId);
    Map map = (HashMap)responseData.getData();
    //1. 经销商的金额与订单金额比较，如果大于，则扣款；如果小于则提示余额不足
    String agencyCode = (String)map.get("agencyCode");
    Map agencyMap = new HashMap();
    agencyMap.put("code",agencyCode);
    List<SalesAgency> agencyList = selectByMap(agencyMap);
    if(agencyList == null && agencyList.size() == 0){
      return ResponseData.build(PaymentBackCode.ACCOUT_NOT_EXISTENCE.getValue(), PaymentBackCode.ACCOUT_NOT_EXISTENCE.getMessage(),"该经销商不存在");
    }
    SalesAgency agency = agencyList.get(0);
    //余额不足，不能下采购
    if(agency.getBalance().compareTo(request.getAmount()) < 0){
      return ResponseData.build(PaymentBackCode.INVALID_AMOUNT.getValue(), PaymentBackCode.INVALID_AMOUNT.getMessage(),"余额不足，请先充值");
    }
    BigDecimal totalBalance = agency.getBalance().subtract(request.getAmount());
    agency.setBalance(totalBalance);
    updateById(agency);

    //2. 把数据插入到经销商余额历史记录表
    SalesAgencyAccountHistory accountHistory = new SalesAgencyAccountHistory();
    accountHistory.setAgencyId(agency.getId());
    accountHistory.setOperator((String)map.get("userName"));
    accountHistory.setTerminalBalance(totalBalance);
    accountHistory.setBalance(request.getAmount());
    accountHistory.setType(MemberConstants.TYPE_BALANCE);
    accountHistory.setSmallType(MemberConstants.SMALL_TYPE_ORDER_CONSUME);
    accountHistory.setOrderNo(request.getPurchaseOrderNo());
    salesAgencyAccountHistoryService.insert(accountHistory);

    //3. 把数据插入到经销商支付记录表
    SalesAgencyPaymentHistory paymentHistory = new SalesAgencyPaymentHistory();
    paymentHistory.setAgencyId(agency.getId());
    paymentHistory.setAmount(request.getAmount());
    paymentHistory.setOrderNo(request.getPurchaseOrderNo());
    String paymentNo = System.currentTimeMillis()+GenerateRandomUtil.generateCode(5);
    paymentHistory.setPaymentNo(paymentNo);
    salesAgencyPaymentHistoryService.insert(paymentHistory);

    //4. 把支付流水号返回
    return ResponseData.build(PaymentBackCode.SUCCESS.getValue(), PaymentBackCode.SUCCESS.getMessage(),paymentNo);
  }

  @Override
  public ResponseData purchasePayment(String purchaseOrderNo) {
    Map<String,Object> map = new HashMap<>();
    map.put("order_no",purchaseOrderNo);
    List<SalesAgencyPaymentHistory> paymentHistoryList = salesAgencyPaymentHistoryService.selectByMap(map);
    if(paymentHistoryList == null || paymentHistoryList.size() <= 0){
      return ResponseData.build(ResponseBackCode.ERROR_NOT_FOUND.getValue(), ResponseBackCode.ERROR_NOT_FOUND.getMessage(),"根据该订单号"+purchaseOrderNo+",未找到支付流水号");
    }
    SalesAgencyPaymentHistory paymentHistory = paymentHistoryList.get(0);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(), ResponseBackCode.SUCCESS.getMessage(),paymentHistory.getPaymentNo());
  }

  @Override
  public ResponseData updateOuterCode(Long agencyId, SalesUpdateOuterCodeRequest request) {

    //查找经销商id
    SalesAgency salesAgency = selectById(agencyId);
    if(salesAgency == null){
      return ResponseData.build(ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getValue(), ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getMessage(),"经销商不存在");
    }
    salesAgency.setOuterCode(request.getOuterCode());
    updateById(salesAgency);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(), ResponseBackCode.SUCCESS.getMessage());
  }

  /**
   * 生成5位数的code
   * 例：A00001
   */
  private String generateAgencyCode(){

    String code = "A00001";
    List<SalesAgency> list = selectList(new EntityWrapper<>());
    Map<String,Long> map = new HashMap<>();
    if(list != null && list.size() > 0){
      for (SalesAgency salesAgency:list){
        map.put(salesAgency.getCode(),salesAgency.getId());
      }
      String generateCode = "A"+GenerateRandomUtil.generateCode(5);
      //生成code,判断是否重复
      while (map.get(generateCode) != null){
        generateCode = "A"+GenerateRandomUtil.generateCode(5);
      }
      code = generateCode;
    }
    return code;
  }



}
