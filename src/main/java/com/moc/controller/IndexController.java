package com.moc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"", "/", "index"})
    public String index() {
        System.out.println("http:\\localhost:8080\\eblog");
        return "index";
    }

}
