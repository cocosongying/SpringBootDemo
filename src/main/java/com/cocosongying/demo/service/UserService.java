package com.cocosongying.demo.service;

import org.springframework.stereotype.Service;

import com.cocosongying.demo.consist.ResultEnum;
import com.cocosongying.demo.doman.User;
import com.cocosongying.demo.exception.UserException;

@Service
public class UserService {
	
	public static User getName(String name) throws UserException {
		if("error".equals(name)){
			throw new UserException(ResultEnum.UNKNOW_ERROR);
		}
		User user = new User();
		user.setName(name);
		return user;
	}

}
