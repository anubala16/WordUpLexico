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
	<h1>Ready for your goals to take flight?</h1>
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
	<form action="register" method="post">
		<input type="hidden" name="action" value="register"> <label>First
			Name: </label> <input type="text" name="firstName" id="fName" value=""
			required><br> <label>Last Name: </label> <input
			type="text" name="lastName" id="lName" value="" required><br>
		<label>Email: </label> <input type="email" name="email" id="email"
			value="" required><br> <label>Password: </label> <input
			type="password" name="password" value="" id="pwd" required><br>
		<label>Repeat Password: </label> <input type="password"
			name="password2" id="pwd2" value="" required><br> <label>Profession:
		</label> <select class="input" name="profession" id="prof" required>
			<option value="student" selected>Student</option>
			<option value="teacher">Teaching Professional</option>
			<option value="academia">Other (Academia)</option>
			<option value="other">Other</option>
		</select> <br> <br> <input type="submit" id="done" name="register" value="Register!">
	</form>
</section>

<c:import url="footer.jsp" />
