package cn.sipin.cloud.member.pojo.pojo.salesUser;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import cn.sipin.cloud.member.pojo.pojo.salesPermission.SalesPermissionAction;
import cn.sipin.cloud.member.pojo.pojo.salesPermission.SalesRole;
import lombok.Data;

/**
 * <p>
 * 门店员工表
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@JsonSerialize(include= Inclusion.ALWAYS)
@Data
@TableName("sales_user")
public class SalesUser extends Model<SalesUser> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 员工帐号
     */
    private String code;

    /**
      * 员工工号
     */
    private String empno;

    /**
     * 姓名
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 门店code
     */
    @TableField("shop_code")
    private String shopCode;

    /**
     * 门店code
     */
    @TableField(exist = false)
    private String shopName;

    /**
     * 类型
     */
    private Integer type;
    /**
     * 创建时间
     */
    @TableField("created_at")
    private LocalDateTime createdAt;

    /**
     * 类型
     */
    private Integer status = 0;

    @TableField(exist = false)
    private List<SalesPermissionAction> permissionActionList;

    @TableField(exist = false)
    private List<SalesRole> roleList;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
