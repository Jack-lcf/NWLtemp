<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="content">
	<c:choose>
		<c:when test="${empty error}">
			<div id="content_text">All words in your dictionary:</div>
		</c:when>
		<c:otherwise>
			<h3 class="error" align="center">${error}</h3>
		</c:otherwise>
	</c:choose>
	<section>
		<form>
			<table>
				<tr>
				    <th class="del_th"><img alt="del" src="images/full.png"></th>
					<th>ID</th>
					<th>English</th>
					<th>Russian</th>
					<th>Correct</th>
					<th>Total</th>
				</tr>
				<c:forEach var="word" items="${words}">
					<tr>
					    <td class="del_td"><img alt="del" src="images/delete_small.png"></td>
						<td class="id">${word.id}</td>
						<td class="words">${word.eng}</td>
						<td class="words">${word.rus}</td>
						<td class="corr">${word.correct}</td>
						<td class="corr">${word.total}</td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</section>
</div>