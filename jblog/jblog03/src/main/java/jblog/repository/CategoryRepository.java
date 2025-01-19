package jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	
	@Autowired
    private SqlSession sqlSession;

    public int insert(CategoryVo categoryVo) {
        return sqlSession.insert("category.insert", categoryVo);
    }
    
    public List<CategoryVo> findByBlogId(String blogId) {
        return sqlSession.selectList("category.findByBlogId", blogId);
    }
    
    public List<Map<String, Object>> findCategoryWithPostCount(String blogId) {
        return sqlSession.selectList("category.findCategoryWithPostCount", blogId);
    }
    
    public boolean delete(Long categoryId) {
        return sqlSession.delete("category.delete", categoryId) > 0;
    }

	public CategoryVo findById(Long categoryId) {
		return sqlSession.selectOne("category.findById", categoryId);
	}
	
}
