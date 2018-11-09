package cn.sipin.cloud.member.service.service.printSetting;

import com.baomidou.mybatisplus.service.IService;
import cn.sipin.cloud.member.pojo.pojo.printSetting.PrintSetting;
import cn.sipin.cloud.member.pojo.request.printSetting.PrintSettingRequest;
import cn.siyue.platform.base.ResponseData;

/**
 * <p>
 * 打印设置 服务类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface PrintSettingServiceContract extends IService<PrintSetting> {

  ResponseData printSetting(String shopCode,PrintSettingRequest request);

}
