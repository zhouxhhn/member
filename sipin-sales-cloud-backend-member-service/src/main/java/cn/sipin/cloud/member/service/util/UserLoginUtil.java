package cn.sipin.cloud.member.service.util;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import cn.sipin.cloud.member.pojo.constants.MemberConstants;
import cn.sipin.cloud.member.service.service.sales.JedisClusterService;

@Component
public class UserLoginUtil {

    @Autowired
    private JedisClusterService jedisClusterService;

    public Long getUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        String userId = null;
        if (StringUtils.isNotEmpty(token)) {
            userId = jedisClusterService.get(MemberConstants.REDIS_USER_SESSION_KEY + ":" + token);
        }
        if (StringUtils.isNotEmpty(userId)) {
            return new Long(userId);
        }
        return null;
    }
}
