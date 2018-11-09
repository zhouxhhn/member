package cn.sipin.cloud.member.pojo.pojo.salesPermission;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
import java.time.LocalDateTime;

import cn.sipin.cloud.member.pojo.pojo.salesShop.SalesShop;
import lombok.Data;

/**
 * <p>
 * 经销商角色表
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Data
@TableName("sales_role")
public class SalesRole extends Model<SalesRole> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 编号
     */
    private String code;
    /**
     * 子系统
     */
    @TableField("children_system")
    private Integer childrenSystem;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 可用范围
     */
    private Long scope;
    /**
     * 创建人id
     */
    private Long creator;

    /**
     * 创建人
     */
    @TableField(exist = false)
    private String creatorName;

    /**
     * 门店范围
     */
    @TableField(exist = false)
    private SalesShop salesShop = new SalesShop();

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
