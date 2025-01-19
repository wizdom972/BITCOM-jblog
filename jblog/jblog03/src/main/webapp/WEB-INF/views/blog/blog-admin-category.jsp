<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
    $(document).ready(function () {
        const contextPath = "${pageContext.request.contextPath}";
        const blogId = "${blog.blogId}";
        
        // 카테고리 목록 로드
        function loadCategories() {
            $.ajax({
                url: contextPath + "/api/category/" + blogId,
                type: "GET",
                dataType: "JSON",
                success: function (categories) {
                	
                    $("#categoryTable").find("tr:gt(0)").remove(); // 기존 목록 삭제
                    
                    categories.forEach(function (category) {
                    	const description = category.description || "";
                    	const isDefaultCategory = category.id === 1; // 기본 카테고리인지 확인
                    	
                        $("#categoryTable").append(`
                            <tr data-id=${"${category.id}"}>
                                <td>${"${category.id}"}</td>
                                <td>${"${category.name}"}</td>
                                <td>${"${category.postCount}"}</td>
                                <td>${"${description}"}</td>
                                <td>
	                                <img 
		                                src="${"${contextPath}"}/assets/images/delete.jpg" 
		                                class="delete-btn" 
		                                data-id=${"${category.id}"} 
		                                alt="삭제" 
		                                style="cursor: pointer;"
	                            	/>
                                </td>
                            </tr>
                        `);
                    });
                },
                error: function (err) {
                    console.error(err);
                }
            });
        }

        // 페이지 로드 시 카테고리 목록 가져오기
        loadCategories();

        // 카테고리 추가
        $("#addCategoryBtn").click(function () {
            const name = $("#categoryName").val();
            const description = $("#categoryDesc").val();

            if (!name) {
                alert("카테고리명을 입력해주세요.");
                return;
            }

            $.ajax({
                url: contextPath + "/api/category/" + blogId,
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify({ name: name, description: description }),
                success: function (response) {
                    if (response.success) {
                        alert("카테고리가 추가되었습니다.");
                        loadCategories(); // 목록 갱신
                        $("#categoryName").val(""); // 입력 필드 초기화
                        $("#categoryDesc").val("");
                    } else {
                        alert("카테고리 추가에 실패했습니다.");
                    }
                },
                error: function (err) {
                    console.error(err);
                    alert("카테고리 추가 중 오류가 발생했습니다.");
                }
            });
        });

        // 카테고리 삭제
        $(document).on("click", ".delete-btn", function () {
            const categoryId = $(this).data("id");
            console.log(categoryId);

            if (!confirm("정말 삭제하시겠습니까?")) {
                return;
            }

            $.ajax({
                url: contextPath + "/api/category/" + categoryId,
                type: "DELETE",
                success: function (response) {
                    if (response.success) {
                        alert("카테고리가 삭제되었습니다.");
                        loadCategories(); // 목록 갱신
                    } else {
                        alert("카테고리 삭제에 실패했습니다.");
                    }
                },
                error: function (err) {
                    console.error(err);
                    alert("카테고리 삭제 중 오류가 발생했습니다.");
                }
            });
        });
    });
</script>
</head>
<body>
    <div id="container">
        <c:import url="/WEB-INF/views/includes/blog-header.jsp" />
        <div id="wrapper">
            <div id="content" class="full-screen">
                <ul class="admin-menu">
                    <li><a href="${pageContext.request.contextPath}/${blog.blogId}/admin/basic">기본설정</a></li>
                    <li class="selected">카테고리</li>
                    <li><a href="${pageContext.request.contextPath}/${blog.blogId}/admin/write">글작성</a></li>
                </ul>
                <table class="admin-cat" id="categoryTable">
                    <tr>
                        <th>번호</th>
                        <th>카테고리명</th>
                        <th>포스트 수</th>
                        <th>설명</th>
                        <th>삭제</th>              
                    </tr>
                    
                </table>

                <h4 class="n-c">새로운 카테고리 추가</h4>
                <table id="admin-cat-add">
                    <tr>
                        <td class="t">카테고리명</td>
                        <td><input type="text" name="name" id="categoryName"></td>
                    </tr>
                    <tr>
                        <td class="t">설명</td>
                        <td><input type="text" name="desc" id="categoryDesc"></td>
                    </tr>
                    <tr>
                        <td class="s">&nbsp;</td>
                        <td><button id="addCategoryBtn">카테고리 추가</button></td>
                    </tr>                      
                </table> 
            </div>
        </div>
        <c:import url="/WEB-INF/views/includes/blog-footer.jsp" />
    </div>
</body>
</html>