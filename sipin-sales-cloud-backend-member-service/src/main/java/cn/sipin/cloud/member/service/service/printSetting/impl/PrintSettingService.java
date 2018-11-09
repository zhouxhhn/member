package cn.sipin.cloud.member.service.service.printSetting.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.sipin.cloud.member.pojo.pojo.printSetting.PrintSetting;
import cn.sipin.cloud.member.pojo.request.printSetting.PrintSettingRequest;
import cn.sipin.cloud.member.service.mapper.printSetting.PrintSettingMapper;
import cn.sipin.cloud.member.service.service.printSetting.PrintSettingServiceContract;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;

/**
 * <p>
 * 打印设置 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class PrintSettingService extends ServiceImpl<PrintSettingMapper, PrintSetting> implements PrintSettingServiceContract {




  @Override
  public ResponseData printSetting(String shopCode, PrintSettingRequest request) {

    //从数据库里获取该门店的打印设置信息
    Map<String,Object> printSettingMap = new HashMap<>();
    printSettingMap.put("shop_code",shopCode);
    List<PrintSetting> printSettingList = selectByMap(printSettingMap);
    PrintSetting printSetting = new PrintSetting();
    printSetting.setShopCode(shopCode);
    if(printSettingList != null && printSettingList.size() > 0){
      printSetting = printSettingList.get(0);
    }

    //pos机ip
    if(request.getPosIp() != null && !"".equals(request.getPosIp())){
      printSetting.setPosIp(request.getPosIp());
    }

    //打印机
    if(request.getPrinter() != null){
      printSetting.setPrinter(request.getPrinter());
    }

    //打印主机IP
    if(request.getPrinterIp() != null && !"".equals(request.getPrinterIp())){
      printSetting.setPrinterIp(request.getPrinterIp());
    }
    //店铺地址
    if(request.getShopAddress() != null && !"".equals(request.getShopAddress())){
      printSetting.setShopAddress(request.getShopAddress());
    }
    //店铺电话
    if(request.getShopPhone() != null && !"".equals(request.getShopPhone())){
      printSetting.setShopPhone(request.getShopPhone());
    }
    //票尾提示
    if(request.getTicketTips() != null && !"".equals(request.getTicketTips())){
      printSetting.setTicketTips(request.getTicketTips());
    }
    //打印机类型
    if(request.getPrinterType() != null && !"".equals(request.getPrinterType())){
      printSetting.setPrinterType(request.getPrinterType());
    }
    //Pos类型
    if(request.getPosType() != null && !"".equals(request.getPosType())){
      printSetting.setPosType(request.getPosType());
    }
    insertOrUpdate(printSetting);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(), ResponseBackCode.SUCCESS.getMessage(),"保存成功");
  }
}
