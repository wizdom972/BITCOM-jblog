<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<h1 class="logo">JBlog</h1>
<ul class="menu">
    <c:choose>
    	<%-- 비로그인 사용자 --%>
        <c:when test="${empty authUser}">
            <li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
            <li><a href="${pageContext.request.contextPath}/user/join">회원가입</a></li>
        </c:when>
        
        <%-- 로그인한 사용자 --%>
        <c:otherwise>
            <li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
            <li><a href="${pageContext.request.contextPath}/${authUser.id}">내블로그</a></li>
        </c:otherwise>
    </c:choose>
</ul>