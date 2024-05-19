package com.agileboot.domain.goods.post.model;

import cn.hutool.core.bean.BeanUtil;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.domain.goods.post.command.AddGoodsCommand;
import com.agileboot.domain.goods.post.command.UpdateGoodsCommand;
import com.agileboot.domain.goods.post.db.GoodsinfoEntity;
import com.agileboot.domain.goods.post.db.GoodsinfoService;
import com.agileboot.domain.system.post.command.AddPostCommand;
import com.agileboot.domain.system.post.command.UpdatePostCommand;
import com.agileboot.domain.system.post.db.SysPostEntity;
import com.agileboot.domain.system.post.db.SysPostService;
import lombok.NoArgsConstructor;

/**
 * @author valarchie
 */
@NoArgsConstructor
public class GoodsPostModel extends GoodsinfoEntity {

    private GoodsinfoService postService;

    public GoodsPostModel(GoodsinfoService postService) {
        this.postService = postService;
    }

    public GoodsPostModel(GoodsinfoEntity entity, GoodsinfoService postService) {
        if (entity != null) {
            BeanUtil.copyProperties(entity, this);
        }
        this.postService = postService;
    }


    public void loadFromAddCommand(AddGoodsCommand addCommand) {
        if (addCommand != null) {
            BeanUtil.copyProperties(addCommand, this, "id");
        }
    }



    public void loadFromUpdateCommand(UpdateGoodsCommand command) {
        if (command != null) {
            loadFromAddCommand(command);
        }
    }





}
