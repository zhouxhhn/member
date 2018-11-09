/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.constants;

import java.util.HashMap;
import java.util.Map;

public class SalesConstants {

  /**
   * 余额新增
   */
  public static final String BALANCE_INCREASE = "increase";

  /**
   * 余额减少
   */
  public static final String BALANCE_DECREASE = "decrease";



  /**
   * 忽略的类和属性
   */
  public static  Map<String,String> IGNORE_MAP = new HashMap<>();

  public static Map<String,String> getIgnoreMap(){
    IGNORE_MAP.put("cn.sipin.cloud.member.service.controller.sales.backend.SalesUserController.addUser","password");
    return  IGNORE_MAP;
  }
}
