package com.agileboot.domain.goods.post.db;

import com.agileboot.common.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author snifph
 * @since 2024-05-12
 */
@Getter
@Setter
@TableName("goodsinfo")
@ApiModel(value = "GoodsinfoEntity对象", description = "菜品信息表")
public class GoodsinfoEntity extends BaseEntity<GoodsinfoEntity> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("菜品编码")
    @TableField("number")
    private String number;

    @ApiModelProperty("菜品名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("菜品单价")
    @TableField("price")
    private String price;

    @ApiModelProperty("菜品状态")
    @TableField("status")
    private String status;

    @ApiModelProperty("商户编码")
    @TableField("shopnumber")
    private String shopnumber;

    @ApiModelProperty("商户名称")
    @TableField("shopname")
    private String shopname;

    @ApiModelProperty("备注")
    @TableField("remarknotice")
    private String remarknotice;

    @ApiModelProperty("菜品图片")
    @TableField("goodspicurl")
    private String goodspicurl;




    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
