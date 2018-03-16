<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../inc/header.jsp">
    <jsp:param name="title" value="${article.topic.title}"/>
</jsp:include>
<div class='container main'>
    <div class='col-md-9' id="content">
        <ul class='breadcrumb'>
            <li><a href='${contextPath}/'><i class="glyphicon glyphicon-home"></i>主页</a><span class='divider'></span></li>
            <li><a href='${contextPath}/?tab=${article.topic.catDir}'>${article.topic.catName}</a><span class='divider'></span></li>
        </ul>
        <div class='panel'>
            <div class='header topic-header'>
                <h1 class="topic-full-title">${article.topic.title}</h1>
                <div class="changes">
                    <span>${article.topic.friendlyTime}</span><span>&nbsp;&nbsp;作者：<a href="${contextPath}/pub/user/${article.topic.authorId}">${article.topic.authorName}</a></span><span>&nbsp;&nbsp; ${article.topic.visitCount}次浏览</span>
                    <span class="share pull-right">
                        <div class="bdsharebuttonbox"><a href="#" class="bds_more" data-cmd="more"></a><a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a><a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a><a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a><a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a><a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a></div>
                        <script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
                    </span>
                </div>
            </div>
            <div class='inner topic'>
                <div class="topic_content">
                    <div class="editormd-preview-container">
                        ${article.topic.contentHTML}
                    </div>
                </div>
                <div class="topic-tags">
                    <span>标签：</span>
                    <c:if test="${not empty article.topic.tags}">
                        <c:forEach items="${article.topic.tags}" var="tag">
                            <a href="${contextPath}/tag/${tag}" class="tag">${tag}</a>
                        </c:forEach>
                    </c:if>
                </div>
                <div class="topic-action-wrapper">
                    <div class="topic-actions">
                        <c:choose>
                            <c:when test="${article.topic.likedUsers.contains(loginUser.id)}">
                                <a href="${contextPath}/like/remove/${article.topic.id}" class="action-link">
                                    <img src="${contextPath}/static/images/ico/liked-lg.svg" alt="">
                                    <span>取消喜欢</span>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="${contextPath}/like/add/${article.topic.id}" class="action-link">
                                    <img src="${contextPath}/static/images/ico/like-lg.svg" alt="">
                                    <span>喜欢</span>
                                </a>
                            </c:otherwise>
                        </c:choose>
                        <a href="#reply" class="action-link">
                            <img src="${contextPath}/static/images/ico/comment-lg.svg" alt="">
                            <span>评论</span>
                        </a>
                        <c:choose>
                            <c:when test="${article.topic.collectedUsers.contains(loginUser.id)}">
                                <a id="collectLink" href="${contextPath}/collect/remove/${article.topic.id}" class="action-link" title="取消收藏">
                                    <img src="${contextPath}/static/images/ico/collected.svg" alt="">
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a id="collectLink" href="${contextPath}/collect/add/${article.topic.id}" class="action-link" title="收藏">
                                    <img src="${contextPath}/static/images/ico/collect-lg.svg" alt="">
                                </a>
                            </c:otherwise>
                        </c:choose>
                        <a href="javascript:;" class="action-link">
                            <img src="${contextPath}/static/images/ico/share-lg.svg" alt="">
                            <span>分享</span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <c:if test="${not empty article.replyList}">
            <div class="panel" id="comment">
                <div class="header">
                    <span class="col_fade">${article.topic.replyCount} 回复</span>
                </div>
                <c:forEach items="${article.replyList}" var="reply" varStatus="status">
                    <div class="cell reply_area reply_item editormd-preview-container" reply_id="${reply.id}" reply_to_id="" id="${reply.id}">
                        <div class="author_content">
                            <a href="${contextPath}/pub/user/${reply.authorInfo.authorId}" class="user_avatar">
                                <img src="${contextPath}${reply.authorInfo.authorAvatar}" title="${reply.authorInfo.authorName}"></a>
                            <div class="user_info">
                                <a class="dark reply_author" href="${contextPath}/pub/user/${reply.authorInfo.authorId}">${reply.authorInfo.authorName}</a>
                                <a class="reply_time" href="#${reply.id}">${status.index+1}楼•${reply.friendlyTime}</a>
                            </div>
                            <div class="user_action">
                              <span>
                                <i class="fa up_btn fa-thumbs-o-up invisible" title="喜欢">
                                    <c:if test="${reply.thumbsUPCount>0}">${reply.thumbsUPCount}</c:if>
                                </i>
                                <span class="up-count"></span>
                              </span>
                                <span></span>
                                <c:if test="${not empty loginUser}">
                                    <c:if test="${loginUser.id==reply.authorInfo.authorId}">
                                        <a href="javascript:;" class="edit-reply" reply-id="${reply.id}" title="编辑"><i class="glyphicon glyphicon-edit"></i></a>
                                        <a href="${contextPath}/article/${article.topic.id}/reply/${reply.id}/del/" title="删除"><i class="glyphicon glyphicon-remove-circle"></i></a>
                                    </c:if>
                                </c:if>
                            </div>
                        </div>
                        <div class="reply_content from-${reply.authorInfo.authorId}">
                            <div class="markdown-text"><p>${reply.contentHTML}</p></div>
                        </div>
                        <div class="clearfix">
                            <div class="reply2_area"></div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:if>

        <c:if test="${not empty loginUser}">
            <jsp:include page="../inc/msgbox.jsp"></jsp:include>

            <div id="reply">
                <form action="${contextPath}/reply/add/${article.topic.id}" method="post" class="form-vertical" id="reply-form" role="form">
                    <fieldset>
                        <div class="form-group">
                            <label>新回复</label>
                            <div id="editormd">
                                <textarea class="editormd-markdown-textarea" name="contentMD" id="contentMD"></textarea>
                                <textarea class="editormd-html-textarea" name="contentHTML" id="contentHTML"></textarea>
                            </div>
                        </div>
                        <input type="hidden" name="topicId" value="${article.topic.id}">
                        <input type="hidden" name="authorId" value="${loginUser.id}">
                        <input type="hidden" name="authorName" value="${loginUser.username}">
                        <input type="hidden" name="authorAvatar" value="${loginUser.avatarURL}">
                        <input type="submit" class="btn btn-primary" value="回复" id="submit">
                    </fieldset>
                </form>
            </div>

            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                            <h4 class="modal-title" id="myModalLabel">编辑评论</h4>
                        </div>
                        <div class="modal-body">
                            <form action="" method="post" class="form-vertical" id="edit-comment-form" role="form">
                                <div class="form-group">
                                    <div id="editormd-edit">
                                        <textarea style="display: none;" id="comment-markdown"></textarea>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-info" id="edit-submit">提交</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${empty loginUser}">
            <div class="content" style="padding: 2em;">
                需要 <a href="${contextPath}/login" class="btn btn-primary">登录</a> 后方可回复, 如果你还没有账号你可以 <a href="${contextPath}/register" class="btn btn-danger">注册</a> 一个帐号。
            </div>
        </c:if>
    </div>
    <jsp:include page="../inc/sidebar.jsp"></jsp:include>
</div>
<jsp:include page="../inc/footer.jsp"></jsp:include>
<c:if test="${empty loginUser}">
    <script src="${contextPath}/static/js/editormd/lib/prettify.min.js"></script>
</c:if>
<script>
    var editor, editor_edit;

    $(document).ready(function(){

        $('.edit-reply').click(function(){
            var replyId = $(this).attr('reply-id');
            $.getJSON("${contextPath}/article/reply/" + replyId + ".json", function (data) {
                $('#edit-comment-form').attr('action', '/article/${article.topic.id}/reply/' + replyId + '/edit');
                $('#myModal').on('shown.bs.modal', function (e) {
                    if (editor_edit) {
                        editor_edit.setMarkdown(data.contentMD);
                    } else {
                        editor_edit = createEditorMd("editormd-edit", "#edit-submit", data.contentMD);
                    }
                });
                $('#myModal').modal({});
            });
        });

        $('#submit').attr('disabled', true);

        editor = createEditorMd("editormd", "#submit");

        $('.editormd-preview-container pre').addClass("prettyprint linenums");
        prettyPrint();


    });

    /**
     * 创建Markdown编辑器封装方法
     * @param divId
     * @param submitId
     * @param markdown
     * @returns {*}
     */
    function createEditorMd(divId, submitId, markdown) {
        var editor = editormd(divId, {
            height           : 300,
            markdown         : markdown,
            tex              : true,
            tocm             : true,
            emoji            : true,
            taskList         : true,
            codeFold         : true,
            searchReplace    : true,
            htmlDecode       : "style,script,iframe",
            flowChart        : true,
            sequenceDiagram  : true,
            autoFocus: false,
            path: "${contextPath}/static/js/editormd/lib/",
            placeholder: "Markdown，提交前请查看预览格式是否正确",
            saveHTMLToTextarea: true,
            imageUpload: true,
            imageFormats: ["jpg", "jpeg", "gif", "png"],
            imageUploadURL: "${contextPath}/upload/image",
            toolbarIcons: function() {
                return ["undo", "redo", "|", "bold", "italic", "quote", "|",
                    "h1", "h2", "h3", "h4", "h5", "h6", "|",
                    "list-ul", "list-ol", "hr", "|",
                    "link", "reference-link", "image", "code", "preformatted-text", "code-block", "|",
                    "goto-line", "watch", "preview", "fullscreen", "|",
                    "help", "info"]
            },
            onfullscreen : function() {
                this.editor.css("border-radius", 0).css("z-index", 120);
            },
            onfullscreenExit : function() {
                this.editor.css({
                    zIndex : 10,
                    border : "none",
                    "border-radius" : "5px"
                });

                this.resize();
            },
            onchange: function() {
                $(submitId).attr('disabled', this.getMarkdown().trim() == "");
            }
        });

        return editor;
    }
</script>
