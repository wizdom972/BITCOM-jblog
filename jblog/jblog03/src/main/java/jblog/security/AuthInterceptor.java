package jblog.security;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jblog.vo.UserVo;

public class AuthInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 1. 핸들러가 메서드인지 확인
		if (!(handler instanceof HandlerMethod)) {
			return true; // 정적 리소스 등의 요청은 통과
		}

		// 2. 캐스팅
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		// 3. handler에서 @Auth 애너테이션 가져오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		if (auth == null) {
			auth = handlerMethod.getBeanType().getAnnotation(Auth.class);
		}

		// 4. 인증이 필요 없는 경우 통과
		if (auth == null || !auth.required()) {
			return true;
		}

		// 5. 세션에서 인증 정보 확인
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("authUser") == null) {
			// 만약 인증되지 않았다면 로그인 페이지로 리다이렉트
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");

		// 6. 본인 확인이 필요한 경우 URL의 ID와 인증된 사용자의 ID 비교
		if (auth.owner()) {
			String pathUserId = request.getRequestURI().split("/")[2]; // URL에서 사용자 ID 추출
			if (!authUser.getId().equals(pathUserId)) {
				// 사용자와 접근하려는 페이지의 사용자 권한이 일치하지 않으면
				// 로그인 페이지로 리다이렉트
				response.sendRedirect(request.getContextPath() + "/user/login");
				return false;
			}
		}

		// 7. @Auth가 붙어있고 인증도 된 경우
		return true; // 인증된 사용자만 요청 허용
	}
}
