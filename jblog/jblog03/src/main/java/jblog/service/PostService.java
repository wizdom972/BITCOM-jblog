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
        return postRepository.insertPost(postVo) == 1;
    }
    
    public List<PostVo> getPostsByCategoryIdAndPostId(Long categoryId, Long postId) {
        return postRepository.findByCategoryAndPost(categoryId, postId);
    }
}
