package com.yocool;

import com.yocool.model.User;
import com.yocool.repo.UserRepository;
import com.yocool.utils.CommonProps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TBlogApplication.class)
@WebAppConfiguration
public class TBlogApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testFindAll(){
		List<User> users=userRepository.findAll();
		Assert.isTrue(!users.isEmpty());
		users.forEach(u->{
			System.out.println(u);
		});
	}

	@Test
	public void testFindByUsername(){
		User user=userRepository.findByUsername(CommonProps.ADMIN_NAME);
		Assert.notNull(user);
		System.out.println(user);
	}

	@Test
	public void testFindById(){
		User user=userRepository.findOne("599c1f9077c8cf04cd6b859e");
		Assert.notNull(user);
		System.out.println(user);
	}

	@Test
	public void testFindByUsernameAndPwd(){
		User u=userRepository.findByUsernameAndPassword(CommonProps.ADMIN_NAME,
				DigestUtils.md5DigestAsHex(CommonProps.ADMIN_PWD.getBytes()));
		Assert.notNull(u);
		System.out.println(u);

		u=userRepository.findByUsernameAndPassword("test",
				DigestUtils.md5DigestAsHex("test".getBytes()));
		Assert.isNull(u);
	}


	@Test
	public void testLogin(){
       Assert.isTrue(!isLogin("test","test"));
       Assert.isTrue(isLogin(CommonProps.ADMIN_NAME,CommonProps.ADMIN_PWD));

	}

	private boolean isLogin(String uname,String pwd){
		User user=userRepository.findByUsernameAndPassword(uname,DigestUtils.md5DigestAsHex(pwd.getBytes()));
		if(null==user){
			System.out.println("账号或者密码错误！");
			return false;
		}else{
			System.out.println("登陆成功！");
			return true;
		}
	}
}
