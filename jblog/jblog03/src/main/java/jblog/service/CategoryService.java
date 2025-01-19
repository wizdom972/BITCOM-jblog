package jblog.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jblog.repository.CategoryRepository;
import jblog.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private PostService postService;

	public List<CategoryVo> getCategoriesByBlogId(String blogId) {
		return categoryRepository.findByBlogId(blogId);
	}
	
    public List<Map<String, Object>> getCategoryWithPostCount(String blogId) {
        return categoryRepository.findCategoryWithPostCount(blogId);
    }
    
    public boolean isValidCategory(Long categoryId) {
        return categoryRepository.findById(categoryId) != null;
    }
	
    public void addCategory(CategoryVo categoryVo) {
        categoryRepository.insert(categoryVo);
    }

    public boolean deleteCategory(Long categoryId) {
    	postService.deletePostsByCategoryId(categoryId);
    	
        return categoryRepository.delete(categoryId);
    }
}
