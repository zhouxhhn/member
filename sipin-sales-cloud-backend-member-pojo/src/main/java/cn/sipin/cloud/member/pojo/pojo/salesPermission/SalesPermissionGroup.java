package cn.sipin.cloud.member.pojo.pojo.salesPermission;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 经销商端权限分组
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Data
@TableName("sales_permission_group")
public class SalesPermissionGroup extends Model<SalesPermissionGroup> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 权限组名
     */
    private String name;
    /**
     * 组类型:前台或者后台
     */
    private Integer type;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
