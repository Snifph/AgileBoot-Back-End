package com.agileboot.domain.goods.post.command;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateGoodsCommand extends AddGoodsCommand {

    @NotNull(message = "ID不能为空")
    @Positive
    private Long id;

}
