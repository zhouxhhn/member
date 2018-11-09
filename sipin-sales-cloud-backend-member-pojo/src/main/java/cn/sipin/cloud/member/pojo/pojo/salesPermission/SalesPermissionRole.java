package cn.sipin.cloud.member.pojo.pojo.salesPermission;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 经销商端权限角色对应表
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Data
@TableName("sales_permission_role")
public class SalesPermissionRole extends Model<SalesPermissionRole> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 角色id
     */
    @TableField("role_id")
    private Long roleId;
    /**
     * 权限id
     */
    @TableField("permission_id")
    private Long permissionId;
    /**
     * 权限组id
     */
    @TableField("permission_group_id")
    private Long permissionGroupId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
