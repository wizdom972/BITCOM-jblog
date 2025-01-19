<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>잘못된 요청</title>
</head>
<body>
    <h1>400 - 잘못된 요청</h1>
    <p>요청이 올바르지 않습니다.</p>
    <a href="${pageContext.request.contextPath}">메인 페이지로 이동</a>
</body>
</html>
