package jblog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jblog.security.Auth;
import jblog.service.BlogService;
import jblog.service.CategoryService;
import jblog.service.PostService;
import jblog.service.UserService;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;

	@RequestMapping({ "", "/", "/{path1}", "/{path1}/{path2}" })
	public String main(
			@PathVariable("id") String id,
			@PathVariable("path1") Optional<Long> path1,
			@PathVariable("path2") Optional<Long> path2,
			Model model) {

		Long categoryId = 0L;
		Long postId = 0L;

		if (path2.isPresent()) {
			categoryId = path1.get();
			postId = path2.get();
		} else if (path1.isPresent()) {
			categoryId = path1.get();
		}
		
	    // 유효성 검사
		if (!userService.isValidUser(id)) {
			return "redirect:/error/id-not-found";  // 계정이 없는 경우
		}
	    if (categoryId != 0 && !categoryService.isValidCategory(categoryId)) {
	        return "redirect:/error/category-not-found"; // 카테고리가 없는 경우
	    }
	    if (postId != 0 && !postService.isValidPost(postId)) {
	        return "redirect:/error/post-not-found"; // 포스트가 없는 경우
	    }

        model.addAttribute("blog", blogService.getBlog(id));
        model.addAttribute("categories", categoryService.getCategoriesByBlogId(id));
        
        // 포스트 목록
        if (categoryId == 0) {
            // 모든 포스트 가져오기
            model.addAttribute("posts", postService.getAllPostsByBlogId(id));
        } else {
            // 특정 카테고리의 포스트 가져오기
            model.addAttribute("posts", postService.getPostsByCategoryIdAndBlogId(categoryId, id));
        }
        
        model.addAttribute("selectedPost", postService.getPostById(postId));
        model.addAttribute("selectedCategoryId", categoryId);
        model.addAttribute("selectedPostId", postId);

		return "blog/blog-main";
	}

	@Auth(owner = true)
	@RequestMapping("/admin")
	public String adminDefault(@PathVariable("id") String id) {
		return "blog/blog-main";
	}
}
