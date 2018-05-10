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
	<h1>My Word-Up Account Sign-in</h1>
</header>
<section>
	<table>
		<c:forEach var="error" items="${errors}">
			<tr>
				<td class="errorMsg">${error}</td>
			</tr>
		</c:forEach>
	</table>
	<p class="successMsg">${success}</p>
	<form action="login" method="post">
		<input type="hidden" name="action" value="login"> <label>Email:
		</label> <input type="email" name="email" required><br> <label>Password:
		</label> <input type="password" name="password" required> <br> <input
			type="submit" value="Log-in!">
	</form>
</section>

<c:import url="footer.jsp" />
