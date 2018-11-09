package cn.sipin.cloud.member.service.service.sales.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.sipin.cloud.member.pojo.constants.MemberConstants;
import cn.sipin.cloud.member.pojo.pojo.region.Region;
import cn.sipin.cloud.member.pojo.pojo.salesAgency.SalesAgencyDelivery;
import cn.sipin.cloud.member.pojo.pojo.salesShop.SalesShop;
import cn.sipin.cloud.member.pojo.pojo.salesUser.SalesUser;
import cn.sipin.cloud.member.pojo.request.sales.backend.delivery.addAddress.SalesAgencyDeliveryRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.delivery.updateAddress.UpdateSalesAgencyDeliveryRequest;
import cn.sipin.cloud.member.pojo.request.sales.front.delivery.addAddress.SaveSalesAgencyFrontDeliveryRequest;
import cn.sipin.cloud.member.pojo.response.sales.backend.delivery.SalesAgencyDeliveryResponse;
import cn.sipin.cloud.member.pojo.response.sales.front.delivery.SalesAgencyFrontDeliveryResponse;
import cn.sipin.cloud.member.service.mapper.sales.SalesAgencyDeliveryMapper;
import cn.sipin.cloud.member.service.service.region.RegionServiceContract;
import cn.sipin.cloud.member.service.service.sales.SalesAgencyDeliveryServiceContract;
import cn.sipin.cloud.member.service.service.sales.SalesShopServiceContract;
import cn.sipin.cloud.member.service.service.sales.SalesUserServiceContract;
import cn.sipin.cloud.member.service.util.JsonTokenUtil;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;

/**
 * <p>
 * 经销商配送信息表 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class SalesAgencyDeliveryServiceImpl extends ServiceImpl<SalesAgencyDeliveryMapper, SalesAgencyDelivery> implements SalesAgencyDeliveryServiceContract {

  private RegionServiceContract regionService;

  private JsonTokenUtil jsonTokenUtil;

  private SalesUserServiceContract salesUserService;

  private SalesShopServiceContract salesShopService;



  @Autowired
  public SalesAgencyDeliveryServiceImpl(RegionServiceContract regionService,JsonTokenUtil jsonTokenUtil,
                                        SalesUserServiceContract salesUserService,SalesShopServiceContract salesShopService){
    this.regionService = regionService;
    this.jsonTokenUtil = jsonTokenUtil;
    this.salesUserService = salesUserService;
    this.salesShopService = salesShopService;
  }

  /**
   * 设置默认地址
   */
  @Transactional(rollbackFor = Exception.class)
  @Override
  public ResponseData setDefaultAddress(Long addressId) {
    //获取门店id
    SalesAgencyDelivery salesAgencyDelivery = selectById(addressId);
    Long shopId = salesAgencyDelivery.getShopId();
    Map<String,Object> map = new HashMap<>();
    map.put("shop_id",shopId);
    List<SalesAgencyDelivery> list = selectByMap(map);
    if(list != null && list.size() > 0){
      int len = list.size();
      for(int i = 0; i < len;i++){
        SalesAgencyDelivery storageDelivery = list.get(i);
        //设置默认地址为1
        if(storageDelivery.getId().equals(salesAgencyDelivery.getId())){
          storageDelivery.setDefaultAddress(1);
        }else{
          storageDelivery.setDefaultAddress(0);
        }
        list.set(i,storageDelivery);
      }
      updateBatchById(list);
    }
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(), ResponseBackCode.SUCCESS.getMessage());
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public ResponseData addAddress(SalesAgencyDeliveryRequest request) {

    //门店id
    Long shopId = request.getShopId();
    Map<String,Object> map = new HashMap<>();
    map.put("shop_id",shopId);
    List<SalesAgencyDelivery> deliveryList = selectByMap(map);
    if(deliveryList == null || deliveryList.size() == 0){
      request.setDefaultAddress(MemberConstants.DEFAULT_ADDRESS);
    }

    //设置full_address地址：省市区+详细地址
    String fullAddress =  regionService.getFullAddress(request.getProvinceCode(),request.getCityCode(),
                                                       request.getDistrictCode(),request.getAddress());

    //传入的省市区code无效
    if(fullAddress == null){
      return new ResponseData<>(ResponseBackCode.ERROR_CREATE_FAIL.getValue(), "传入的省市区code无效");
    }
    SalesAgencyDelivery salesAgencyDelivery = new SalesAgencyDelivery();

    //保存地址
    BeanUtils.copyProperties(request, salesAgencyDelivery);
    salesAgencyDelivery.setFullAddress(fullAddress);

    //是否是默认地址
    if(MemberConstants.DEFAULT_ADDRESS == salesAgencyDelivery.getDefaultAddress()){

      List<SalesAgencyDelivery> salesAgencyDeliveryList =getDefaultDelivery(request.getShopId());
      if(salesAgencyDeliveryList != null && salesAgencyDeliveryList.size() > 0) {
        updateBatchById(salesAgencyDeliveryList);
      }
    }
    insert(salesAgencyDelivery);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage());

  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public ResponseData updateAddress(Long addressId, UpdateSalesAgencyDeliveryRequest request) {
    SalesAgencyDelivery salesAgencyDelivery = selectById(addressId);
    if(salesAgencyDelivery == null){
      return ResponseData.build(ResponseBackCode.ERROR_PARAM_INVALID.getValue(),ResponseBackCode.ERROR_PARAM_INVALID.getMessage(),"地址id无效");
    }

    //设置full_address地址：省市区+详细地址
    String fullAddress =  regionService.getFullAddress(request.getProvinceCode(),request.getCityCode(),
                                                       request.getDistrictCode(),request.getAddress());

    //传入的省市区code无效
    if(fullAddress == null){
      return ResponseData.build(ResponseBackCode.ERROR_CREATE_FAIL.getValue(),ResponseBackCode.ERROR_CREATE_FAIL.getMessage(),"传入的省市区code无效");
    }
    SalesAgencyDelivery agencyDelivery = new SalesAgencyDelivery();
    BeanUtils.copyProperties(request,agencyDelivery);
    agencyDelivery.setId(addressId);
    agencyDelivery.setFullAddress(fullAddress);

    //是否是默认地址
    if(MemberConstants.DEFAULT_ADDRESS == agencyDelivery.getDefaultAddress()){
        List<SalesAgencyDelivery> salesAgencyDeliveryList =getDefaultDelivery(request.getShopId());
        if(salesAgencyDeliveryList != null && salesAgencyDeliveryList.size() > 0) {
          updateBatchById(salesAgencyDeliveryList);
        }
    }
    updateById(agencyDelivery);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage());

  }

  @Override
  public ResponseData deleteAddress(Long addressId) {
    SalesAgencyDelivery salesAgencyDelivery = selectById(addressId);
    salesAgencyDelivery.setStatus(1);
    updateById(salesAgencyDelivery);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage());

  }

  @Override
  public ResponseData<SalesAgencyDeliveryResponse> searchAddress(Long addressId) {
    SalesAgencyDelivery salesAgencyDelivery = selectById(addressId);
    SalesAgencyDeliveryResponse response = new SalesAgencyDeliveryResponse();
    BeanUtils.copyProperties(salesAgencyDelivery,response);
    return new ResponseData<>(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage(), response);

  }

  @Override
  public ResponseData<Page> index(Long shopId, int page, int size) {
    Page userPage = new Page<SalesAgencyDeliveryResponse>(page, size);
    userPage.setAsc(false);
    userPage = selectPage(userPage, new EntityWrapper<SalesAgencyDelivery>().eq("shop_id", shopId).eq("status", 0));
    return new ResponseData<>(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage(), userPage);

  }

  @Override
  public ResponseData<Page> searchAllAddress(int page, int size) {
    Long userId = jsonTokenUtil.getCurrentUserId();
    SalesUser salesUser = salesUserService.selectById(userId);
    String shopCode = salesUser.getShopCode();
    Map<String,Object> map = new HashMap<>();
    map.put("code",shopCode);
    List<SalesShop> shopList = salesShopService.selectByMap(map);
    if(shopList != null && shopList.size() >0){
      SalesShop salesShop = shopList.get(0);
      Long shopId = salesShop.getId();
      Page userPage = new Page<SalesAgencyFrontDeliveryResponse> (page, size);
      userPage.setAsc(false);
      userPage = selectPage(userPage, new EntityWrapper<SalesAgencyDelivery>().eq("shop_id", shopId).eq("status", 0));
      //放入省市区的中文
      List<SalesAgencyDelivery> deliveryList = userPage.getRecords();
      List<SalesAgencyFrontDeliveryResponse> responseList = new ArrayList<>();
      if(deliveryList != null){
        for(int i = 0,length = deliveryList.size();i<length;i++){
          SalesAgencyDelivery salesAgencyDelivery = deliveryList.get(i);
          SalesAgencyFrontDeliveryResponse response = new SalesAgencyFrontDeliveryResponse();
          BeanUtils.copyProperties(salesAgencyDelivery,response);

          List<Long> regionIds = new ArrayList<>();
          regionIds.add(salesAgencyDelivery.getProvinceCode());
          regionIds.add(salesAgencyDelivery.getCityCode());
          regionIds.add(salesAgencyDelivery.getDistrictCode());
          List<Region> regions =regionService.selectByRegionId(regionIds);
          if(regions != null) {
            for (Region region : regions) {
              if (region.getId().equals(salesAgencyDelivery.getProvinceCode())) {
                response.setProvinceName(region.getName());
              }
              if (region.getId().equals(salesAgencyDelivery.getCityCode())) {
                response.setCityName(region.getName());
              }
              if (region.getId().equals(salesAgencyDelivery.getDistrictCode())) {
                response.setDistrictName(region.getName());
              }
            }
            responseList.add(response);
          }
        }
        userPage.setRecords(responseList);
      }
      return new ResponseData<>(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage(), userPage);
    }
    return new ResponseData<>(ResponseBackCode.ERROR_NOT_FOUND.getValue(),"找不到相应的门店，该用户无效", null);
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public ResponseData frontAddAddress(SaveSalesAgencyFrontDeliveryRequest request) {
    //设置full_address地址：省市区+详细地址
    String fullAddress =  regionService.getFullAddress(request.getProvinceCode(),request.getCityCode(),
                                                       request.getDistrictCode(),request.getAddress());

    //传入的省市区code无效
    if(fullAddress == null){
      return new ResponseData<>(ResponseBackCode.ERROR_CREATE_FAIL.getValue(), "传入的省市区code无效");
    }
    SalesAgencyDelivery salesAgencyDelivery = new SalesAgencyDelivery();

    //查找shopId
    Long userId = jsonTokenUtil.getCurrentUserId();
    SalesUser salesUser = salesUserService.selectById(userId);
    String shopCode = salesUser.getShopCode();
    Map<String,Object> map = new HashMap<>();
    map.put("code",shopCode);
    List<SalesShop> shopList = salesShopService.selectByMap(map);
    if(shopList != null && shopList.size() >0) {
      SalesShop salesShop = shopList.get(0);
      Long shopId = salesShop.getId();
      //保存地址
      BeanUtils.copyProperties(request, salesAgencyDelivery);
      salesAgencyDelivery.setShopId(shopId);
      salesAgencyDelivery.setFullAddress(fullAddress);

      if(MemberConstants.DEFAULT_ADDRESS == salesAgencyDelivery.getDefaultAddress()){
        List<SalesAgencyDelivery> salesAgencyDeliveryList =getDefaultDelivery(shopId);
        if(salesAgencyDeliveryList != null && salesAgencyDeliveryList.size() > 0) {
          updateBatchById(salesAgencyDeliveryList);
        }
      }

      insert(salesAgencyDelivery);
      SalesAgencyFrontDeliveryResponse addResponse = new SalesAgencyFrontDeliveryResponse();
      BeanUtils.copyProperties(salesAgencyDelivery, addResponse);
      List<Long> regionIds = new ArrayList<>();
      regionIds.add(addResponse.getProvinceCode());
      regionIds.add(addResponse.getCityCode());
      regionIds.add(addResponse.getDistrictCode());
      List<Region> regionsList =regionService.selectByRegionId(regionIds);
      if(regionsList != null) {
        for (Region region : regionsList) {
          if (region.getId().equals(salesAgencyDelivery.getProvinceCode())) {
            addResponse.setProvinceName(region.getName());
          }else if (region.getId().equals(salesAgencyDelivery.getCityCode())) {
            addResponse.setCityName(region.getName());
          }else if (region.getId().equals(salesAgencyDelivery.getDistrictCode())) {
            addResponse.setDistrictName(region.getName());
          }
        }
      }
      return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage(),addResponse);
    }
    return new ResponseData<>(ResponseBackCode.ERROR_NOT_FOUND.getValue(),"找不到相应的门店，该用户无效", null);
  }

  private List<SalesAgencyDelivery> getDefaultDelivery(Long shopId) {
    //查看有没有其它的默认地址，如果有，则设置其它的地址不为默认
    Map<String, Object> defaultMap = new HashMap<>();
    defaultMap.put("default_address", MemberConstants.DEFAULT_ADDRESS);
    defaultMap.put("shop_id", shopId);
    List<SalesAgencyDelivery> agencyDeliveryList = selectByMap(defaultMap);
    if (agencyDeliveryList != null && agencyDeliveryList.size() > 0) {
      for (int i = 0, length = agencyDeliveryList.size(); i < length; i++) {
        SalesAgencyDelivery delivery = agencyDeliveryList.get(i);
        delivery.setDefaultAddress(MemberConstants.NOT_DEFAULT_ADDRESS);
        agencyDeliveryList.set(i, delivery);
      }
    }
    return agencyDeliveryList;
  }
}
