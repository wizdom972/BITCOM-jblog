package jblog.repository;

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
}
