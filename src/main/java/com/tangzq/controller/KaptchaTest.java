package com.tangzq.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 测试验证码
 */
@Slf4j
@Controller
public class KaptchaTest {
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    private static final String SESSION_KEY_VCODE="verifyCode";

    /**
     * 生成验证码
     * @param session
     * @param response
     * @throws Exception
     */
    @RequestMapping("/vcode")
    public void vcode(HttpSession session, HttpServletResponse response) throws Exception{
            String createText = defaultKaptcha.createText();
            session.setAttribute(SESSION_KEY_VCODE, createText);
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            ImageIO.write(defaultKaptcha.createImage(createText), "JPEG", response.getOutputStream());
    }

    /**
     * 验证
     * @param vcode
     * @return
     */
    @RequestMapping(value = "/doVerify",method = RequestMethod.POST)
    public String doVerify(@RequestParam("vcode")String vcode,ModelMap model,HttpSession session,
            RedirectAttributes redirectAttributes){
        String captchaId = (String) session.getAttribute(SESSION_KEY_VCODE);
        log.info("Session  verifyCode "+captchaId+", client verifyCode "+vcode);
        if (!captchaId.equals(vcode)) {
            model.addAttribute("info", "错误的验证码");
            return "vcodetest";
        } else {
            redirectAttributes.addFlashAttribute("info","登录成功");
            return "redirect:/vcodetest";
        }

    }



    @RequestMapping("/vcodetest")
    public String vcodetest(){
        return "vcodetest";
    }

}
