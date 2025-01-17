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

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;

	@RequestMapping({ "", "/{path1}", "/{path1}/{path2}" })
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

        model.addAttribute("blog", blogService.getBlog(id));
        model.addAttribute("categories", categoryService.getCategoriesByBlogId(id));
        model.addAttribute("posts", postService.getPostsByCategoryIdAndPostId(categoryId, postId));
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
