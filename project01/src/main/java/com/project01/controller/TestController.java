package com.project01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class TestController {
	
//	@GetMapping("/testGetParameter")
//	public String testGetParameter(@ModelAttribute("name") String name) {
//		System.out.println("name : " + name);
//		
//		return "testGet";
//	}

	@RequestMapping("/testGetParameter/{name}")
	public String testGetParameter(@PathVariable String name) {
		System.out.println(name);
		
		return "testGet";
	}
}
