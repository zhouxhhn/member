package cn.sipin.cloud.member.pojo.pojo.salesAgency;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * <p>
 * 经销商帐户历史表
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Data
@TableName("sales_agency_account_history")
public class SalesAgencyAccountHistory extends Model<SalesAgencyAccountHistory> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 单号
     */
    @TableField("order_no")
    private String orderNo;


    @TableField("serial_no")
    private String serialNo;

    /**
     * 经销商id
     */
    @TableField("agency_id")
    private Long agencyId;
    /**
     * 积分
     */
    private BigDecimal points;
    /**
     * 期末积分
     */
    @TableField("terminal_points")
    private BigDecimal terminalPoints;
    /**
     * 余额
     */
    private BigDecimal balance;
    /**
     * 期末余额
     */
    @TableField("terminal_balance")
    private BigDecimal terminalBalance;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 每种类型中的小类型
     */
    @TableField("small_type")
    private Integer smallType;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 创建时间
     */
    @TableField("create_at")
    private LocalDateTime createAt;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
