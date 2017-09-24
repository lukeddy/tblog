package com.tangzq.controller;

import com.tangzq.service.CategoryService;
import com.tangzq.service.TopicService;
import com.tangzq.service.UserService;
import com.tangzq.utils.CommonProps;
import com.tangzq.utils.ValidateCode;
import com.tangzq.vo.IndexVo;
import com.tangzq.vo.LoginUserVo;
import com.tangzq.vo.PageVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 首页控制器
 */
@Controller
public class HomeController {

    public static final String VCODE_SESSION_KEY="validateCode";

    @Value("${appname}")
    private String configAppName;

    @Autowired
    private UserService userService;


    @Autowired
    private TopicService topicService;

    @Autowired
    private CategoryService categoryService;


    /**
     * 跳转到首页
     * @param vo 首页参数封装
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/home")
    public String index(IndexVo vo, ModelMap modelMap) {
        modelMap.addAttribute("pager",topicService.findByPage(vo));
        modelMap.addAttribute("catList",categoryService.findAll());
        modelMap.addAttribute("indexVo",vo);
        return "index";
    }

    /**
     * 登陆页面
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    /**
     * 用户登录
     * @return
     */
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String doLogin(LoginUserVo user, HttpSession session,
                          RedirectAttributes redirectAttributes){

        String vcodeInSession = (String) session.getAttribute(VCODE_SESSION_KEY);
        String submitCode = user.getValidateCode();

        if (StringUtils.isEmpty(submitCode) || !StringUtils.equals(vcodeInSession,submitCode)) {
            redirectAttributes.addFlashAttribute("message","验证码错误！");
            return "redirect:/login";
        }
        if(!userService.isUserValid(user.getUsername(),user.getPassword())){
            redirectAttributes.addFlashAttribute("message","用户名或者密码错误");
            return "redirect:/login";
        }
        session.setAttribute(CommonProps.LOGIN_USER_SESSION_KEY,userService.findUser(user.getUsername(),user.getPassword()));
        return "index";
    }

    /**
     * 生成验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/validateCode")
    public void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-cache");
        String verifyCode = ValidateCode.generateTextCode(ValidateCode.TYPE_ALL_MIXED, 4, null);
        request.getSession().setAttribute(VCODE_SESSION_KEY, verifyCode);
        response.setContentType("image/jpeg");
        BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);
        ImageIO.write(bim, "JPEG", response.getOutputStream());
    }


    /**
     * 退出系统
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }

    /**
     * 关于页面
     * @return
     */
    @RequestMapping(value = "/about")
    public String about() {
        return "about";
    }


}
