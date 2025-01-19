package jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import jblog.service.PostService;
import jblog.vo.PostVo;
import jblog.vo.UserVo;

@Controller
@RequestMapping("/api/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
    @PostMapping
    public String createPost(
            @ModelAttribute PostVo postVo,
            HttpSession session) {
    	
    	UserVo authUser = (UserVo) session.getAttribute("authUser");
  	    String blogId = authUser.getId();
    	
  	    postService.createPost(postVo);
        
        return "redirect:/" + blogId + "/" + postVo.getCategoryId() + "/" + postVo.getId();
    }
}
