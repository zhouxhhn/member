package cn.sipin.cloud.member.client.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import cn.siyue.platform.httplog.aspect.LogAspect;

@Aspect
@Component
public class MemberClientLogAspect extends LogAspect {
    @Override
    public Long getUserId() {
        return null;
    }

    @Override
    public String getSystemName() {
        return "Member client";
    }
}
