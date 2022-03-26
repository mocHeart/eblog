package com.moc.service;

import com.moc.common.lang.Result;
import com.moc.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moc.shiro.AccountProfile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 公众号：java思维导图
 * @since 2021-12-06
 */
public interface UserService extends IService<User> {

    Result register(User user);

    AccountProfile login(String email, String password);
}
