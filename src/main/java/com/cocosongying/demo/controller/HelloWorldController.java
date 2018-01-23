package com.cocosongying.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping(value="/helloworld")
	public String sayHello(){
		return "Hello World";
	}
}
