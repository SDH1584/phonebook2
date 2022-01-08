<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.javaex.vo.PersonVo" %>
<%
//request의 attribute영역 가져오기 dao에서 가져오는거아님 절대
	List<PersonVo> personList=(List<PersonVo>)request.getAttribute("pList");
%>






<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>[phonebook2]</h1>
	<h2>전화번호리스트</h2>
	<p>입력한 정보 내역입니다.</p>
	
	<%
	for(int i=0;i<personList.size();i++){
	%>
	<table border="1">
		<tr>
			<td>이름(name)</td>
			<td><%=personList.get(i).getName() %></td>
		</tr>
		<tr>
			<td>핸드폰</td>
			<td><%=personList.get(i).getHp() %></td>
		</tr>
		<tr>
			<td>회사(company))</td>
			<td><%=personList.get(i).getCompany() %></td>
		</tr>
		<tr>
			<td><a href="pbc?action=updateForm&personId<%=personList.get(i).getPersonId()%>">[수정]</a></td>
			<td><a href="pbc?action=delete&personId=<%=personList.get(i).getPersonId()%>">[삭제]</a></td>
		</tr>	
	</table>
	<br>
	<%
	}
	%>
	<br>
	<a href="./pbc?action=writeForm">번호 등록</a>
	
	
	
	
	
</body>
</html>