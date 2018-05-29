package com.tangzq;

import com.tangzq.model.User;
import com.tangzq.repository.UserRepository;
import com.tangzq.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.util.DigestUtils;

/**
 * 应用启动入口类
 * @author tangzhiqiang
 */
@SpringBootApplication(scanBasePackages = "com.tangzq")
@EnableAutoConfiguration
@Slf4j
public class Application extends SpringBootServletInitializer implements CommandLineRunner {


	@Autowired
	private UserRepository userRepository;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * 通过实现CommandLineRunner接口，在应用启动时调用
	 * @param strings
	 * @throws Exception
     */
	@Override
	public void run(String... strings) throws Exception {
		log.info("开始初始化数据....");
		initUser();
		log.info("初始化数据完成....");
	}

	/**
	 * 初始化系统管理员
	 */
	private void initUser(){
		User u=userRepository.findByUsername(Constants.ADMIN_NAME);
		if(null==u){
			u=new User();
			u.setUsername(Constants.ADMIN_NAME);
			u.setPassword(DigestUtils.md5DigestAsHex(Constants.ADMIN_PWD.getBytes()));
			u.setEmail(Constants.ADMIN_EMAIL);
			userRepository.save(u);
			log.info("初始化管理员账号成功！");
		}else{
			log.warn("管理员账号已经存在");
		}
	}



}
