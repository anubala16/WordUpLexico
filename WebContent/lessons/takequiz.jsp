<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${user == null}">
		<c:import url="header.jsp" />
	</c:when>
	<c:otherwise>
		<c:import url="header2.jsp" />
	</c:otherwise>
</c:choose>
<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>

<header>
	<h1>Lesson Quiz</h1>
</header>
<section>
	<c:if test="${errors != null }">
		<table>
			<c:forEach var="error" items="${errors}">
				<tr>
					<td class="errorMsg"><c:out value='${error}'/></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<div class="title"><c:out value='${lessonAuthor.lesson.title}'/></div>
	<div class="impMsg"><c:out value='By ${lessonAuthor.author}, on
		${lessonAuthor.lesson.dateCreated}'/></div>
	<div class="impMsg"><c:out value='Subject: ${lessonAuthor.lesson.subject}'/></div>
	<br>
	<form action="gradeattempt" method="post">
		<input type="hidden" name="action" value="quiz"> 
		<c:forEach var="card" items="${cards}" begin="0" end="${cardCount}"
		step="1" varStatus="status">
		<div class="question"><c:out value='${status.count}. ${card.description}'/></div>
		<label>Response: </label> <input type="text" name="<c:out value='${status.count}'/>" id="resp" value=""
			required><br> 
		<br> 
		</c:forEach>
		<input type="hidden"
					id="lessonID" name="lessonID" value="<c:out value='${lessonAuthor.lesson.lessonID}'/>">	
		<input type="submit" id="done" name="submitQuiz" value="Submit Attempt">
	</form>

	<br> <br> <a class="userMenu" href="welcome.jsp">Cancel
		Quiz Attempt</a>
</section>
<c:import url="../footer.jsp" />