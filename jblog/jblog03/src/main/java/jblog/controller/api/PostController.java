package jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jblog.service.PostService;
import jblog.vo.PostVo;

@Controller
@RequestMapping("/api/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
    @PostMapping
    public String createPost(
            @ModelAttribute PostVo postVo,
            RedirectAttributes redirectAttributes) {
    	
        boolean isCreated = postService.createPost(postVo);
        
        if (isCreated) {
            redirectAttributes.addFlashAttribute("message", "글이 성공적으로 작성되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("message", "글 작성 중 문제가 발생했습니다.");
        }
        
        return "redirect:/" + postVo.getCategoryId() + postVo.getId();
    }
}
