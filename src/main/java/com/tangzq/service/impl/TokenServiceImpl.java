package com.tangzq.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tangzq.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Token接口实现类
 * @author tangzhiqiang
 */
@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

    /**
     * token secret
     */
    public static final String TOKEN_SECRET = "s4T2zO6Pma8UH1sxq";

    /**
     * token过期时间：10天
     */
    public static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 10;

    @Override
    public String createToken(String userId) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            String token = JWT.create()
                    .withClaim("userId", userId)
                    .withClaim("createdAt", new Date())
                    //token 10天后过期
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .sign(algorithm);
            return token;
        } catch (UnsupportedEncodingException exception) {
            log.error("create token error",exception);
        } catch (JWTCreationException exception) {
            log.error("create token error",exception);
        }
        return null;
    }

    @Override
    public String getUserIdFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            String userId=jwt.getClaim("userId").asString();
            log.info("user ID from token:"+userId);
            return userId;
        } catch (UnsupportedEncodingException exception) {
            log.error("parse token error",exception);
            return null;
        } catch (JWTVerificationException exception) {
            log.error("parse token error",exception);
            return null;
        }
    }

    @Override
    public boolean isTokenValid(String token) {
        //TODO 验证Token的时效性
        return this.getUserIdFromToken(token) != null;
    }
}
