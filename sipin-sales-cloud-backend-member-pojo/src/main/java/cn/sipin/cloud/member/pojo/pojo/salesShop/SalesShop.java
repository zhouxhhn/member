package cn.sipin.cloud.member.pojo.pojo.salesShop;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 门店表
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@JsonSerialize(include= Inclusion.ALWAYS)
@Data
@TableName("sales_shop")
public class SalesShop extends Model<SalesShop> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 门店code
     */
    private String code;

    /**
     * 门店sourceId
     */
    @ApiModelProperty(value = "门店sourceId")
    @TableField("source_id")
    private String sourceId;

    /**
     * 门店名称
     */
    @ApiModelProperty(value = "门店名称",required = true)
    private String name;
    /**
     * 门店地址
     */
    private String address;

    /**
     * 门店电话
     */
    private String phone;

    /**
     * 经销商帐号
     */
    @TableField("agency_code")
    private String agencyCode;
    /**
     * 创建时间
     */
    @TableField("created_at")
    private LocalDateTime createdAt;


    @TableField(exist = false)
    private Long agencyId;

    @TableField(exist = false)
    private String agencyName;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
