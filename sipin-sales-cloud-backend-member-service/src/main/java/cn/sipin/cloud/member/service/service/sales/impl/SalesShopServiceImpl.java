package cn.sipin.cloud.member.service.service.sales.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.sipin.cloud.member.pojo.constants.MemberConstants;
import cn.sipin.cloud.member.pojo.pojo.salesShop.SalesShop;
import cn.sipin.cloud.member.pojo.pojo.salesUser.SalesUser;
import cn.sipin.cloud.member.pojo.request.sales.backend.shop.addShop.AddShopRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.shop.index.IndexShopRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.shop.updateShop.UpdateShopRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.shop.updateSourceIdRequest.SalesUpdateSourceIdRequest;
import cn.sipin.cloud.member.pojo.response.sales.backend.shop.index.IndexShopResponse;
import cn.sipin.cloud.member.pojo.response.sales.backend.shop.searchShop.SearchShopResponse;
import cn.sipin.cloud.member.service.mapper.sales.SalesShopMapper;
import cn.sipin.cloud.member.service.service.sales.SalesShopServiceContract;
import cn.sipin.cloud.member.service.service.sales.SalesUserServiceContract;
import cn.sipin.cloud.member.service.util.GenerateRandomUtil;
import cn.sipin.cloud.member.service.util.JsonTokenUtil;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;

/**
 * <p>
 * 门店 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class SalesShopServiceImpl extends ServiceImpl<SalesShopMapper, SalesShop> implements SalesShopServiceContract {

  private JsonTokenUtil jsonTokenUtil;

  private SalesUserServiceContract salesUserService;

  @Autowired
  public SalesShopServiceImpl(JsonTokenUtil jsonTokenUtil, SalesUserServiceContract salesUserService){
    this.jsonTokenUtil = jsonTokenUtil;
    this.salesUserService = salesUserService;
  }

  @Override
  public ResponseData addShop(AddShopRequest addShopRequest) {

    //查找门店名称是否重复
    Map<String,Object> map = new HashMap<>();
    map.put("name",addShopRequest.getName());
    List<SalesShop> shopList = selectByMap(map);
    if(shopList != null && shopList.size() > 0){
      return ResponseData.build(ResponseBackCode.ERROR_OBJECT_EXIST.getValue(), ResponseBackCode.ERROR_OBJECT_EXIST.getMessage(),"门店名称重复，请输入其它名字");
    }

    SalesShop salesShop = new SalesShop();
    BeanUtils.copyProperties(addShopRequest, salesShop);

    //生成经销商端帐号
    String code = generateShopCode();
    salesShop.setCode(code);

    //插入到经销商表
    insert(salesShop);

    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(), ResponseBackCode.SUCCESS.getMessage());
  }

  @Override
  public ResponseData deleteShop(Long shopId) {
    deleteById(shopId);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage());
  }

  @Override
  public ResponseData<Page<IndexShopResponse>> index(int page, int size, IndexShopRequest request) {
    //得到经销商的code
    Long userId = jsonTokenUtil.getCurrentUserId();

    SalesUser salesUser = salesUserService.selectById(userId);
    List<SalesShop> salesShopList = null;
    List<SalesShop> shopListTotal = null;
    Page userPage = new Page<SalesShop>(page, size);
    userPage.setAsc(false);
    if(salesUser.getShopCode() !=null && !"".equals(salesUser.getShopCode())){
      Map<String,Object> map = new HashMap<>();
      map.put("code",salesUser.getShopCode());
      List<SalesShop> list =  selectByMap(map);
      if(list != null && list.size() > 0){
        SalesShop salesShop = list.get(0);
        salesShopList = baseMapper.selectShopPage(userPage.getLimit(), userPage.getOffset(), salesShop.getAgencyCode(),request);
        shopListTotal = baseMapper.selectShopPage(null, null, salesShop.getAgencyCode(),request);
      }
    }else{
      salesShopList = baseMapper.selectShopPage(userPage.getLimit(), userPage.getOffset(),null,request);
      shopListTotal = baseMapper.selectShopPage(null,null,null,request);
    }
    List<IndexShopResponse> responseList = new ArrayList<>();
    if(salesShopList != null && salesShopList.size() > 0){
      for (SalesShop shop:salesShopList){
        IndexShopResponse response = new IndexShopResponse();
        BeanUtils.copyProperties(shop,response);
        responseList.add(response);
      }
    }
    if(shopListTotal != null && shopListTotal.size() > 0){
      userPage.setTotal(shopListTotal.size());
    }
    userPage.setRecords(responseList);
    return new ResponseData<>(ResponseBackCode.SUCCESS.getValue(),
                              ResponseBackCode.SUCCESS.getMessage(), userPage);
  }

  @Override
  public ResponseData searchShop(Long shopId) {
    SalesShop salesShop = selectById(shopId);
    if(salesShop == null) {
      return ResponseData.build(ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getValue(), ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getMessage(),"传入的门店id有误，找不到门店");
    }
    SearchShopResponse response = new SearchShopResponse();
    BeanUtils.copyProperties(salesShop,response);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage(),response);
  }

  @Override
  public ResponseData updateShop(Long shopId,UpdateShopRequest updateShopRequest) {
    SalesShop shop = selectById(shopId);
    if(shop == null) {
      return ResponseData.build(ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getValue(), ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getMessage(),"传入的id有误，该门店不存在");
    }
    BeanUtils.copyProperties(updateShopRequest,shop);
    updateById(shop);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage() );
  }

  @Override
  public ResponseData searchAllShop(String type, int page, int size) {
    Page userPage = new Page<SalesShop>(page, size);
    userPage.setAsc(false);

    if(MemberConstants.SEARCH_SHOP.equals(type)){
      //分页请求
      userPage = selectPage(userPage, new EntityWrapper<>());
      return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),
                                ResponseBackCode.SUCCESS.getMessage(), userPage);
    }else{
      //不是分页请求
      List<SalesShop> list = selectList(new EntityWrapper<>());
      if(list != null && list.size() > 0){
        userPage.setRecords(list);
        userPage.setTotal(list.size());
      }
      return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),
                                ResponseBackCode.SUCCESS.getMessage(), userPage);
    }
  }

  @Override
  public ResponseData updateSourceId(Long shopId, SalesUpdateSourceIdRequest request) {

    //查找经销商id
    SalesShop salesShop = selectById(shopId);
    if(salesShop == null){
      return ResponseData.build(ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getValue(), ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getMessage(),"门店不存在");
    }
    salesShop.setSourceId(request.getSourceId());
    updateById(salesShop);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(), ResponseBackCode.SUCCESS.getMessage());
  }

  /**
   * 生成5位数的code
   * 例：A00001
   */
  private String generateShopCode(){

    String code = "S00001";
    List<SalesShop> list = selectList(new EntityWrapper<>());
    Map<String,Long> map = new HashMap<>();
    if(list != null && list.size() > 0){
      for (SalesShop salesShop:list){
        map.put(salesShop.getCode(),salesShop.getId());
      }
      String generateCode = "S" + GenerateRandomUtil.generateCode(6);
      //生成code,判断是否重复
      while (map.get(generateCode) != null){
        generateCode = "S" + GenerateRandomUtil.generateCode(6);
      }
      code = generateCode;
    }
    return code;
  }

}
