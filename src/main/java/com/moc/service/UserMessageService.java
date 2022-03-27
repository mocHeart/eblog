package com.moc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moc.entity.UserMessage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moc.vo.UserMessageVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 公众号：java思维导图
 * @since 2021-12-06
 */
public interface UserMessageService extends IService<UserMessage> {

    IPage<UserMessageVo> paging(Page page, QueryWrapper<UserMessage> orderByDesc);

    void updateToRead(List<Long> ids);

}
