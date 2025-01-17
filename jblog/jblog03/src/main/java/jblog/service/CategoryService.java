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

	public List<CategoryVo> getCategoriesByBlogId(String blogId) {
		return categoryRepository.findByBlogId(blogId);
	}
	
    public List<Map<String, Object>> getCategoryWithPostCount(String blogId) {
        return categoryRepository.findCategoryWithPostCount(blogId);
    }
	
    public void addCategory(CategoryVo categoryVo) {
        categoryRepository.insert(categoryVo);
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.delete(categoryId);
    }
}
