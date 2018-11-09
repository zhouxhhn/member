package cn.sipin.cloud.member.pojo.pojo.printSetting;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 打印设置
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@JsonSerialize(include= Inclusion.ALWAYS)
@Data
@Accessors(chain = true)
@TableName("print_setting")
public class PrintSetting extends Model<PrintSetting> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 打印主机IP
     */
    @TableField("printer_ip")
    private String printerIp;
    /**
     * 打印机
     */
    private Integer printer;
    /**
     * 店铺地址
     */
    @TableField("shop_address")
    private String shopAddress;
    /**
     * 店铺电话
     */
    @TableField("shop_phone")
    private String shopPhone;
    /**
     * 票尾提示
     */
    @TableField("ticket_tips")
    private String ticketTips;
    /**
     * POS机IP
     */
    @TableField("pos_ip")
    private String posIp;
    /**
     * 门店code
     */
    @TableField("shop_code")
    private String shopCode;
    /**
     * 创建时间
     */
    @TableField("created_at")
    private Date createdAt;
    /**
     * 更新时间
     */
    @TableField("updated_at")
    private Date updatedAt;

    /**
     * 0表示AO打印机，1表示商米内置打印机
     */
    @TableField("printer_type")
    private Integer printerType = 1;

    /**
     * 0表示通联pos，1表示不使用pos机
     */
    @TableField("pos_type")
    private Integer posType = 1;



    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
