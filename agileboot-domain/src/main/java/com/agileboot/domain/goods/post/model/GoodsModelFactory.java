package com.agileboot.domain.goods.post.model;

import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode.Business;
import com.agileboot.domain.goods.post.db.GoodsinfoEntity;
import com.agileboot.domain.goods.post.db.GoodsinfoService;
import com.agileboot.domain.system.post.db.SysPostEntity;
import com.agileboot.domain.system.post.db.SysPostService;
import com.agileboot.domain.system.post.model.PostModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author valarchie
 */
@Component
@RequiredArgsConstructor
public class GoodsModelFactory {

    private final GoodsinfoService postService;

    public GoodsPostModel loadById(Long postId) {
        GoodsinfoEntity byId = postService.getById(postId);
        if (byId == null) {
            throw new ApiException(Business.COMMON_OBJECT_NOT_FOUND, postId, "id");
        }
        return new GoodsPostModel(byId, postService);
    }

    public GoodsPostModel create() {
        return new GoodsPostModel(postService);
    }

}
