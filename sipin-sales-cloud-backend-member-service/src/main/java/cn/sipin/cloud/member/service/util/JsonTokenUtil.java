/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.service.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import cn.sipin.cloud.member.service.service.sales.MemberUserLoginTokenService;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import cn.siyue.platform.exceptions.exception.PermissionDenyException;

@Component
public class JsonTokenUtil {

  @Autowired
  private MemberUserLoginTokenService memberUserLoginTokenService;

  /**
   * 根据token获取用户id
   */
  public Long getCurrentUserId(){
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    String token = request.getHeader("token");
    ResponseData responseData = memberUserLoginTokenService.checkToken(token);
    if (!responseData.getCode().equals(ResponseBackCode.SUCCESS.getValue())) {
      throw new PermissionDenyException(
          ResponseBackCode.ERROR_TOKEN_TIMEOUT_CODE.getValue(),
          ResponseBackCode.ERROR_TOKEN_TIMEOUT_CODE.getMessage()
      );
    }
    return Long.valueOf((String)responseData.getData());
  }
}
