package cn.sipin.cloud.member.service.service.salesPermission.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import cn.sipin.cloud.member.pojo.pojo.salesPermission.SalesPermissionGroup;
import cn.sipin.cloud.member.service.mapper.salesPermission.SalesPermissionGroupMapper;
import cn.sipin.cloud.member.service.service.salesPermission.SalesPermissionGroupServiceContract;

/**
 * <p>
 * 经销商端权限分组 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class SalesPermissionGroupService extends ServiceImpl<SalesPermissionGroupMapper, SalesPermissionGroup> implements SalesPermissionGroupServiceContract {

}
