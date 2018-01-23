package com.cocosongying.demo.exception;

import com.cocosongying.demo.consist.ResultEnum;

public class UserException extends RuntimeException {

	private static final long serialVersionUID = 3190041987455681080L;
	private Integer code;
	
	public UserException(ResultEnum resultEnum) {
		super(resultEnum.getMsg());
		this.code = resultEnum.getCode();
	}
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
}
