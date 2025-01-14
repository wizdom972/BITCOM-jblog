package jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jblog.security.Auth;
import jblog.service.UserService;
import jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/join")
	public String join() {
		return "user/join";
	}
	
	@PostMapping("/join")
	public String join(UserVo userVo, BindingResult result, Model model) {
		
		// 오류가 있으면 회원가입 페이지로 리다이렉트
		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel());

			return "user/join";
		}
	
		userService.join(userVo);
		
		return "redirect:/user/joinsuccess";
	}
	
	@Auth
	@GetMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}
	
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
}
