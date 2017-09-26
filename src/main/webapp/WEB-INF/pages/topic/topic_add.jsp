<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../inc/header.jsp"></jsp:include>
<div class='container main'>
    <jsp:include page="../inc/sidebar.jsp"></jsp:include>
    <div class='col-md-9'>
        <ul class='breadcrumb'>
            <li><a href='/'>主页</a><span class='divider'></span></li>
            <li><a href='${contextPath}/topic/list'>帖子管理</a><span class='divider'></span></li>
            <li class='active'>新建帖子</li>
        </ul>
        <div class='panel'>
            <div class='inner'>
                <div class="topic_content">
                    <div class="markdown-text">
                        <h3>新建帖子</h3>
                        <div class="row">
                            <jsp:include page="../inc/msgbox.jsp"></jsp:include>
                            <form method="post" action="${contextPath}/topic/create">
                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-addon">栏目:</div>
                                        <select id="catId" name="catId" class="form-control">
                                            <option value="" selected=""></option>
                                            <c:forEach items="${catList}" var="cat">
                                                <option value="${cat.id}">${cat.catName}</option>
                                            </c:forEach>
                                         </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-addon">标题:</div>
                                        <input type="text" name="title" class="form-control" placeholder="输入帖子标题" value="${topicVo.title}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-addon">摘要:</div>
                                        <textarea name="desc" class="form-control" rows="3" placeholder="输入摘要">${topicVo.desc}</textarea>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-addon">标签:</div>
                                        <input type="text" name="tags" class="form-control" placeholder="输入标签" value="${topicVo.tags}">
                                    </div>
                                    <span class="label-info">注意：标签使用英文逗号分隔</span>
                                </div>

                                <div class="form-group">
                                    <%--<div class="input-group">--%>
                                        <%--<div class="input-group-addon">内容:</div>--%>
                                        <%--<textarea name="content" class="form-control" rows="12" placeholder="请输入帖子内容">${topicVo.content}</textarea>--%>
                                    <%--</div>--%>
                                    <div id="editormd">
                                        <textarea class="editormd-markdown-textarea" name="contentMD" id="contentMD">${topicVo.contentMD}</textarea>
                                        <textarea class="editormd-html-textarea" name="contentHTML" id="contentHTML">${topicVo.contentHTML}</textarea>
                                    </div>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="contentIsHTML" value="true"> 是否网页？
                                    </label>
                                    <label>
                                        <input type="checkbox" name="top" value="true"> 置顶帖？
                                    </label>
                                    <label>
                                        <input type="checkbox" name="good" value="true"> 精华帖？
                                    </label>
                                </div>

                                <div class="form-group">
                                    <div class="text-center">
                                        <input type="hidden" name="authorId" value="${loginUser.id}">
                                        <input type="hidden" name="authorName" value="${loginUser.username}">
                                        <button class="btn btn-success" id="submit" type="submit">新建</button>
                                        <button class="btn btn-default" type="reset">清空</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../inc/footer.jsp"></jsp:include>
<script>
    var editor;
    $(function() {
        editor = createEditorMd("editormd", "#submit");
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
            height           : 500,
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
            path: "${contextPath}/js/editormd/lib/",
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
                //$('#contentHTML').html(this.getPreviewedHTML());
                $(submitId).attr('disabled', this.getMarkdown().trim() == "");
                //console.log(this.getHTML());
                //console.log("====================");
                //console.log(this.getPreviewedHTML());
            }
        });

        return editor;
    }
</script>