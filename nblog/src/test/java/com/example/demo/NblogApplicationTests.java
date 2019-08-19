package com.example.demo;

import com.nblog.NblogApplication;
import com.nblog.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NblogApplication.class)
public class NblogApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	RedisTemplate<String, User> redisTemplate;
	@Test
	public void test()  {
		User user = new User();
		user.setUsername("aaa");
		user.setPassword("123456");
		// 保存字符串
		redisTemplate.opsForValue().set("user", user, 300, TimeUnit.SECONDS);

		System.out.println(redisTemplate.opsForValue().get("user").toString());


	}

}
