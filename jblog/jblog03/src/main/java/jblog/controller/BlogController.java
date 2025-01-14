package jblog.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@RequestMapping({"", "/{path1}", "/{path1}/{path2}"})
	public String main(
			@PathVariable("id") String id,
			@PathVariable("path1") Optional<Long> path1,
			@PathVariable("path2") Optional<Long> path2) {
		
		Long categoryId = 0L;
		Long postId = 0L;
		
		if (path2.isPresent()) {
			categoryId = path1.get();
			postId = path2.get();
		} else if (path1.isPresent()) {
			categoryId = path1.get();
		}
		
		// 서비스에서~
		// categoryId == 0L -> default categoryId로 set
		// postId == 0L -> default postId로 set
		System.out.println("BlogController.main(" + id + ", " + categoryId + ", " + postId + ")");
		
		return "blog/main";
	}
	
	// @Auth 달아줘야함~~
	@RequestMapping("/admin")
	public String adminDefault(@PathVariable("id") String id) {
		return "blog/main";
	}
}
