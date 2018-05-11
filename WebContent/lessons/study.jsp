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
	<h1>Study Lesson</h1>
</header>
<section>
	<c:if test="${errors != null }">
		<table>
			<c:forEach var="error" items="${errors}">
				<tr>
					<td class="errorMsg">${error}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<div class="title">${lessonAuthor.lesson.title}</div>
	<div class="impMsg">By ${lessonAuthor.author}, on
		${lessonAuthor.lesson.dateCreated}</div>
	<div class="impMsg">Subject: ${lessonAuthor.lesson.subject}</div>
	<br>
	<c:forEach var="card" items="${cards}" begin="0" end="${ cardCount }"
		step="1" varStatus="status">

		<table>
			<tr>
				<td class="cardWord">${status.count}.${card.word}</td>
			</tr>
			<tr>
				<td class="cardDesc">${card.description}</td>
			</tr>
		</table>
		<br>
	</c:forEach>

	<br> <br>
	<c:if test="${user.type == 1 }">
		<a class="userMenu" href="welcome.jsp">Finish Study</a>
	</c:if>
	<c:if test="${user.type == 0 }">
		<a class="userMenu" href="welcome.jsp"><- Back to My Dashboard</a>
	</c:if>
</section>
<c:import url="../footer.jsp" />