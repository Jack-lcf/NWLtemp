<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="content">
	<section>
		<c:forEach var="error" items="${error}">
			<div class="error">${error}</div>
		</c:forEach>
	</section>
</div>