package cn.sipin.cloud.member.pojo.pojo.salesUser;


import cn.sipin.cloud.member.pojo.pojo.printSetting.PrintSetting;
import cn.sipin.cloud.member.pojo.pojo.salesUser.SalesUser;
import lombok.Data;

/**
 * <p>
 * 门店管理员组合类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Data
public class SalesUserCompose {

  private SalesUser salesUser;

  private String token;

  private PrintSetting printSetting = new PrintSetting();


}
