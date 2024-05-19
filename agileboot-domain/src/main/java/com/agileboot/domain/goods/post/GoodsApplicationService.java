package com.agileboot.domain.goods.post;

import com.agileboot.common.core.page.PageDTO;
import com.agileboot.domain.common.command.BulkOperationCommand;
import com.agileboot.domain.goods.post.command.AddGoodsCommand;
import com.agileboot.domain.goods.post.command.UpdateGoodsCommand;
import com.agileboot.domain.goods.post.db.GoodsinfoEntity;
import com.agileboot.domain.goods.post.db.GoodsinfoService;
import com.agileboot.domain.goods.post.dto.GoodsDTO;
import com.agileboot.domain.goods.post.model.GoodsModelFactory;
import com.agileboot.domain.goods.post.model.GoodsPostModel;
import com.agileboot.domain.goods.post.query.GoodsPostQuery;
import com.agileboot.domain.system.post.command.AddPostCommand;
import com.agileboot.domain.system.post.command.UpdatePostCommand;
import com.agileboot.domain.system.post.db.SysPostEntity;
import com.agileboot.domain.system.post.db.SysPostService;
import com.agileboot.domain.system.post.dto.PostDTO;
import com.agileboot.domain.system.post.model.PostModel;
import com.agileboot.domain.system.post.model.PostModelFactory;
import com.agileboot.domain.system.post.query.PostQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author valarchie
 */
@Service
@RequiredArgsConstructor
public class GoodsApplicationService {


    private final GoodsModelFactory postModelFactory;
    private final GoodsinfoService postService;

    public PageDTO<GoodsDTO> getPostList(GoodsPostQuery query) {
        Page<GoodsinfoEntity> page = postService.page(query.toPage(), query.toQueryWrapper());
        List<GoodsDTO> records = page.getRecords().stream().map(GoodsDTO::new).collect(Collectors.toList());
        return new PageDTO<>(records, page.getTotal());
    }


    public void addPost(AddGoodsCommand addCommand) {
        GoodsPostModel postModel = postModelFactory.create();
        postModel.loadFromAddCommand(addCommand);
       /* postModel.checkPostNameUnique();
        postModel.checkPostCodeUnique();*/
        postModel.insert();
    }


    public void updatePost(UpdateGoodsCommand updateCommand) {
        GoodsPostModel postModel = postModelFactory.loadById(updateCommand.getId());
        postModel.loadFromUpdateCommand(updateCommand);

        /*postModel.checkPostNameUnique();
        postModel.checkPostCodeUnique();*/

        postModel.updateById();
    }


    public void deletePost(BulkOperationCommand<Long> deleteCommand) {
      /*  for (Long id : deleteCommand.getIds()) {
            GoodsPostModel postModel = postModelFactory.loadById(id);
            postModel.checkCanBeDelete();
        }*/

        postService.removeBatchByIds(deleteCommand.getIds());
    }


}
