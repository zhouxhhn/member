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
import lombok.experimental.Accessors;

/**
 * <p>
 * 经销商采购支付历史表
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Data
@Accessors(chain = true)
@TableName("sales_agency_payment_history")
public class SalesAgencyPaymentHistory extends Model<SalesAgencyPaymentHistory> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 经销商id
     */
    @TableField("agency_id")
    private Long agencyId;
    /**
     * 订单号
     */
    @TableField("order_no")
    private String orderNo;
    /**
     * 支付流水号
     */
    @TableField("payment_no")
    private String paymentNo;
    /**
     * 金额
     */
    private BigDecimal amount;
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
