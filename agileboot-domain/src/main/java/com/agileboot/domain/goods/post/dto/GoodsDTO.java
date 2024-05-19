package com.agileboot.domain.goods.post.dto;

import cn.hutool.core.bean.BeanUtil;
import com.agileboot.common.annotation.ExcelColumn;
import com.agileboot.common.enums.BasicEnumUtil;
import com.agileboot.common.enums.common.StatusEnum;
import com.agileboot.domain.goods.post.db.GoodsinfoEntity;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author valarchie
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GoodsDTO {

    public GoodsDTO(GoodsinfoEntity entity) {
        if (entity != null) {
            BeanUtil.copyProperties(entity, this);

        }
    }

    @ExcelColumn(name = "菜品id")
    private Long id;


    @ExcelColumn(name = "菜品编码")
    private String number;

    @ExcelColumn(name = "菜品名称")
    private String name;


    @ExcelColumn(name = "菜品单价")
    private String price;

    @ExcelColumn(name = "商户编码")
    private String shopnumber;

    @ExcelColumn(name = "商户名称")
    private String shopname;



    @ExcelColumn(name = "备注")
    private String remarknotice;



    @ExcelColumn(name = "状态")
    private String status;

    @ExcelColumn(name = "图片")
    private String goodspicurl;

}
