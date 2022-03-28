package com.moc.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.moc.common.lang.Result;
import com.moc.entity.Post;
import com.moc.entity.UserCollection;
import com.moc.vo.CommentVo;
import com.moc.vo.PostVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

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

        // 阅读量加一
        postService.putViewCount(vo);

        request.setAttribute("currentCategoryId", vo.getCategoryId());
        request.setAttribute("post", vo);
        request.setAttribute("pageData", results);

        return "post/detail";
    }

    /**
     * 判断用户是否收藏了文章
     * @param pid 1
     * @return 2
     */
    @ResponseBody
    @PostMapping("/collection/find/")
    public Result collectionFind(Long pid) {
        int count = collectionService.count(new QueryWrapper<UserCollection>()
                .eq("user_id", getProfileId())
                .eq("post_id", pid)
        );
        return Result.success(MapUtil.of("collection", count > 0 ));
    }

    /**
     * 文章 - 添加收藏
     * @param pid 1
     * @return 2
     */
    @ResponseBody
    @PostMapping("/collection/add/")
    public Result collectionAdd(Long pid) {
        Post post = postService.getById(pid);

        Assert.isTrue(post != null, "改帖子已被删除");
        int count = collectionService.count(new QueryWrapper<UserCollection>()
                .eq("user_id", getProfileId())
                .eq("post_id", pid)
        );
        if(count > 0) {
            return Result.fail("你已经收藏");
        }

        UserCollection collection = new UserCollection();
        collection.setUserId(getProfileId());
        collection.setPostId(pid);
        collection.setCreated(new Date());
        collection.setModified(new Date());

        collection.setPostUserId(post.getUserId());

        collectionService.save(collection);
        return Result.success();
    }

    /**
     * 文章 - 取消收藏
     * @param pid 1
     * @return 2
     */
    @ResponseBody
    @PostMapping("/collection/remove/")
    public Result collectionRemove(Long pid) {
        Post post = postService.getById(pid);
        Assert.isTrue(post != null, "改帖子已被删除");

        collectionService.remove(new QueryWrapper<UserCollection>()
                .eq("user_id", getProfileId())
                .eq("post_id", pid));

        return Result.success();
    }

}
