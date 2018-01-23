package com.cocosongying.demo.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocosongying.demo.doman.Result;
import com.cocosongying.demo.exception.UserException;
import com.cocosongying.demo.utils.ResultUtil;

@ControllerAdvice
public class ExceptionHandle {
	
	private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Result handle(Exception e) {
		if (e instanceof UserException) {
			UserException uexp = (UserException) e;
			return ResultUtil.error(uexp.getCode(), uexp.getMessage());
		}else{
			logger.error("【系统异常】{}", e);
			return ResultUtil.error(-1, e.getMessage());
		}
		
	}
}
