package com.moc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class IndexController {

    @Autowired
    HttpServletRequest request;

    @RequestMapping("/")
    public String index() {
        System.out.println(request.getRequestURL());
        return request.getRequestURL() + "：欢迎您！";
    }

}
