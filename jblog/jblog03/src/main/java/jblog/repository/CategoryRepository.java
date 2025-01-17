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
    
    public void delete(Long categoryId) {
        sqlSession.delete("category.delete", categoryId);
    }
}
