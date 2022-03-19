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
}
