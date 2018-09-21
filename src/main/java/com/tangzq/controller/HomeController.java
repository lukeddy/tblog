package com.tangzq.controller;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.tangzq.model.User;
import com.tangzq.service.CategoryService;
import com.tangzq.service.TopicService;
import com.tangzq.service.UserService;
import com.tangzq.utils.Constants;
import com.tangzq.vo.IndexVo;
import com.tangzq.vo.LoginUserVo;
import com.tangzq.vo.RegisterUserVo;
import com.tangzq.vo.SearchVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

/**
 * 首页控制器
 * @author tangzhiqiang
 */
@Controller
@Slf4j
public class HomeController {


    @Value("${appname}")
    private String configAppName;


    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

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
    @RequestMapping(value = "/")
    public String index(IndexVo vo, ModelMap modelMap) {
        modelMap.addAttribute("pager",topicService.findByPage(vo));
        modelMap.addAttribute("catList",categoryService.findAll());
        modelMap.addAttribute("indexVo",vo);
        log.info(vo.toString());
        return "index";
    }

    @RequestMapping(value="/search")
    public String search(SearchVo searchVo, ModelMap modelMap){
        modelMap.addAttribute("searchVo",searchVo);
        modelMap.addAttribute("pager",topicService.search(searchVo));
        return "search";
    }

    /**
     * 注册页面
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(ModelMap model) {
        model.addAttribute("registerForm",new RegisterUserVo());
        return "register";
    }


    /**
     * 用户登录
     * @return
     */
    @RequestMapping(value="/register",method = RequestMethod.POST)
    public String doegister(@Valid @ModelAttribute("registerForm") RegisterUserVo registerUser, BindingResult result,
                            HttpSession session,
                            ModelMap model,
                            RedirectAttributes redirectAttributes){

        if(result.hasErrors()){
            return "register";
        }

        String vcodeInSession = (String) session.getAttribute(Constants.VCODE_SESSION_KEY);
        String submitCode = registerUser.getValidateCode();
        if (!StringUtils.equals(vcodeInSession,submitCode)) {
            result.rejectValue("validateCode",null,"验证码错误!");
        }
        if(null!=userService.findByUsername(registerUser.getUsername())){
            result.rejectValue("username",null,"该用户名已经存在");
        }
        if(null!=userService.findUserByEmail(registerUser.getEmail())){
            result.rejectValue("email",null,"该邮箱已经被注册");
        }
        if(result.hasErrors()){
            return "register";
        }

        User savedUser=userService.createUser(registerUser);
        if(null!=savedUser&&savedUser.getId()!=null){
            redirectAttributes.addFlashAttribute("messageSuc","注册成功！");
            return "redirect:/login";
        }else{
            model.addAttribute("messageErr","注册失败");
            model.addAttribute("vo",registerUser);
            return "register";
        }
    }


    /**
     * 登陆页面
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(ModelMap model) {
        model.addAttribute("loginForm",new LoginUserVo());
        return "login";
    }

    /**
     * 用户登录
     * @return
     */
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String doLogin(@Valid @ModelAttribute("loginForm") LoginUserVo user, BindingResult result,
                          HttpSession session,
                          ModelMap model,
                          RedirectAttributes redirectAttributes){

        if(result.hasErrors()){
            return "login";
        }

        String vcodeInSession = (String) session.getAttribute(Constants.VCODE_SESSION_KEY);
        String submitCode = user.getValidateCode();

        if (!StringUtils.equals(vcodeInSession,submitCode)) {
            result.rejectValue("validateCode",null,"验证码错误!");
            return "login";
        }
        if(!userService.isUserValid(user.getUsername(),user.getPassword())){
            model.addAttribute("messageErr","用户名或者密码错误");
            return "login";
        }

        session.setAttribute(Constants.LOGIN_USER_SESSION_KEY,userService.findUser(user.getUsername(),user.getPassword()));
        return "redirect:/";
    }



    /**
     * 生成验证码
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/validateCode")
    public void validateCode(HttpServletResponse response,HttpSession session) throws IOException {
        String createText = captchaProducer.createText();
        session.setAttribute(Constants.VCODE_SESSION_KEY, createText);
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ImageIO.write(captchaProducer.createImage(createText), "JPEG", response.getOutputStream());
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
