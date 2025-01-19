package jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jblog.repository.PostRepository;
import jblog.vo.PostVo;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
    // 글 작성
    public boolean createPost(PostVo postVo) {
        return postRepository.insertPost(postVo) > 0;
    }
    
    // 모든 포스트 가져오기
    public List<PostVo> getAllPostsByBlogId(String blogId) {
        return postRepository.findAllPostsByBlogId(blogId);
    }
    
    // 특정 카테고리의 모든 포스트 가져오기
    public List<PostVo> getPostsByCategoryId(Long categoryId) {
        return postRepository.findPostsByCategoryId(categoryId);
    }
    
    // 특정 포스트 가져오기
    public PostVo getPostById(Long postId) {
        return postRepository.findPostById(postId);
    }

}