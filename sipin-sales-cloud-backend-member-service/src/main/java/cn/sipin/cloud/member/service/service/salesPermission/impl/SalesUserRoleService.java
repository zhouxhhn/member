package cn.sipin.cloud.member.service.service.salesPermission.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import cn.sipin.cloud.member.pojo.pojo.salesPermission.SalesUserRole;
import cn.sipin.cloud.member.service.mapper.salesPermission.SalesUserRoleMapper;
import cn.sipin.cloud.member.service.service.salesPermission.SalesUserRoleServiceContract;

/**
 * <p>
 * 经销商端用户角色表 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class SalesUserRoleService extends ServiceImpl<SalesUserRoleMapper, SalesUserRole> implements SalesUserRoleServiceContract {

}
