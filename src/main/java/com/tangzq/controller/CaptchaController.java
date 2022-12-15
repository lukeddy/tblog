package com.tangzq.controller;

import com.tangzq.utils.Constants;
import com.tangzq.utils.ValidateCode;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 验证码控制器
 * @author tangzhiqiang
 */
@Controller
@RequestMapping("/captcha")
public class CaptchaController
{

    /**
     * 生成验证码
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/validateCode")
    public void validateCode(HttpServletResponse response, HttpSession session) throws IOException {
        response.setHeader("Cache-Control", "no-cache");
        String verifyCode = ValidateCode.generateTextCode(ValidateCode.TYPE_ALL_MIXED, 4, null);
        session.setAttribute(Constants.VCODE_SESSION_KEY, verifyCode);
        response.setContentType("image/jpeg");
        BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);
        ImageIO.write(bim, "JPEG", response.getOutputStream());
    }
}