package cn.sipin.cloud.member.pojo.pojo.salesAgency;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 经销商余额表
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@JsonSerialize(include= Inclusion.ALWAYS)
@ApiModel(value = "经销商余额操作(SalesAgencyBalance)")
@Data
@TableName("sales_agency_balance")
public class SalesAgencyBalance extends Model<SalesAgencyBalance> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "经销商余额主健id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 金额
     */
    @NotNull(message = "金额不能为空")
    @ApiModelProperty(value = "金额",required = true)
    private BigDecimal balance;
    /**
     * 交易平台
     */
    @ApiModelProperty(value = "交易平台")
    @TableField("trade_platform")
    private String tradePlatform;
    /**
     * 流水号
     */
    @ApiModelProperty(value = "流水号")
    @TableField("serial_num")
    private String serialNum;
    /**
     * 业务类型
     */
    @NotNull(message = "类型不能为空")
    @TableField("business_type")
    @Size(min = 1, max = 64, message = "类型过长或者过短")
    @ApiModelProperty(value = "类型新增为:increase,减少为:decrease",required = true)
    private String businessType;
    /**
     * 经销商帐号
     */
    @Size(min = 1, max = 20, message = "经销商帐号过长或者过短")
    @ApiModelProperty(value = "经销商帐号",required = true)
    private String code;

    /**
     * 客户订单号
     */
    @ApiModelProperty(value = "客户订单号")
    @TableField("order_num")
    private String orderNum;

    /**
     * 操作人
     */
    @ApiModelProperty(value = "操作人")
    private String operator;

    /**
     * 期末余额
     */
    @TableField("total_balance")
    @ApiModelProperty(value = "期末余额")
    private BigDecimal totalBalance;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("created_at")
    private Date createdAt;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
