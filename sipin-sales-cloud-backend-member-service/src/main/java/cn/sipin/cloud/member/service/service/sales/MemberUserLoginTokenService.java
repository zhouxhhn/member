/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.service.service.sales;

import cn.siyue.platform.base.ResponseData;

/**
 * 用户登录、token验证
 */
public interface MemberUserLoginTokenService {

  /**
   * token验证 用redis保存、验证
   *
   * @param token 传入验证token的值
   */
  ResponseData checkToken(String token);

  /**
   * 根据token，获取用户信息
   */
  ResponseData getUserByToken(String token);


}
