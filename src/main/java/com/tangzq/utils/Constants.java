package com.tangzq.utils;

/**
 * 系统级信息
 * @author luke
 */
public class Constants {
    public static final String ADMIN_NAME="admin";
    public static final String ADMIN_PWD="123456";
    public static final String ADMIN_EMAIL="admin@test.com";

    /**
     * 验证码Session Key名称
     */
    public static final String VCODE_SESSION_KEY="validateCode";

    /**
     * 已登录用户保存保存到session中的使用的key名称
     */
    public static final String LOGIN_USER_SESSION_KEY="loginUser";

    /**
     * API已登录用户ID
     */
    public static final String API_LOGIN_USER_ID_KEY="api_logined_uid";

    /**
     * 上传文件保存目录
     */
    public static final String UPLOAD_FOLDER="upload";

    /**
     * 头像保存目录
     */
    public static final String UPLOAD_AVATAR_FOLDER=UPLOAD_FOLDER+"/avatar";

}
