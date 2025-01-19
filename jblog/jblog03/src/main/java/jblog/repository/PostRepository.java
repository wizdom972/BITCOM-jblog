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
    
    public List<PostVo> findAllPostsByBlogId(String blogId) {
        return sqlSession.selectList("post.findAllPostsByBlogId", blogId);
    }
    
	public List<PostVo> findPostsByCategoryIdAndBlogId(Long categoryId, String blogId) {
		return sqlSession.selectList("post.findPostsByCategoryIdAndBlogId", Map.of("categoryId", categoryId, "blogId", blogId));
	}    

    public PostVo findPostById(Long postId) {
        return sqlSession.selectOne("post.findPostById", postId);
    }

	public int deletePostsByCategoryId(Long categoryId) {
		return sqlSession.delete("post.deletePostsByCategoryId", categoryId);
	}

}
