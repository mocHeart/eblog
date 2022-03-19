package com.moc.controller;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moc.entity.Post;
import com.moc.service.PostService;
import com.moc.vo.PostVo;
import org.springframework.stereotype.Controller;
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
        request.setAttribute("currentCategoryID", id);
        return "post/category";
    }


    @GetMapping("/post/{id:\\d*}")
    public String detail(@PathVariable(name = "id") Long id) {

        PostVo vo = postService.selectOnePost(new QueryWrapper<Post>().eq("p.id", id));
        Assert.notNull(vo, "文章已被删除！");

        request.setAttribute("currentCategoryId", vo.getCategoryId());
        request.setAttribute("post", vo);

        return "post/detail";
    }


}
