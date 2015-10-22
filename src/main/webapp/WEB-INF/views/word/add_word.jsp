<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div id="content">
	<c:choose>
		<c:when test="${empty error}">
		    <c:choose>
		        <c:when test="${empty createdId}">
                    <div id="content_text">Add new word into dictionary:</div>
                </c:when>
                <c:otherwise>
                    <div id="content_text">The added word has gotten ID = ${createdId}</div>
                </c:otherwise>		
		    </c:choose>
		</c:when>
		<c:otherwise>
			<h3 class="error" align="center">${error}</h3>
		</c:otherwise>
	</c:choose>
	<div id="content_section">
		<section>
			<form action="action" method="POST" accept-charset="windows-1251">
				<p>
					<label for="english">English:</label>
					<input type="text" autofocus="autofocus" required="required" name="english" id="engEdit" value="" />
				</p>
				<p>
					<label for="russian">Russian:</label>
					<input type="text" name="russian" required="required" id="engEdit" value="" />
				</p>
				<p>
					<button type="submit">Add</button>
					<button type="reset">Clean</button>
				</p>
				<p>					
					<input type="hidden" name="command" id="command" value=/add />
				</p>
			</form>
		</section>
	</div>
</div>