<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="menubar.jsp"/>
	
	<!-- 에러문구 받아옴~~ -->
	<h1 align="center">${ errorMsg }</h1>
	<!-- 작은 스코프 영역부터 찾아가지고 알아서 들어감~ -->
</body>
</html>