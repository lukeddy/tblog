<%@ page language="java" pageEncoding="UTF-8" %>
<jsp:include page="../inc/header.jsp"></jsp:include>
  <div id="main">
      <div id="container">
          <div class="row">
              <div class="col-md-3 left sidebar">
                  <jsp:include page="user_sidebar.jsp">
                      <jsp:param name="activeTab" value="userinfo"/>
                  </jsp:include>
              </div>
              <div class="col-md-9">
                  <ul class="breadcrumb">
                      <li><a href="/"><i class="glyphicon glyphicon-home"></i> 首页</a></li>
                      <li class="active">个人资料</li>
                  </ul>
                  <div class="wrapper">
                      <form action="/user_center/edit_info" method="post" class="form-horizontal" role="form">
                          <div class="form-group">
                              <label class="col-lg-2 control-label">用户名</label>
                              <div class="col-lg-6">
                                  <p class="form-control-static">${loginUser.username}</p>
                              </div>
                          </div>

                          <div class="form-group">
                              <label for="email" class="col-lg-2 control-label">电子邮件</label>
                              <div class="col-lg-10">
                                  <input type="text" value="${loginUser.email}" name="email" id="email" class="form-control">
                              </div>
                          </div>

                          <div class="form-group">
                              <label for="website" class="col-lg-2 control-label">个人网站</label>
                              <div class="col-lg-10">
                                  <input type="text" value="www.shikezhi.com" name="website" id="website" class="form-control">
                              </div>
                          </div>

                          <div class="form-group">
                              <label for="location" class="col-lg-2 control-label">所在地</label>
                              <div class="col-lg-10">
                                  <input type="text" value="成都" name="location" id="location" class="form-control" placeholder="城市名即可，如苏州">
                              </div>
                          </div>

                          <div class="form-group">
                              <label for="tagline" class="col-lg-2 control-label">签名</label>
                              <div class="col-lg-10">
                                  <input type="text" value="心云" name="tagline" id="tagline" class="form-control">
                              </div>
                          </div>

                          <div class="form-group">
                              <label for="bio" class="col-lg-2 control-label">个人简介</label>
                              <div class="col-lg-10">
                                  <textarea id="bio" name="bio" class="form-control">游离于IT世界多年，Java&amp;Android&amp;python&amp;php&amp;golang&amp;nodejs玩转</textarea>
                              </div>
                          </div>
                          <hr>

                          <div class="form-group">
                              <label for="github_username" class="col-lg-2 control-label">GitHub用户名</label>
                              <div class="col-lg-10">
                                  <input type="text" value="tzq668766" name="github_username" id="github_username" class="form-control">
                              </div>
                          </div>

                          <div class="form-group">
                              <label for="weibo" class="col-lg-2 control-label">新浪微博</label>
                              <div class="col-lg-10">
                                  <div class="input-group">
                                      <span class="input-group-addon">http://weibo.com/</span>
                                      <input type="text" value="francistangzq" name="weibo" id="weibo" class="form-control">
                                  </div>
                              </div>
                          </div>
                          <div class="form-group">
                              <div class="col-lg-offset-2 col-lg-10">
                                  <input type="submit" class="btn btn-primary" value="保存设置">
                              </div>
                          </div>
                      </form>
                  </div>

              </div>

          </div>
      </div>
  </div>
<jsp:include page="../inc/footer.jsp"></jsp:include>