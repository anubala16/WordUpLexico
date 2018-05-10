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
			<th>File</th>
			<th>Subject</th>
			<th>Access Level</th>
			<th>Date Created</th>
		</thead>
		<c:forEach var="lesson" items="${lessons}">
			<tr>
				<td>${lesson.lessonID}</td>
				<td class="">${lesson.title}</td>
				<td>${lesson.filePath}</td>
				<td>${lesson.subject}</td>
				<td>${lesson.accessLevel}</td>
				<td>${lesson.dateCreated}</td>
			</tr>
		</c:forEach>
	</table>
	<br> <br> <a class="userMenu" href="welcome.jsp"><- Back
		to my Dashboard</a>
</section>

<c:import url="../footer.jsp" />