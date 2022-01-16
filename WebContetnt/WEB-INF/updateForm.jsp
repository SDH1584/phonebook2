<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.javaex.vo.PersonVo" %>    
<%
	PersonVo vo = (PersonVo)request.getAttribute("pVo");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>[PhoneBook2]</h1>
	
	<p>
		수정 화면입니다. 아래 항목을 수정하고 "수정"버튼을 클릭하세요
	</p>
	
	<form action="/phonebook2/pbc" method="get">
		이름(name): <input type="text" name="name" value="<%=vo.getName()%>" > <br>
		핸드폰(hp): <input type="text" name="hp" value="<%=vo.getHp()%>" > <br>
		회사(company): <input type="text" name="company" value="<%=vo.getCompany()%>" > <br>
		코드(id): <input type="text" name="id" value="<%=vo.getPersonId()%>" readonly > <br>
		<input type="text" name="action" value="update">
		<button type = submit> 수정 </button> <br><br>
	</form>
</body>
</html>