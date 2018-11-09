/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.service.service.sales.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.sipin.cloud.member.service.service.sales.JedisClusterService;
import redis.clients.jedis.JedisCluster;

/*
 * redis操作实现类
 */
@Service
public class JedisClusterServiceImpl implements JedisClusterService {

  private JedisCluster jedisCluster;

  @Autowired
  public JedisClusterServiceImpl(JedisCluster jedisCluster){
    this.jedisCluster = jedisCluster;
  }

  @Override
  public String get(String key) {
    return jedisCluster.get(key);
  }

  @Override
  public String set(String key, String value) {
    return jedisCluster.set(key, value);
  }

  @Override
  public String hget(String hkey, String key) {
    return jedisCluster.hget(hkey, key);
  }

  @Override
  public long hset(String hkey, String key, String value) {
    return jedisCluster.hset(hkey, key, value);
  }

  @Override
  public long incr(String key) {
    return jedisCluster.incr(key);
  }

  @Override
  public long expire(String key, int second) {
    return jedisCluster.expire(key, second);
  }

  @Override
  public long ttl(String key) {
    return jedisCluster.ttl(key);
  }

  @Override
  public long del(String key) {

    return jedisCluster.del(key);
  }

  @Override
  public long hdel(String hkey, String key) {

    return jedisCluster.hdel(hkey, key);
  }
}
