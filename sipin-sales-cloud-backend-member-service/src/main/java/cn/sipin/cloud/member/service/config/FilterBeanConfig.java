package cn.sipin.cloud.member.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.siyue.platform.httplog.filter.HttpServletRequestReplacedFilter;

/**
 * 配置
 */
@Configuration
public class FilterBeanConfig {
	/**
	 * 解决request流读取多次报错（java流只能读取一次）
	 * @return
	 */
	@Bean
    public HttpServletRequestReplacedFilter requestReplacedFilter() {
        return new HttpServletRequestReplacedFilter();
    }
}
