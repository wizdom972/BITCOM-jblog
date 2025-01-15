package jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jblog.repository.BlogRepository;
import jblog.vo.BlogVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogRepository blogRepository;

	// 블로그 정보 가져오기
	public BlogVo getBlog(String id) {
		return blogRepository.findById(id);
	}

	// 블로그 정보 업데이트
	public void updateBlog(BlogVo blogVo) {
		blogRepository.update(blogVo);
	}
}
