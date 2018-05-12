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
					<td class="errorMsg"><c:out value='${error}'/></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<p class="successMsg"><C:out value='${success}'/></p>
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
					<td><c:out value='${lessonAttempt.lesson.lessonID}'/></td>
					<td><c:out value='${lessonAttempt.lesson.title}'/></td>
					<td><c:out value='${lessonAttempt.attempt.count}'/></td>
					<td><strong><c:out value='${lessonAttempt.attempt.score}'/></strong> out of <strong><c:out value='${lessonAttempt.qCount}'/></strong> points<br><strong>=> ${lessonAttempt.percent}%</strong></td>
					<td><c:out value='${lessonAttempt.attempt.timestamp}'/></td>
					<td>
						<button type="submit" id="email" name="review"
							value="<c:out value='${lessonAttempt.attempt.attemptID}'/>">Review Attempt</button>
						<button type="submit" id="email" name="email"
							value="<c:out value='${lessonAttempt.attempt.attemptID}'/>">Email My
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