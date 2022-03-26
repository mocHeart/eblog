package com.moc.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.moc.common.lang.Result;
import com.moc.entity.Post;
import com.moc.entity.User;
import com.moc.entity.UserMessage;
import com.moc.shiro.AccountProfile;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class UserController extends BaseController {

    @GetMapping("/user/home")
    public String home() {
        User user = userService.getById(getProfileId());
        List<Post> posts = postService.list(new QueryWrapper<Post>()
                .eq("user_id", getProfileId())
                // 30天内
                //.gt("created", DateUtil.offsetDay(new Date(), -30))
                .orderByDesc("created")
        );

        request.setAttribute("user", user);
        request.setAttribute("posts", posts);
        return "/user/home";
    }

    @GetMapping("/user/set")
    public String set() {
        User user = userService.getById(getProfileId());
        request.setAttribute("user", user);
        return "/user/set";
    }

    @ResponseBody
    @PostMapping("/user/set")
    public Result doSet(User user) {

        if(StrUtil.isNotBlank(user.getAvatar())) {

            User temp = userService.getById(getProfileId());
            temp.setAvatar(user.getAvatar());
            userService.updateById(temp);

            AccountProfile profile = getProfile();
            profile.setAvatar(user.getAvatar());

            SecurityUtils.getSubject().getSession().setAttribute("profile", profile);

            return Result.success().action("/user/set#avatar");
        }

        if(StrUtil.isBlank(user.getUsername())) {
            return Result.fail("昵称不能为空");
        }
        int count = userService.count(new QueryWrapper<User>()
                .eq("username", getProfile().getUsername())
                .ne("id", getProfileId()));
        if(count > 0) {
            return Result.fail("该昵称已被占用！");
        }

        User temp = userService.getById(getProfileId());
        temp.setUsername(user.getUsername());
        temp.setGender(user.getGender());
        temp.setSign(user.getSign());
        userService.updateById(temp);

        AccountProfile profile = getProfile();
        profile.setUsername(temp.getUsername());
        profile.setSign(temp.getSign());
        SecurityUtils.getSubject().getSession().setAttribute("profile", profile);

        return Result.success().action("/eblog/user/set#info");
    }

}
