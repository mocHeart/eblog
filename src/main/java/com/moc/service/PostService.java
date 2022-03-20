package com.moc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moc.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moc.vo.PostVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 公众号：java思维导图
 * @since 2021-12-06
 */
public interface PostService extends IService<Post> {

    IPage paging(Page page, Long categoryId, Long userId, Integer level, Boolean recommend, String created);

    PostVo selectOnePost(QueryWrapper<Post> wrapper);

    /**
     * 初始化：本周热议
     */
    void initWeekRank();

    /**
     * redis缓存文章评论数增减1,并重做本周热议缓存
     * @param postId
     * @param isIncr
     */
    void incrCommentCountAndUnionForWeekRank(long postId, boolean isIncr);

    /**
     * 阅读量加一
     * @param vo
     */
    void putViewCount(PostVo vo);
}
