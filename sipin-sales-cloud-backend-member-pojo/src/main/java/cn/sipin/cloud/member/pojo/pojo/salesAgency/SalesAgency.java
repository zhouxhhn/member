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
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * 经销商表
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@JsonSerialize(include= Inclusion.ALWAYS)
@ApiModel(value = "经销商操作(SalesAgency)")
@Data
@TableName("sales_agency")
public class SalesAgency extends Model<SalesAgency> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 经销商帐号
     */
    private String code;
    /**
     * 经销商名称
     */
    private String name;
    /**
     * 经销商等级
     */
    private String grade;
    /**
     * 通讯地址
     */
    private String address;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 联系人
     */
    private String contacts;
    /**
     * 帐户余额
     */
    private BigDecimal balance;
    /**
     * 结算折扣
     */
    private BigDecimal discount = new BigDecimal("1");
    /**
     * 营业执照图片地址
     */
    @TableField("license_img_url")
    private String licenseImgUrl;
    /**
     * 营业执照图片密钥
     */
    @TableField("license_img_secret")
    private String licenseImgSecret;

    /**
     * k3外部编码
     */
    @TableField("outer_code")
    private String outerCode;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
