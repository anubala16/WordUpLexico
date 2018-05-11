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
	<c:if test="${errors != null }">
		<table>
			<c:forEach var="error" items="${errors}">
				<tr>
					<td class="errorMsg">${error}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<table>
		<thead>
			<th>User ID</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>Profession</th>
		</thead>
		<c:forEach var="user" items="${users}">
			<tr>
				<td>${user.userID}</td>
				<td>${user.firstName}</td>
				<td>${user.lastName}</td>
				<td>${user.email}</td>
				<td>${user.profession}</td>
			</tr>
		</c:forEach>
	</table>
	<br> <br> <a class="userMenu" href="welcome.jsp"><- Back
		to My Dashboard</a>
</section>

<c:import url="../footer.jsp" />