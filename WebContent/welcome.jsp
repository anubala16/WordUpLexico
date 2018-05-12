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
	<h1>Hello, Word-Up User!</h1>
</header>
<section>
	<form action="dashboard" method="post">
		<c:choose>
			<c:when test="${user.type == 1}">
				<!-- regular user -->
				<p>Welcome, <c:out value="${user.firstName}"/>! You are logged in as a
					teacher/student.</p>
				<br>
				<input type="hidden" name="action" value="not sure">
				<input type="submit" id="done" name="create"
					value="Create a Lesson!">
				<input type="submit" id="done" name="viewAllLessons"
					value="Lesson Catalog">
				<input type="submit" id="done" name="viewMyLessons"
					value="My Lessons">
				<input type="submit" id="done" name="viewMyScores"
					value="My Score Reports">
			</c:when>
			<c:when test="${user.type == 0}">
				<!-- type = 0; admin user -->
				<p>Hello, <c:out value="${user.firstName}"/>! You are logged in as an admin.</p>
				<br>
				<input type="hidden" name="action" value="admin">
				<input type="submit" id="done" name="adminLessons"
					value="View Lessons!">
				<input type="submit" id="done" name="adminUsers" value="View Users">
			</c:when>
			<c:otherwise>
				<p>Please login before proceeding further.</p>
			</c:otherwise>
		</c:choose>
	</form>
	<hr>
	<c:if test="${user != null}">
		<form action="logout" method="post">
			<input type="hidden" name="action" value="logout"> <input
				type="submit" id="done" value="Sign-out">
		</form>
	</c:if>
</section>
<c:import url="footer.jsp" />
