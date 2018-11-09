/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.service.service.sales;

/**
 * redis操作服务类
 */
public interface JedisClusterService {

  String get(String key);

  String set(String key, String value);

  String hget(String hkey, String key);

  long hset(String hkey, String key, String value);

  long incr(String key);

  long expire(String key, int second);

  long ttl(String key);

  long del(String key);

  long hdel(String hkey, String key);
}
