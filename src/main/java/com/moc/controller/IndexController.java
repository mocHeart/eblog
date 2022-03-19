package com.moc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController {

    @RequestMapping({"", "/", "index"})
    public String index() {
        System.out.println("http:\\localhost:8080\\eblog");

        // 1.分页信息  2.分类  3.用户  4.置顶  5.精选  6.排序
        IPage results = postService.paging(getPage(), null, null, null, null, "created");

        request.setAttribute("pageData", results);
        request.setAttribute("currentCategoryID", 0);
        return "index";
    }

}
