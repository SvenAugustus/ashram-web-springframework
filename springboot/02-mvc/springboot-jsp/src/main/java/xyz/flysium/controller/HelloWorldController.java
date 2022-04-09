package xyz.flysium.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zeno.cai
 */
public class HelloWorldController {

  @RequestMapping("/")
	public String hello() {
		return "index";
	}

}
