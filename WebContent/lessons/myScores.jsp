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
	<h1>My Scores</h1>
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
	<form action="viewMyScores" method="post">
		<input type="hidden" name="action" value="scores">
		<table>
			<thead>
				<th>Lesson ID</th>
				<th>Lesson Title</th>
				<th>Attempt Count</th>
				<th>Score</th>
				<th>Date Taken</th>
				<th>View/Email My Scores</th>
			</thead>
			<c:forEach var="lessonAttempt" items="${lessonAttempts}">
				<tr>
					<td>${lessonAttempt.lesson.lessonID}</td>
					<td>${lessonAttempt.lesson.title}</td>
					<td>${lessonAttempt.attempt.count}</td>
					<td><strong>${lessonAttempt.attempt.score}</strong> out of <strong>${lessonAttempt.qCount}</strong> points<br>=> <strong>${lessonAttempt.percent}%</strong></td>
					<td>${lessonAttempt.attempt.timestamp}</td>
					<td>
						<button type="submit" id="email" name="email"
							value="${lessonAttempt.attempt.attemptID}">Email My
							Score Report</button>
					</td>
				</tr>
			</c:forEach>
		</table>
	</form>
	<br> <br> <a class="userMenu" href="welcome.jsp"><- Back
		to Dashboard</a>
</section>

<c:import url="../footer.jsp" />