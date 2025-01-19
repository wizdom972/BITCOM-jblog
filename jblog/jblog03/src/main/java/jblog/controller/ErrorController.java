package jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping("/id-not-found")
    public String handleIdNotFound(Model model) {
        model.addAttribute("errorMessage", "존재하지 않는 블로그입니다.");
        return "errors/invalid-id-error";
    }

    @RequestMapping("/category-not-found")
    public String handleCategoryNotFound(Model model) {
        model.addAttribute("errorMessage", "존재하지 않는 카테고리입니다.");
        return "errors/invalid-id-error";
    }

    @RequestMapping("/post-not-found")
    public String handlePostNotFound(Model model) {
        model.addAttribute("errorMessage", "존재하지 않는 포스트입니다.");
        return "errors/invalid-id-error";
    }
    
}
