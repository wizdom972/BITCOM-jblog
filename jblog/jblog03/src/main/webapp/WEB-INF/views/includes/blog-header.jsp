<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="header">
	<h1><a href="${pageContext.request.contextPath}/${blog.blogId}" >${blog.title}</a></h1>
	<ul>
		<c:choose>
			<%-- 비로그인 사용자 --%>
			<c:when test="${empty authUser}">
				<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
			</c:when>
			
			<%-- 로그인한 사용자 --%>
			<c:otherwise>
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
				<li><a href="${pageContext.request.contextPath}/${blog.blogId}/admin/basic">블로그 관리</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>
