<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>서버 오류</title>
</head>
<body>
    <h1>500 - 서버 오류</h1>
    <p>서버에서 오류가 발생했습니다. 관리자에게 문의해주세요.</p>
    <a href="${pageContext.request.contextPath}">메인 페이지로 이동</a>
</body>
</html>
