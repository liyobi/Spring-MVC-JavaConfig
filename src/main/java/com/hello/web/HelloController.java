package com.hello.web;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	private final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(Map<String, Object> model) {

		logger.debug("hello() is executed");
		model.put("title", "hello world");
		model.put("msg", "Hello World Example");
		
		return "index";
	}

	@RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
	public ModelAndView hello( @PathVariable("name") String name) {

		logger.debug("hello() is executed - $name {}", name);

		ModelAndView model = new ModelAndView();
		
		model.addObject("title", "hello world "+name);
		model.addObject("msg", "Hello World Example");
		
		
		model.setViewName("index");
		
		return model;

	}

}