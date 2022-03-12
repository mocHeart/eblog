package com.moc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController {

    @RequestMapping({"", "/", "index"})
    public String index() {
        System.out.println("http:\\localhost:8080\\eblog");

        int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
        int size = ServletRequestUtils.getIntParameter(request, "size", 2);
        Page page = new Page(pn, size);

        // 1.分页信息  2.分类  3.用户  4.置顶  5.精选  6.排序
        IPage results = postService.paging(page, null, null, null, null, "created");

        request.setAttribute("pageData", results);
        request.setAttribute("currentCategoryID", 0);
        return "index";
    }

}
