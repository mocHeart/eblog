package com.moc.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.moc.common.lang.Result;
import com.moc.entity.User;
import com.moc.util.ValidationUtil;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class AuthController extends BaseController {

    private static final String KAPTCHA_SESSION_KEY = "KAPTCHA_SESSION_KEY";

    @Autowired
    Producer producer;

    @GetMapping("/kaptcha.jpg")
    public void kaptcha(HttpServletResponse response) throws IOException {

        // 验证码
        String text = producer.createText();
        BufferedImage image = producer.createImage(text);
        request.getSession().setAttribute(KAPTCHA_SESSION_KEY, text);

        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "jpg", outputStream);
    }

    @GetMapping("/register")
    public String register() {
        return "/auth/reg";
    }

    @ResponseBody
    @PostMapping("/register")
    public Result doRegister(User user, String repass, String vercode) {

        ValidationUtil.ValidResult validResult = ValidationUtil.validateBean(user);
        if(validResult.hasErrors()) {
            return Result.fail(validResult.getErrors());
        }

        if(!user.getPassword().equals(repass)) {
            return Result.fail("两次输入密码不相同");
        }

        String captcha = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        System.out.println("验证码：" + captcha);
        if(vercode == null || !vercode.equalsIgnoreCase(captcha)) {
            return Result.fail("验证码输入不正确");
        }

        return Result.success().action("/login");
    }

    @GetMapping("/login")
    public String login() {
        return "/auth/login";
    }

}
