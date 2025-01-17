package jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jblog.vo.PostVo;

@Repository
public class PostRepository {

	@Autowired
	private SqlSession sqlSession;
	
    public int insertPost(PostVo postVo) {
        return sqlSession.insert("post.insert", postVo);
    }
    
    public List<PostVo> findByCategoryAndPost(Long categoryId, Long postId) {
        return sqlSession.selectList("post.findByCategoryAndPost", Map.of("categoryId", categoryId, "postId", postId));
    }
}
