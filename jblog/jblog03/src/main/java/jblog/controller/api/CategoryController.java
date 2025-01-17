package jblog.controller.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jblog.service.CategoryService;
import jblog.vo.CategoryVo;

@RestController("categoryApiController")
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	// 카테고리 목록 조회
	@GetMapping("/{blogId}")
	public List<Map<String, Object>> getCategoriesWithPostCount(@PathVariable("blogId") String blogId) {
        return categoryService.getCategoryWithPostCount(blogId);
    }

    // 카테고리 추가
    @PostMapping("/{blogId}")
    public Map<String, Object> addCategory(@PathVariable("blogId") String blogId, @RequestBody CategoryVo categoryVo) {
        categoryVo.setBlogId(blogId);
        categoryService.addCategory(categoryVo);

        return Map.of(
            "success", true,
            "category", categoryVo
        );
    }

    // 카테고리 삭제
    @DeleteMapping("/{categoryId}")
    public Map<String, Object> deleteCategory(@PathVariable("categoryId") Long categoryId) {
        categoryService.deleteCategory(categoryId);

        return Map.of(
            "success", true,
            "deletedCategoryId", categoryId
        );
    }   
}
