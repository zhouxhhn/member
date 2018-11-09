/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.constants;

/**
 * 用户常量类
 */
public class MemberConstants {

  public static final String OPERATOR_FLAG = "operator";

  public static final String BUSINESS_FLAG = "business";

  public static final String CUSTOMER_FLAG = "customer";

  /*微信登录*/
  public static final String LOGIN_WEIXIN_FLAG = "weixin";

  /*手机号+密码登录*/
  public static final String LOGIN_PWD_FLAG = "pwd";

  /*手机号+验证码登录*/
  public static final String LOGIN_CODE_FLAG = "code";

  public static final String REDIS_USER_SESSION_KEY = "REDIS_USER_SESSION";

  //Session的过期时间1天
  public static final int SSO_SESSION_EXPIRE = 86400;

  //验证码
  public static final String REDIS_IDENTITY_CODE = "REDIS_IDENTITY_CODE";

  public static final String USER_ID = "userId";

  //余额增加
  public static final String BALANCE_ADD = "add";

  //余额减少
  public static final String BALANCE_REDUCE = "reduce";

  //业务类型_订单消费
  public static final int SMALL_TYPE_ORDER_CONSUME = 0;

  //业务类型_在线充值
  public static final int SMALL_TYPE_ONLINE_RECHARSE = 1;

  //业务类型_柜台充值
  public static final int SMALL_TYPE_COUNTER_RECHARGE = 3;

  //业务类型_人工增加
  public static final int SMALL_TYPE_PERSONAL_ADD = 4;

  //业务类型_人工减少
  public static final int SMALL_TYPE_PERSONAL_REDUCE = 5;

  //业务类型_退款
  public static final int SMALL_TYPE_REFOUND = 6;

  //业务类型_活动赠送
  public static final int SMALL_TYPE_ACTIVITY_GIFT = 7;

  //业务类型_消费获得
  public static final int SMALL_TYPE_CONSUME_GET = 8;

  //业务类型_消费抵扣
  public static final int SMALL_TYPE_CONSUME_DEDUCE = 9;

  //类型_积分
  public static final int TYPE_POINTS = 0;

  //类型_余额
  public static final int TYPE_BALANCE = 1;

  public static final String SEARCH_SHOP = "page";

  //经销商端用户有效
  public static final int SALES_USER_STATUS_VALID = 0;

  //经销商端用户无效
  public static final int SALES_USER_STATUS_INVALID = 1;

  //经销商端用户系统管理员
  public static final int SALES_USER_TYPE_SYSTEM = 1;

  //经销商端用户门店管理员
  public static final int SALES_USER_TYPE_SHOP = 0;

  //权限标志
  public static final String PERMISSION_URL = "PERMISSION_URL";

  //状态有效
  public static final int STATUS_VALID=0;

  //状态无效
  public static final int STATUS_INVALID=1;

  //是否是默认地址
  public static final int DEFAULT_ADDRESS = 1;

  //不是默认地址
  public static final int NOT_DEFAULT_ADDRESS = 0;

  //角色公有
  public static final int ROLE_PUBLIC = 0;

  //角色私有
  public static final int ROLE_PRIVATE = 1;

  //系统管理员角色
  public static final int ROLE_SYSTEM = 2;

  //内部查询经销商列表的页码和数量
  public static final int AGENCY_SIZE = 100;

  //内部查询经销商列表的页码和数量
  public static final int AGENCY_PAGE = 1;

  //前台角色
  public static final int ROLE_FRONT = 0;

  //后台角色
  public static final int ROLE_BACKEND = 1;
}
