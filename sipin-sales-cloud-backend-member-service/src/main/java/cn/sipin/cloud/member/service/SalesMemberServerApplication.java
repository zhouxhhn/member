/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import cn.sipin.cloud.member.service.service.salesPermission.InitSalesRedisService;

/**
 * 经销商基础管理服务类入口
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"cn.sipin.cloud.member.service.*","cn.siyue.platform.*"})
@MapperScan("cn.sipin.cloud.member.service.mapper*")
public class SalesMemberServerApplication implements CommandLineRunner {


  @Autowired
  InitSalesRedisService initSalesRedisService;

  public static void main(String[] args) {
    SpringApplication.run(SalesMemberServerApplication.class,"initSalesRedis");
  }

  @Override
  public void run(String... args) {
    if (args == null || args.length == 0) {
      return;
    }
    if ("initSalesRedis".equals(args[0])) {
      initSalesRedisService.initSalesRedis();
    }
  }

}
