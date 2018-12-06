package com.tangzq.service;

/**
 * 生成Token接口
 * @author tangzhiqiang
 */
public interface TokenService {

    /**
     * 生成Token
     * @param userId
     * @return
     */
    String createToken(String userId);

    /**
     * 从Token中获取用户ID
     * @param token
     * @return
     */
    String getUserIdFromToken(String token);

    /**
     * 验证Token是否有效
     * @param token
     * @return
     */
     boolean isTokenValid(String token);


}
