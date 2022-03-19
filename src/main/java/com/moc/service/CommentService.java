package com.moc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moc.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moc.vo.CommentVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 公众号：java思维导图
 * @since 2021-12-06
 */
public interface CommentService extends IService<Comment> {

    IPage<CommentVo> paging(Page page, Long postId, Long userId, String oreder);
}
