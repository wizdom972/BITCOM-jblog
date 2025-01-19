<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% pageContext.setAttribute( "newLine", "\n" ); %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog-header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<!-- 선택된 포스트의 내용 -->
					<c:choose>
						<c:when test="${not empty posts && selectedPostId != 0}">
     					   	<h4>${selectedPost.title}</h4>
        					<p>${fn:replace(selectedPost.contents, newLine, "<br>")}</p>
						</c:when>
						<c:otherwise>
							<h4>포스트를 선택하세요</h4>
							<p>포스트를 선택해 내용을 확인하세요.</p>
							<p>카테고리를 선택하면 해당 카테고리의 포스트 리스트만을 확인할 수 있습니다.</p>
						</c:otherwise>
					</c:choose>
				</div>
				<ul class="blog-list">
					<c:forEach var="post" items="${posts}">
						<li>
							<a href="${pageContext.request.contextPath}/${blog.blogId}/${post.categoryId}/${post.id}"
								class="${post.id == selectedPostId ? 'selected' : ''}">
								${post.title}
							</a> 
							<span>${post.regDate}</span>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blog.profile}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach var="category" items="${categories}">
					<li>
						<a href="${pageContext.request.contextPath}/${blog.blogId}/${category.id}"
							class="${category.id == selectedCategoryId ? 'selected' : ''}">
							${category.name}
						</a>
					</li>
				</c:forEach>
			</ul>
		</div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp" />
	</div>
</body>
</html>