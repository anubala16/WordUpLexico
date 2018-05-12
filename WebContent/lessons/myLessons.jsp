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
	<h1>My Lessons</h1>
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
	<table>
		<thead>
			<th>Lesson ID</th>
			<th>Lesson Title</th>
			<th>File</th>
			<th>Subject</th>
			<th>Access Level</th>
			<th>Date Created</th>
		</thead>
		<c:forEach var="lesson" items="${lessons}">
			<tr>
				<td><c:out value='${lesson.lessonID}'/></td>
				<td><c:out value='${lesson.title}'/></td>
				<td><c:out value='${lesson.filePath}'/></td>
				<td><c:out value='${lesson.subject}'/></td>
				<td><c:out value='${lesson.accessLevel}'/></td>
				<td><c:out value='${lesson.dateCreated}'/></td>
			</tr>
		</c:forEach>
	</table>
	<br> <br> <a class="userMenu" href="welcome.jsp"><- Back
		to my Dashboard</a>
</section>

<c:import url="../footer.jsp" />
