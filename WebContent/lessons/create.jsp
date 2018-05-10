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
	<h1>Create/Update a Lesson</h1>
</header>
<section>
	<table>
		<c:forEach var="error" items="${errors}">
			<tr>
				<td class="errorMsg">${error}</td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${lesson != null}">
		<p class="successMsg">${success}</p>
	</c:if>
	<c:if test="${lesson == null}">
		<p class="impMsg">Note: Please ensure each line in the material
			file only has one word and definition/description. Each word and its
			definition must be separated by a colon ':' character.</p>
		<p class="impMsg">Warning! Please ensure tile and file path is
			unique for each lesson or an older lesson will be overriden.</p>
		<div class="container">
			<form action="lesson" method="post">
				<input type="hidden" name="action" value="create"> <label>Lesson
					Name: </label> <input type="text" name="title" id="title" value="" required>
				<br> <br> <label>Link to File: </label> <input type="text"
					name="file" id="file" value="" required>
				<!-- 
			<input type="file" name="file" id="file" accept="txt" required>
			 -->
				<br> <br> <label>Subject: </label> <select class="input"
					name="subject" id="subject" required>
					<option value="" selected>Please Choose One</option>
					<option value="Arts">Arts</option>
					<option value="Biology">Biology</option>
					<option value="Chemistry">Chemistry</option>
					<option value="Computer Science">Computer Science</option>
					<option value="Engineering">Engineering</option>
					<option value="History">History</option>
					<option value="Linguistics">Linguistics</option>
					<option value="Literature">Literature</option>
					<option value="Math">Math</option>
					<option value="Physics">Physics</option>
					<option value="Psychology">Psychology</option>
					<option value="Sociology">Sociology</option>
					<option value="other">Other</option>
				</select> <br> <br> <label>Subject 2: </label> <select
					class="input" name="subject2" id="subject2">
					<option value="" selected>Please Choose One</option>
					<option value="Arts">Arts</option>
					<option value="Biology">Biology</option>
					<option value="Chemistry">Chemistry</option>
					<option value="Computer Science">Computer Science</option>
					<option value="Engineering">Engineering</option>
					<option value="History">History</option>
					<option value="Linguistics">Linguistics</option>
					<option value="Literature">Literature</option>
					<option value="Math">Math</option>
					<option value="Physics">Physics</option>
					<option value="Psychology">Psychology</option>
					<option value="Sociology">Sociology</option>
					<option value="other">Other</option>
				</select> <br> <br> <label>Subject 3: </label> <select
					class="input" name="subject3" id="subject3">
					<option value="" selected>Please Choose One</option>
					<option value="Arts">Arts</option>
					<option value="Biology">Biology</option>
					<option value="Chemistry">Chemistry</option>
					<option value="Computer Science">Computer Science</option>
					<option value="Engineering">Engineering</option>
					<option value="History">History</option>
					<option value="Linguistics">Linguistics</option>
					<option value="Literature">Literature</option>
					<option value="Math">Math</option>
					<option value="Physics">Physics</option>
					<option value="Psychology">Psychology</option>
					<option value="Sociology">Sociology</option>
					<option value="other">Other</option>
				</select> <br> <br> <label>Whom to Share this Lesson with?</label>
				<select class="input" name="access" id="access" required>
					<option value="private">Go Private</option>
					<option value="public" selected>Go Public!</option>
				</select> <br> <br> <input type="submit" id="done"
					value="Create My Lesson!">
			</form>
		</div>
		<a class="userMenu" href="welcome.jsp"><- Back to my Dashboard</a>
	</c:if>
	<c:if test="${lesson != null }">
		<label>Lesson Name: </label>
		<input type="text" name="title" id="title" value="${lesson.title}" disabled>
		<br>
		<br>
		<label>Link to File: </label>
		<input type="text" name="file" id="file" value="${lesson.filePath}" disabled>
		<br>
		<br>
		<label>Subject: </label>
		<select class="input" name="subject" id="subject" disabled>
			<option value="" selected>Please Choose One</option>
			<option value="Arts" <% if(request.getParameter("subject").equals("Arts")) { out.println("selected"); }  %>>Arts</option>
			<option value="Biology" <% if(request.getParameter("subject").equals("Biology")) { out.println("selected"); }  %>>Biology</option>
			<option value="Chemistry" <% if(request.getParameter("subject").equals("Chemistry")) { out.println("selected"); }  %>>Chemistry</option>
			<option value="Computer Science" <% if(request.getParameter("subject").equals("Computer Science")) { out.println("selected"); }  %>>Computer Science</option>
			<option value="Engineering" <% if(request.getParameter("subject").equals("Engineering")) { out.println("selected"); }  %>>Engineering</option>
			<option value="History" <% if(request.getParameter("subject").equals("History")) { out.println("selected"); }  %>>History</option>
			<option value="Linguistics" <% if(request.getParameter("subject").equals("Linguistics")) { out.println("selected"); }  %>>Linguistics</option>
			<option value="Literature" <% if(request.getParameter("subject").equals("Literature")) { out.println("selected"); }  %>>Literature</option>
			<option value="Math" <% if(request.getParameter("subject").equals("Math")) { out.println("selected"); }  %>>Math</option>
			<option value="Physics" <% if(request.getParameter("subject").equals("Physics")) { out.println("selected"); }  %>>Physics</option>
			<option value="Psychology" <% if(request.getParameter("subject").equals("Psychology")) { out.println("selected"); }  %>>Psychology</option>
			<option value="Sociology" <% if(request.getParameter("subject").equals("Sociology")) { out.println("selected"); }  %>>Sociology</option>
			<option value="other" <% if(request.getParameter("subject").equals("other")) { out.println("selected"); }  %>>Other</option>
		</select>
		<br>
		<br>
		<label>Subject 2: </label>
		<select class="input" name="subject2" id="subject2" value="${lesson.subject2}" disabled>
			<option value="" selected>Please Choose One</option>
			<option value="Arts" <% if(request.getParameter("subject2").equals("Arts")) { out.println("selected"); }  %>>Arts</option>
			<option value="Biology" <% if(request.getParameter("subject2").equals("Biology")) { out.println("selected"); }  %>>Biology</option>
			<option value="Chemistry" <% if(request.getParameter("subject2").equals("Chemistry")) { out.println("selected"); }  %>>Chemistry</option>
			<option value="Computer Science" <% if(request.getParameter("subject2").equals("Computer Science")) { out.println("selected"); }  %>>Computer Science</option>
			<option value="Engineering" <% if(request.getParameter("subject2").equals("Engineering")) { out.println("selected"); }  %>>Engineering</option>
			<option value="History" <% if(request.getParameter("subject2").equals("History")) { out.println("selected"); }  %>>History</option>
			<option value="Linguistics" <% if(request.getParameter("subject2").equals("Linguistics")) { out.println("selected"); }  %>>Linguistics</option>
			<option value="Literature" <% if(request.getParameter("subject2").equals("Literature")) { out.println("selected"); }  %>>Literature</option>
			<option value="Math" <% if(request.getParameter("subject2").equals("Math")) { out.println("selected"); }  %>>Math</option>
			<option value="Physics" <% if(request.getParameter("subject2").equals("Physics")) { out.println("selected"); }  %>>Physics</option>
			<option value="Psychology" <% if(request.getParameter("subject2").equals("Psychology")) { out.println("selected"); }  %>>Psychology</option>
			<option value="Sociology" <% if(request.getParameter("subject2").equals("Sociology")) { out.println("selected"); }  %>>Sociology</option>
			<option value="other" <% if(request.getParameter("subject2").equals("other")) { out.println("selected"); }  %>>Other</option>
		</select>
		<br>
		<br>
		<label>Subject 3: </label>
		<select class="input" name="subject3" id="subject3" value="${lesson.subject3}" disabled>
			<option value="" selected>Please Choose One</option>
			<option value="Arts" <% if(request.getParameter("subject3").equals("Arts")) { out.println("selected"); }  %>>Arts</option>
			<option value="Biology" <% if(request.getParameter("subject3").equals("Biology")) { out.println("selected"); }  %>>Biology</option>
			<option value="Chemistry" <% if(request.getParameter("subject3").equals("Chemistry")) { out.println("selected"); }  %>>Chemistry</option>
			<option value="Computer Science" <% if(request.getParameter("subject3").equals("Computer Science")) { out.println("selected"); }  %>>Computer Science</option>
			<option value="Engineering" <% if(request.getParameter("subject3").equals("Engineering")) { out.println("selected"); }  %>>Engineering</option>
			<option value="History" <% if(request.getParameter("subject3").equals("History")) { out.println("selected"); }  %>>History</option>
			<option value="Linguistics" <% if(request.getParameter("subject3").equals("Linguistics")) { out.println("selected"); }  %>>Linguistics</option>
			<option value="Literature" <% if(request.getParameter("subject3").equals("Literature")) { out.println("selected"); }  %>>Literature</option>
			<option value="Math" <% if(request.getParameter("subject3").equals("Math")) { out.println("selected"); }  %>>Math</option>
			<option value="Physics" <% if(request.getParameter("subject3").equals("Physics")) { out.println("selected"); }  %>>Physics</option>
			<option value="Psychology" <% if(request.getParameter("subject3").equals("Psychology")) { out.println("selected"); }  %>>Psychology</option>
			<option value="Sociology" <% if(request.getParameter("subject3").equals("Sociology")) { out.println("selected"); }  %>>Sociology</option>
			<option value="other" <% if(request.getParameter("subject3").equals("other")) { out.println("selected"); }  %>>Other</option>
		</select>
		<br>
		<br>
		<label>Whom to Share this Lesson with?</label>
		<select class="input" name="access" id="access" value="${lesson.accessLevel}" disabled>
			<option value="private" <% if(request.getParameter("access").equals("private")) { out.println("selected"); }  %>>Go Private</option>
			<option value="public" <% if(request.getParameter("access").equals("public")) { out.println("selected"); }  %>>Go Public!</option>
		</select>
		<br>
		<br>
		<a class="userMenu" href="welcome.jsp"><- Back to my Dashboard</a>
	</c:if>
</section>

<c:import url="../footer.jsp" />