#  个人博客系统: tBlog

## 介绍

tBlog 是使用 ***Java框架*** 和 ***MongoDB*** 开发的个人博客系统，界面优雅，功能简单、小巧，您可以使用它搭建自己的博客。


## 技术选型(后续会有更多版本:[springmvc版本](https://github.com/tzq668766/tblog/tree/tblog-spirngmvc-mongo))

1、后端

* 核心框架：SpringBoot
* 持久层框架：Sprint Data Mongo
* 视图框架：JSP
* 工具类：Apache Commons、Jackson

2、前端

* JS框架：jQuery
* CSS框架：Bootstrap
* 文件上传：DropZone 
* 富文本：EditorMD


## 如何运行

```
1. 安装`Maven[必须]` `JDK[必须]` `MongoDB[必须]`,并配置Maven,JDK的环境变量
2. 启动 MongoDB
3. 在项目的application-xxx.properties文件中修改MongoDB数据库连接地址
4. 到项目根目录执行命令`mvn package && java -jar target/tblog-0.0.1-SNAPSHOT.jar` 
5. 访问 `http://localhost:8080/tblog/` 管理员默认账号：admin/123456
6. 完成!
```

## License

MIT


## 项目效果图

![](https://github.com/tzq668766/screenshots/blob/master/tblog_screenshots/tblog-index.jpg)

![](https://github.com/tzq668766/screenshots/blob/master/tblog_screenshots/tblog-personal-page.jpg)

![](https://github.com/tzq668766/screenshots/blob/master/tblog_screenshots/tblog-article-edit.jpg)