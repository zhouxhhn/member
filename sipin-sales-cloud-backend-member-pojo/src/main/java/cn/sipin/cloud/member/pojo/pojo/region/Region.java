package cn.sipin.cloud.member.pojo.pojo.region;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 行政区
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@JsonSerialize(include= Inclusion.ALWAYS)
@ApiModel(value = "地区操作(Region)")
@Data
public class Region extends Model<Region> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ApiModelProperty(value = "主健id")
    private Long id;
    /**
     * 地区名
     */
    @ApiModelProperty(value = "地区名")
    private String name;
    /**
     * 父ID
     */
    @ApiModelProperty(value = "父ID")
    @TableField("parent_id")
    private Long parentId;

    /**
     * 排序
     */
    @ApiModelProperty(value = "是否可派送")
    private Integer acceptable;

    /**
     * 子节点
     */
    @TableField(exist = false)
    private List<Region> children = new ArrayList<>();


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
