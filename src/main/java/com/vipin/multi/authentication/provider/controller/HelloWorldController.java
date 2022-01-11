package com.vipin.multi.authentication.provider.controller;

import java.util.StringJoiner;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	@GetMapping("/hello-world-bean/{name}")
	public String helloWorldBean(@PathVariable String name) {
		return new StringJoiner(" ").add("Hello World!").add(name).toString();
	}
}
