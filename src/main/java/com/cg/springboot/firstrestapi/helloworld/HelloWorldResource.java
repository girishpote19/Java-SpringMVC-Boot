package com.cg.springboot.firstrestapi.helloworld;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldResource {

	@RequestMapping("/hello-world")
	public String helloWorld() {
		return "hello World";

	}

	// created bean and return
	@RequestMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("hello World");

	}

	// path param
	@RequestMapping("/hello-world-path-param/{name}")
	public HelloWorldBean helloWorldPathParam(@PathVariable String name) {
		return new HelloWorldBean("hello World, " + name);

	}

	// multiple path param
	@RequestMapping("/hello-world-path-param/{name}/message/{message}")
	public HelloWorldBean helloWorldMultiplePathParam
	(@PathVariable String name, 
			@PathVariable String message) {
		return new HelloWorldBean("hello World, " + name + "," + message);

	}
}
