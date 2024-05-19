package com.agileboot.domain.goods.post.query;

import cn.hutool.core.util.StrUtil;
import com.agileboot.common.core.page.AbstractPageQuery;
import com.agileboot.domain.goods.post.db.GoodsinfoEntity;
import com.agileboot.domain.system.post.db.SysPostEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GoodsPostQuery extends AbstractPageQuery<GoodsinfoEntity> {

    private String number;



    @Override
    public QueryWrapper<GoodsinfoEntity> addQueryCondition() {
        QueryWrapper<GoodsinfoEntity> queryWrapper = new QueryWrapper<GoodsinfoEntity>();

        this.orderColumn = "create_time";


        return queryWrapper;
    }
}
