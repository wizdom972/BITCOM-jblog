package jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import jblog.security.Auth;
import jblog.service.BlogService;
import jblog.service.CategoryService;
import jblog.service.FileUploadService;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;

@Auth(owner = true)
@Controller
@RequestMapping("{id}/admin")
public class AdminController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private CategoryService categoryService;
	
    // 모든 관리 페이지에 blog 데이터를 추가
    @ModelAttribute("blog")
    public BlogVo getBlog(@PathVariable("id") String id) {
        return blogService.getBlog(id);
    }
	
	// 블로그 관리 기본 페이지
    @GetMapping("/basic")
    public String adminBasic(@PathVariable("id") String id, Model model) {
        return "blog/blog-admin-basic";
    }

    // 카테고리 관리 페이지
    @GetMapping("/category")
    public String adminCategory(@ModelAttribute("blog") BlogVo blog, Model model) {
    	model.addAttribute("categories", categoryService.getCategoriesByBlogId(blog.getBlogId()));
    	
        return "blog/blog-admin-category";
    }

    // 글쓰기 페이지
    // 글쓰기 페이지에 카테고리 데이터 가져오기
    @GetMapping("/write")
    public String adminWrite(@PathVariable("id") String id, Model model) {
        // 블로그 id에 속한 카테고리 목록 가져오기
        List<CategoryVo> categories = categoryService.getCategoriesByBlogId(id);

        // 모델에 카테고리 목록 추가
        model.addAttribute("categories", categories);

        return "blog/blog-admin-write"; // 글 작성 JSP 파일로 이동
    }
    
    // 블로그 설정 업데이트
    @PostMapping("/updateProfile")
    public String updateProfile(
            @PathVariable("id") String id,
            @RequestParam("title") String title,
            @RequestParam("logo-file") MultipartFile logoFile,
            HttpSession session) {
    	
        // 이미지 업로드 처리
        String profileUrl = fileUploadService.restore(logoFile);

        // Blog 정보 업데이트
        BlogVo blogVo = new BlogVo();
        blogVo.setBlogId(id);
        blogVo.setTitle(title);
        blogVo.setProfile(profileUrl != null ? profileUrl : blogService.getBlog(id).getProfile());
        blogService.updateBlog(blogVo);

        return "redirect:/{id}/admin/basic";
    }
}
