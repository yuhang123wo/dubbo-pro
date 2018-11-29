package cn.yh.st.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.yh.st.dubbo.facade.UserService;

@RestController
public class TestController {

	@Autowired
	private UserService userService;

	@RequestMapping("testDubbo")
	public Object testDubbo() {
		return userService.findUserById(1);
	}
}
