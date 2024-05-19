package com.agileboot.admin.controller.goods;

import com.agileboot.admin.customize.aop.accessLog.AccessLog;
import com.agileboot.common.core.base.BaseController;
import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.common.core.page.PageDTO;
import com.agileboot.common.enums.common.BusinessTypeEnum;
import com.agileboot.common.utils.poi.CustomExcelUtil;
import com.agileboot.domain.common.command.BulkOperationCommand;
import com.agileboot.domain.goods.post.GoodsApplicationService;
import com.agileboot.domain.goods.post.command.AddGoodsCommand;
import com.agileboot.domain.goods.post.command.UpdateGoodsCommand;
import com.agileboot.domain.goods.post.dto.GoodsDTO;
import com.agileboot.domain.goods.post.query.GoodsPostQuery;
import com.agileboot.domain.system.post.PostApplicationService;
import com.agileboot.domain.system.post.command.AddPostCommand;
import com.agileboot.domain.system.post.command.UpdatePostCommand;
import com.agileboot.domain.system.post.dto.PostDTO;
import com.agileboot.domain.system.post.model.PostModel;
import com.agileboot.domain.system.post.query.PostQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 岗位信息操作处理
 *
 * @author ruoyi
 */
@Tag(name = "职位API", description = "职位相关的增删查改")
@RestController
@RequestMapping("/goods/post")
@Validated
@RequiredArgsConstructor
public class GoodsPostController extends BaseController {

    private final GoodsApplicationService postApplicationService;

    /**
     * 获取菜品数据
     */
    //@Operation(summary = "职位列表")
    //@PreAuthorize("@permission.has('system:post:list')")
    @GetMapping("/list")
    public ResponseDTO<PageDTO<GoodsDTO>> list(GoodsPostQuery query) {
        //query.setNumber("111");
        PageDTO<GoodsDTO> pageDTO = postApplicationService.getPostList(query);
        return ResponseDTO.ok(pageDTO);
    }

    /**
     * 新增菜品
     */
    //@Operation(summary = "添加职位")
    //@PreAuthorize("@permission.has('goods:post:add')")
    //@AccessLog(title = "岗位管理", businessType = BusinessTypeEnum.ADD)
    @PostMapping
    public ResponseDTO<Void> add(@RequestBody AddGoodsCommand addCommand) {
        postApplicationService.addPost(addCommand);
        return ResponseDTO.ok();
    }


    /**
     * 修改菜品
     */
    //@Operation(summary = "修改职位")
    //@PreAuthorize("@permission.has('system:post:edit')")
    //@AccessLog(title = "岗位管理", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping
    public ResponseDTO<Void> edit(@RequestBody UpdateGoodsCommand updateCommand) {
        postApplicationService.updatePost(updateCommand);
        return ResponseDTO.ok();
    }

    /**
     * 删除菜品
     */
    //@Operation(summary = "删除职位")
    //@PreAuthorize("@permission.has('system:post:remove')")
    //@AccessLog(title = "岗位管理", businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping
    public ResponseDTO<Void> remove(@RequestParam @NotNull @NotEmpty List<Long> ids) {
        postApplicationService.deletePost(new BulkOperationCommand<>(ids));
        return ResponseDTO.ok();
    }


}
