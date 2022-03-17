<#-- 引入 layout.ftl 模板 -->
<#include "/inc/layout.ftl" />

<#-- @layout 标签内的数据会替换到 <#nested> 标签的位置 -->
<#-- "首页" 是 宏layout的一个参数 -->
<@layout "首页">

    <#-- 头部菜单栏 -->
    <#include "/inc/header-panel.ftl" />


    <#-- 中间部分 -->
    <div class="layui-container">
        <div class="layui-row layui-col-space15">
            <#-- 左侧 -->
            <div class="layui-col-md8">
                <div class="fly-panel">
                    <div class="fly-panel-title fly-filter">
                        <a>置顶</a>
                        <a href="#signin" class="layui-hide-sm layui-show-xs-block fly-right" id="LAY_goSignin" style="color: #FF5722;">去签到</a>
                    </div>
                    <ul class="fly-list">
                        <@posts size=3 level=1>
                            <#list results.records as post>
                                <@plisting post></@plisting>
                            </#list>
                        </@posts>
                    </ul>
                </div>

                <div class="fly-panel" style="margin-bottom: 0;">

                    <div class="fly-panel-title fly-filter">
                        <a href="" class="layui-this">综合</a>
                        <span class="fly-mid"></span>
                        <a href="">未结</a>
                        <span class="fly-mid"></span>
                        <a href="">已结</a>
                        <span class="fly-mid"></span>
                        <a href="">精华</a>
                        <span class="fly-filter-right layui-hide-xs">
                <a href="" class="layui-this">按最新</a>
                <span class="fly-mid"></span>
                <a href="">按热议</a>
              </span>
                    </div>

                    <ul class="fly-list">
                        <#list pageData.records as post>
                            <@plisting post></@plisting>
                        </#list>
                    </ul>

                    <#-- 页码条 -->
                    <@paging pageData></@paging>

                </div>
            </div>
            <#-- 右侧 -->
            <#include "/inc/right.ftl" />
        </div>
    </div>
</@layout>