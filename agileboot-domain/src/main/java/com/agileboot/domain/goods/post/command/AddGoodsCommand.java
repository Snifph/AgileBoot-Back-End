package com.agileboot.domain.goods.post.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class AddGoodsCommand {

    @NotBlank(message = "菜品编码不能为空")
    @Size(max = 64, message = "菜品编码长度不能超过64个字符")
    protected String number;

    /**
     * 岗位名称
     */
    @NotBlank(message = "菜品名称不能为空")
    @Size(max = 64, message = "菜品名称长度不能超过64个字符")
    protected String name;



    protected String remarknotice;

    @PositiveOrZero
    protected String status;

}
