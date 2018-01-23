package com.cocosongying.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cocosongying.demo.configs.JdbcProperties;
import com.cocosongying.demo.configs.UserProperties;
import com.cocosongying.demo.doman.Result;
import com.cocosongying.demo.doman.User;
import com.cocosongying.demo.service.UserService;
import com.cocosongying.demo.utils.ResultUtil;

@RestController
public class HelloController {
	
	@Autowired
	private UserProperties userProperties;
	
	@Autowired
	private JdbcProperties jdbcProperties;
	
	@GetMapping(value="/hello")
	public Result sayHello(){
		User user = new User();
//		user.setId(userProperties.getId());
//		user.setName(userProperties.getName());
		
		user.setId(jdbcProperties.getIp());
		user.setName(jdbcProperties.getUsername() + jdbcProperties.getPassword());
		return ResultUtil.success(user);
	}
	
	@GetMapping(value="/hello/{name}")
	public Result sayHello(@PathVariable("name") String name) throws Exception{
		return ResultUtil.success(UserService.getName(name));
	}
	
	@PostMapping(value="/hello")
	public Result<User> helloSomeone(User user){
		return ResultUtil.success(user);
	}

}
