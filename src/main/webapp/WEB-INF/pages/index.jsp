<%@ page language="java" pageEncoding="UTF-8" %>
<jsp:include page="inc/header.jsp"></jsp:include>
<div id='main'>
    <jsp:include page="inc/sidebar.jsp"></jsp:include>
    <div id='content'>
        <div class="panel">
            <div class="header">
                <a href="/?tab=all" class="topic-tab current-tab">全部</a>
                <a href="/?tab=good" class="topic-tab ">精华</a>
                <a href="/?tab=share" class="topic-tab ">分享</a>
                <a href="/?tab=ask" class="topic-tab ">问答</a>
                <a href="/?tab=job" class="topic-tab ">招聘</a>
                <a href="/?tab=dev" class="topic-tab ">客户端测试</a>
            </div>
            <div class="inner no-padding">
                <div id="topic_list">
                    <div class="cell">
                        <a class="user_avatar pull-left" href="javascript:;">
                            <img src="//gravatar.com/avatar/d00d8e3461257418a62b1cb7abeea85a?size=48" title="xinyu198736">
                        </a>
                        <span class="reply_count pull-left">
                            <span class="count_of_replies" title="回复数">
                              6
                            </span>
                            <span class="count_seperator">/</span>
                            <span class="count_of_visits" title="点击数">
                              1141
                            </span>
                        </span>
                        <a class="last_time pull-right" href="javascript:;">
                            <img class="user_small_avatar" src="https://avatars1.githubusercontent.com/u/20686484?v=4&amp;s=120">
                            <span class="last_active_time">8 小时前</span>
                        </a>
                        <div class="topic_title_wrapper">
                            <span class="put_top">置顶</span>
                            <a class="topic_title" href="${contextPath}/article/123abcsfdsf" title="杭州 NodeParty 第四期总结（slide、现场照片）">
                                杭州 NodeParty 第四期总结（slide、现场照片）
                            </a>
                        </div>
                    </div>
                    <div class="cell">
                        <a class="user_avatar pull-left" href="javascript:;">
                            <img src="//gravatar.com/avatar/d00d8e3461257418a62b1cb7abeea85a?size=48" title="xinyu198736">
                        </a>
                        <span class="reply_count pull-left">
                            <span class="count_of_replies" title="回复数">
                              6
                            </span>
                            <span class="count_seperator">/</span>
                            <span class="count_of_visits" title="点击数">
                              1141
                            </span>
                        </span>
                        <a class="last_time pull-right" href="javascript:;">
                            <img class="user_small_avatar" src="https://avatars1.githubusercontent.com/u/20686484?v=4&amp;s=120">
                            <span class="last_active_time">8 小时前</span>
                        </a>
                        <div class="topic_title_wrapper">
                            <span class="put_top">置顶</span>
                            <a class="topic_title" href="javascript:;" title="杭州 NodeParty 第四期总结（slide、现场照片）">
                                杭州 NodeParty 第四期总结（slide、现场照片）
                            </a>
                        </div>
                    </div>
                    <div class="cell">
                        <a class="user_avatar pull-left" href="javascript:;">
                            <img src="//gravatar.com/avatar/d00d8e3461257418a62b1cb7abeea85a?size=48" title="xinyu198736">
                        </a>
                        <span class="reply_count pull-left">
                            <span class="count_of_replies" title="回复数">
                              6
                            </span>
                            <span class="count_seperator">/</span>
                            <span class="count_of_visits" title="点击数">
                              1141
                            </span>
                        </span>
                        <a class="last_time pull-right" href="javascript:;">
                            <img class="user_small_avatar" src="https://avatars1.githubusercontent.com/u/20686484?v=4&amp;s=120">
                            <span class="last_active_time">8 小时前</span>
                        </a>
                        <div class="topic_title_wrapper">
                            <span class="put_top">置顶</span>
                            <a class="topic_title" href="javascript:;" title="杭州 NodeParty 第四期总结（slide、现场照片）">
                                杭州 NodeParty 第四期总结（slide、现场照片）
                            </a>
                        </div>
                    </div>
                </div>
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <li>
                            <a href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <li><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li><a>...</a></li>
                        <li>
                            <a href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>
        </div>
    </div>
    </div>
</div>
<jsp:include page="inc/footer.jsp"></jsp:include>
