package com.vipin.multi.authentication.provider.controller;

import java.util.StringJoiner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.concurrent.DelegatingSecurityContextRunnable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping("/hello-world-bean/{name}")
	// @Async
	public String helloWorldBean(@PathVariable String name) {
		Runnable runnable = () -> {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			System.out.println(new StringJoiner(" ").add("Hello World!").add(name)
					.add((String) authentication.getPrincipal()).toString());
		};
		//DelegatingSecurityContextRunnable dr = new DelegatingSecurityContextRunnable(runnable);
	
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		//DelegatingSecurityContextExecutorService des = new DelegatingSecurityContextExecutorService(executorService);
		executorService.execute(runnable);
		executorService.shutdown();
		return new StringJoiner(" ").add("Hello World!").add(name).toString();
	}
}
