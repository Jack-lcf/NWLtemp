<%@page import="constants.Attributes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="content">
	<div id="main_text">
		<%
		    HttpSession ses = request.getSession();
		    String f = (String) ses.getAttribute(Attributes.RESULT_KEY);
		    out.println(f);
		%>
	</div>

</div>