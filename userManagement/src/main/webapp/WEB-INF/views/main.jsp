<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>User 관리 프로젝트</title>
</head>
<body>
  
  <%-- 로그인이 안되어 있는 경우
    == session scope에 "loginUser"가 없는 경우
   --%>
  <c:if test="${empty sessionScope.loginUser}">

    <h1>Login</h1>
    <form action="/login" method="post">
      <div>
        ID : <input type="text" name="userId">
      </div>
      <div>
        PW : <input type="password" name="userPw">
      </div>
      <div>
        <button>로그인</button>
        <a href="/signUp">사용자 등록</a>
      </div>
    </form>
  </c:if>
  
  <%-- 로그인 상태인 경우 --%>
	<c:if test="${not empty sessionScope.loginUser}">

		<h1>${loginUser.userName}님이접속하셨습니다</h1>
		<ul>
			<li>userNo : ${loginUser.userNo}</li>
			<li>userId : ${loginUser.userId}</li>
			<li>userName : ${loginUser.userName}</li>
			<li>enrollDate : ${loginUser.enrollDate}</li>
		</ul>

		<button id="logout">logout</button>


		<hr>

		<ul>
		
			<li><a href="/selectAll">사용자 목록 조회</a></li>
		</ul>
	</c:if>




  <c:if test="${!empty sessionScope.message}">
    <script>
      alert("${sessionScope.message}");
    </script>

    <c:remove var="message" scope="session" />
  </c:if>
<script src="/resources/js/main.js"></script>
</body>
</html>