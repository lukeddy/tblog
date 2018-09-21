package com.tangzq.utils;

/**
 * 系统级信息
 * @author tangzhiqiang
 */
public class Constants {
    public static final String ADMIN_NAME="admin";
    public static final String ADMIN_PWD="123456";
    public static final String ADMIN_EMAIL="admin@test.com";

    public static final String VCODE_SESSION_KEY="validateCode";

    /**
     * 已登录用户保存保存到session中的使用的key名称
     */
    public static final String LOGIN_USER_SESSION_KEY="loginUser";

    /**
     * 上传文件保存目录
     */
    public static final String UPLOAD_FOLDER="upload";

    /**
     * 头像保存目录
     */
    public static final String UPLOAD_AVATAR_FOLDER=UPLOAD_FOLDER+"/avatar";

}
