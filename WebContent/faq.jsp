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
	<h1>Frequently Asked Questions (& Answers)</h1>
</header>
<section>
	<ol>
		<li class="question">Why Word-Up Lexico?</li>
		<p class="answer">Word-Up Lexico is designed to help students
			prepare for their upcoming vocabulary tests. Registered users can
			create "lessons" based on uploaded study material, study the lesson,
			and get quizzed on it!</p>
		<li class="question">How to sign up for Word-Up Lexico?</li>
		<p class="answer">
			Please click the <a href="register.jsp">Sign-Up</a> link above to
			start using Work-Up instantly!
		</p>
		<li class="question">Can I share my lessons with my friends?</li>
		<p class="answer">That's a great feature! Unofrtunately, we don't
			have that feature yet, but we will try to accommodate that in future
			iterations soon!</p>
	</ol>
</section>
<c:import url="footer.jsp" />
