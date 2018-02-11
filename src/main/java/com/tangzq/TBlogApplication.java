package com.yocool;

import com.yocool.model.User;
import com.yocool.repo.UserRepository;
import com.yocool.utils.CommonProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.DigestUtils;

/**
 * 应用启动入口类
 */
@SpringBootApplication(scanBasePackages = "com.yocool")
@EnableAutoConfiguration
public class TBlogApplication implements CommandLineRunner {

	@Autowired
	private UserRepository  userRepository;

	public static void main(String[] args) {
		SpringApplication.run(TBlogApplication.class, args);
	}

	/**
	 * 通过实现CommandLineRunner接口，在应用启动时调用
	 * @param strings
	 * @throws Exception
     */
	@Override
	public void run(String... strings) throws Exception {
		initUser();
	}

	/**
	 * 初始化系统管理员
	 */
	private void initUser(){
		User u=userRepository.findByUsername(CommonProps.ADMIN_NAME);
		if(null==u){
			u=new User();
			u.setUsername(CommonProps.ADMIN_NAME);
			u.setPassword(DigestUtils.md5DigestAsHex(CommonProps.ADMIN_PWD.getBytes()));
			u.setEmail(CommonProps.ADMIN_EMAIL);
			userRepository.save(u);
			System.out.println("初始化管理员账号成功！");
		}else{
			System.out.println("管理员账号已经存在");
		}
	}


}
