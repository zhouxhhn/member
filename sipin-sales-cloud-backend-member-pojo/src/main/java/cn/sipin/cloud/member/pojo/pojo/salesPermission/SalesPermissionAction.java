package cn.sipin.cloud.member.pojo.pojo.salesPermission;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 经销商端权限表
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Data
@TableName("sales_permission_action")
public class SalesPermissionAction extends Model<SalesPermissionAction> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 动作
     */
    @TableField("action_url")
    private String actionUrl;

    /**
     * 显示名
     */
    @TableField("display_name")
    private String displayName;
    /**
     * 权限分组
     */
    @TableField("group_id")
    private Long groupId;
    /**
     * 创建时间
     */
    @TableField("created_at")
    private LocalDateTime createdAt;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
