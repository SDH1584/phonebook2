<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.javaex.Dao.PhoneDao"%>
<%@ page import="com.javaex.vo.PersonVo"%>

<%
	PersonVo vo = (PersonVo)request.getAttribute("pvo");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>[Phonebook2]</h1>

	<h2>전화번호 수정폼</h2>

	<p>전화번호를 수정하려면 아래 항복을 기입하고 '수정'버튼을 클릭하세요.</p>

	<form action="/phonebook2/pbc" method="get">
			이름(name) : <input type="text" name="name" value="<%= vo.getName() %>"> <br>
			핸드폰(hp) : <input type="text" name="hp" value="<%= vo.getHp() %>"> <br>
			회사(company) : <input type="text" name="company" value="<%= vo.getCompany() %>"> <br>
			코드(id) : <input type="text" name="personid" value=<%= vo.getPersonId() %>> <br>
			<input type="text" name="action" value="update"> <br>
			<button type="submit">수정</button>
		</form>
</body>