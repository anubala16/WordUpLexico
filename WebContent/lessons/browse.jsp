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
	<h1>Lesson Catalog</h1>
</header>
<section>
	<table>
		<c:forEach var="error" items="${errors}">
			<tr>
				<td class="errorMsg">${error}</td>
			</tr>
		</c:forEach>
	</table>
	<table>
		<thead>
			<th>Lesson ID</th>
			<th>Lesson Title</th>
			<th>Author</th>
			<th>Subject</th>
			<th>Access Level</th>
			<th>Date Created</th>
			<th>Study/Quiz?</th>
		</thead>
		<c:forEach var="lessonAuthor" items="${lessonAuthors}">
			<tr>
				<td>${lessonAuthor.lesson.lessonID}<input type="hidden" id="lessonID" name="lessonID" value="${lesson.lessonID}"></td>
				<td class="">${lessonAuthor.lesson.title}</td>
				<td>${lessonAuthor.author}</td>
				<td>${lessonAuthor.lesson.subject}</td>
				<td>${lessonAuthor.lesson.accessLevel}</td>
				<td>${lessonAuthor.lesson.dateCreated}</td>
				<td><input type="submit" id="done" name="study" value="Study">
				<input type="submit" id="done" name="quiz" value="Quiz Me!"></td>
			</tr>
		</c:forEach>
	</table>
	<br> <br> <a class="userMenu" href="welcome.jsp"><- Back
		to my Dashboard</a>
</section>

<c:import url="../footer.jsp" />