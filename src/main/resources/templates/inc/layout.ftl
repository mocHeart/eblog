<#-- 定义宏layout（理解为模板） title是一个变量   -->
<#macro layout title>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="fly,layui,前端社区">
    <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
    <link rel="stylesheet" href="/eblog/res/layui/css/layui.css">
    <link rel="stylesheet" href="/eblog/res/css/global.css">

    <script src="/eblog/res/layui/layui.all.js"></script>
    <script src="/eblog/res/js/jquery.min.js"></script>
    <script src="/eblog/res/js/sockjs.js"></script>
    <script src="/eblog/res/js/stomp.js"></script>
    <script src="/eblog/res/js/im.js"></script>
    <script src="/eblog/res/js/chat.js"></script>
</head>
<body>

<#-- 头部 -->
<#include "/inc/header.ftl" />
<#-- 页码条 -->
<#include "/inc/common.ftl" />

<#-- 中间部分不通用 - 需要单独引入 -->
<#nested >

<#-- 尾部 -->
<#include "/inc/footer.ftl" />

<script src="/eblog/res/layui/layui.js"></script>
<script>
    layui.cache.page = '';
    layui.cache.user = {
        username: '游客'
        ,uid: -1
        ,avatar: '/eblog/res/images/avatar/00.jpg'
        ,experience: 83
        ,sex: '男'
    };
    layui.config({
        version: "3.0.0"
        ,base: '/eblog/res/mods/' //这里实际使用时，建议改成绝对路径
    }).extend({
        fly: 'index'
    }).use('fly');
</script>

<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_30088308'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "w.cnzz.com/c.php%3Fid%3D30088308' type='text/javascript'%3E%3C/script%3E"));</script>

</body>
</html>


</#macro>