<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="content">
	<c:choose>
		<c:when test="${empty error}">
			<%-- <c:choose>
				<c:when test="${test.equals('1')}">
					<div id="content_text">English -> Russian</div>
				</c:when>
				<c:otherwise>
					<c:if test="${test.equals('2')}">
						<div id="content_text">Russian -> English</div>
					</c:if>
				</c:otherwise>
			</c:choose> --%>
			<div id="content_text">Learning word</div>
		</c:when>
		<c:otherwise>
			<h3 class="error" align="center">${error}</h3>
		</c:otherwise>
	</c:choose>
	<section></section>
</div>