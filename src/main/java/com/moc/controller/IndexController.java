package com.moc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController {

    @RequestMapping({"", "/", "index"})
    public String index() {
        System.out.println("http:\\localhost:8080\\eblog");


        request.setAttribute("currentCategoryID", 0);
        return "index";
    }

}
