package jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jblog.repository.BlogRepository;
import jblog.repository.CategoryRepository;
import jblog.repository.UserRepository;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import jblog.vo.UserVo;

@Service
public class UserService {
	
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CategoryRepository categoryRepository;
	
	public void join(UserVo userVo) {
		
		// 회원 정보 저장
		userRepository.insert(userVo);
		
		// 회원 블로그 생성
		BlogVo blogVo = new BlogVo();
        blogVo.setBlogId(userVo.getId());
        blogVo.setTitle(userVo.getName() + "의 블로그");
        blogVo.setProfile("/assets/images/spring-logo.jpg"); // 기본 프로필 이미지 경로
        blogRepository.insert(blogVo);
        
        // 기본 카테고리 생성
        CategoryVo categoryVo = new CategoryVo();
        categoryVo.setBlogId(userVo.getId());
        categoryVo.setName("기본 카테고리");
        categoryVo.setDescription(null);
        categoryRepository.insert(categoryVo);
	}

	public UserVo getUser(String id, String password) {
		return userRepository.findByIdAndPassword(id, password);
	}
}
