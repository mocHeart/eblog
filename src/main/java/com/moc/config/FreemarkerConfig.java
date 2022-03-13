package com.moc.config;

import com.moc.template.TimeAgoMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class FreemarkerConfig {

    @Autowired
    private freemarker.template.Configuration configuration;


    /**
     * PostConstruct注解: 被用来修饰一个非静态的void（）方法
     *   被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次
     *   PostConstruct在构造函数之后执行，init（）方法之前执行
     *   Constructor(构造方法) -> @Autowired(依赖注入) -> @PostConstruct(注释的方法)
     *
     *  *.ftl中的 timeAgo() --> new TimeAgoMethod().exec()
     */
    @PostConstruct
    public void setUp() {
        configuration.setSharedVariable("timeAgo", new TimeAgoMethod());
    }

}
