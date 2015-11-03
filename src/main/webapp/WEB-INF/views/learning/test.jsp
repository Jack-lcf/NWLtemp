<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="content">
	<c:choose>
		<c:when test="${empty error}">
			<div id="content_text">${test_name}</div>
		</c:when>
		<c:otherwise>
			<h3 class="error" align="center">${error}</h3>
		</c:otherwise>
	</c:choose>
	<c:choose>
        <c:when test="${empty result}">
            <div id="conclusion_text">${result_text}</div>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${result == true}">
                    <div id="conclusion_text">${result_text}</div>
                </c:when>
                <c:otherwise>
                    <div id="#conclusion_worng_text">${result_text}</div>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
	
	<div id="content_section">
		<section>
			<form action="action" method="POST" accept-charset="windows-1251">
				<p>
					<label>${target_label}</label>
					<span>${target_word}</span>
				</p>
				<p>
					<label>${reply_label}</label>
					<input type="text" name="rus_reply" autofocus="autofocus" required="required" id="rusEdit" value="${reply_word}" />
				</p>
				<div id="check">
					<p>
					   <button type="submit">${button_text}</button>
					</p>
				</div>
				<p>
				    <c:choose>
                        <c:when test="${empty result}">
                            <input type="hidden" name="command" id="command" value=/check_word />
                        </c:when>
                        <c:otherwise>
                            <input type="hidden" name="command" id="command" value=${test_action} />
                        </c:otherwise>
                    </c:choose>
					
				</p>
			</form>
		</section>
	</div>
</div>