package com.moc.controller;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.moc.entity.Post;
import com.moc.vo.CommentVo;
import com.moc.vo.PostVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PostController extends BaseController {

    /**
     * id 只能是数字
     * :\\id*  表示正则匹配数字
     */
    @GetMapping("/category/{id:\\d*}")
    public String category(@PathVariable(name = "id") Long id) {
        int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
        request.setAttribute("currentCategoryId", id);
        request.setAttribute("pn", pn);
        return "post/category";
    }


    @GetMapping("/post/{id:\\d*}")
    public String detail(@PathVariable(name = "id") Long id) {

        PostVo vo = postService.selectOnePost(new QueryWrapper<Post>().eq("p.id", id));
        Assert.notNull(vo, "文章已被删除！");

        // 1.分页信息  2.分类  3.用户  4.置顶  5.精选  6.排序
        IPage<CommentVo> results = commentService.paging(getPage(), vo.getId(), null, "created");

        request.setAttribute("currentCategoryId", vo.getCategoryId());
        request.setAttribute("post", vo);
        request.setAttribute("pageData", results);

        return "post/detail";
    }


}
