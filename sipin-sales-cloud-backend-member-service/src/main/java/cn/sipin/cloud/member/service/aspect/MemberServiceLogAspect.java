package cn.sipin.cloud.member.service.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.sipin.cloud.member.pojo.constants.SalesConstants;
import cn.sipin.cloud.member.service.util.UserLoginUtil;
import cn.siyue.platform.httplog.aspect.LogAspect;

@Aspect
@Component
public class MemberServiceLogAspect extends LogAspect {
    @Autowired
    private UserLoginUtil userLoginUtil;

    @Override
    public Long getUserId() {
        return userLoginUtil.getUserId();
    }

    @Override
    public String getSystemName() {
        return "Sales Member service";
    }

    @Override
    public List<String> buildIgnoreProperties(String className, String methodName) {
        List<String> ignoreParas = new ArrayList<>();
        Map<String,String> IGNORE_MAP = SalesConstants.getIgnoreMap();
        String paras = IGNORE_MAP.get(className+ "."+ methodName);
        if(paras != null && !"".equals(paras)){
            ignoreParas.add(paras);
        }
        return ignoreParas;
    }
}
