<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<div id='footer'>
    <div id='footer_main'>
        <div class="links">
            <a class='dark' href='${contextPath}/rss'>RSS</a>
            |
            <a class='dark' href='https://github.com/tzq668766/tblog/tree/tblog-spirngmvc-mongo/'>源码地址</a>
            | 本次构建于：
            <a href="https://github.com/tzq668766/tblog/commit/${fns:getConfig("git.commit.id")}" target="_blank">${fns:getConfig("git.closest.tag.name")}-${fns:getConfig("git.commit.id.abbrev")}-${fns:getConfig("git.build.time")}</a>
        </div>

        <div class='col_fade'>
            <p>TBlog使用Java技术，结合流行框架开发个人博客系统。</p>
            <p>如果感觉系统比错，请不要吝啬您的星星，只需要轻轻的戳一下，谢谢！ <a href='https://github.com/${fns:getConfig("git.build.user.name")}'>个人博客(https://www.xxoo.com/)</a></p>
        </div>
    </div>
</div>
<!-- scripts -->
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="${contextPath}/static/js/jquery.scrollUp.min.js"></script>
<script src="${contextPath}/static/js/editormd/editormd.min.js"></script>
<!--回到顶部开始-->
<script>
    $(document).ready(function () {
        $.scrollUp({
            scrollName: 'scrollUp',
            topDistance: '100',
            topSpeed: 300,
            animation: 'fade',
            animationInSpeed: 200,
            animationOutSpeed: 200,
            scrollText: '',
            activeOverlay: false
        });
    });
</script>
<a id="scrollUp" href="#top" title="" style="position: fixed; z-index: 222222222222; display: none;"></a></body>
<!--回到顶部结束-->
</body>
</html>