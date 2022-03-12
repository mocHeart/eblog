package com.moc.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moc.entity.Category;
import com.moc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * 初始化
 * ApplicationRunner ： 是一个接口，里面定义一个run方法需要自己去实现，在SpringBoot启动之后会执行
 *
 */
@Component
public class ContextStartUp implements ApplicationRunner, ServletContextAware {

    @Autowired
    CategoryService categoryService;

    /**
     * 凡是实现ServletContextAware接口的类，都可以取得ServletContext
     */
    ServletContext servletContext;

    /**
     * 初始化
     * @param args
     */
    @Override
    public void run(ApplicationArguments args) {
        List<Category> categories = categoryService.list(new QueryWrapper<Category>().eq("status", 0));
        servletContext.setAttribute("categories", categories);
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
