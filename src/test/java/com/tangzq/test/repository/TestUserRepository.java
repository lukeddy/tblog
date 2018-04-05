package com.tangzq.test.repository;

import com.tangzq.model.User;
import com.tangzq.repository.UserRepository;
import com.tangzq.test.TestBase;
import com.tangzq.utils.Constants;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

import java.util.List;


public class TestUserRepository extends TestBase {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testFindAll(){
		List<User> users=(List<User>)userRepository.findAll();
		Assert.isTrue(!users.isEmpty(),"没用用户数据");
		users.forEach(u->{
			System.out.println(u);
		});
	}

	@Test
	public void testFindByUsername(){
		User user=userRepository.findByUsername(Constants.ADMIN_NAME);
		Assert.notNull(user,"用户不存在");
		System.out.println(user);
	}

	@Test
	public void testFindById(){
		User user=userRepository.findOne("599c1f9077c8cf04cd6b859e");
		Assert.notNull(user,"用户不存在");
		System.out.println(user);
	}

	@Test
	public void testFindByUsernameAndPwd(){
		User u=userRepository.findByUsernameAndPassword(Constants.ADMIN_NAME,
				DigestUtils.md5DigestAsHex(Constants.ADMIN_PWD.getBytes()));
		Assert.notNull(u,"用户不存在");
		System.out.println(u);

		u=userRepository.findByUsernameAndPassword("test",
				DigestUtils.md5DigestAsHex("test".getBytes()));
		Assert.isNull(u,"用户已经存在");
	}


	@Test
	public void testLogin(){
       Assert.isTrue(!isLogin("test","test"),"该用户已经登录");
       Assert.isTrue(isLogin(Constants.ADMIN_NAME, Constants.ADMIN_PWD),"登陆失败");

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
