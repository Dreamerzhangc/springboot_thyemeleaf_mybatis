package cn.edu.nyist.springboot07_jsp_mybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    //到主页面
	@RequestMapping(path = "/tomain")
	public String tochangetheme() {
		return "main";
	}
}
