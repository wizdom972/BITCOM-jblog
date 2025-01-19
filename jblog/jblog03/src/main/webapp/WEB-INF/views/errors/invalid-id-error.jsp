<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error</title>
</head>
<body>
    <h1>에러 페이지</h1>
    <p>${errorMessage}</p>
    <a href="${pageContext.request.contextPath}">메인 페이지로 이동</a>
</body>
</html>