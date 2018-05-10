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
	<h1>Welcome to Word-Up Lexico!</h1>
</header>
<section>
	<p class="useful">
		Questions? Or Comments?<br> Please contact us! <br> Email:
		customerSupport@wordup.com <br> Phone: 1-800-111-1111 <br>
		Address: 121 Lexico Lane, WordGirl Place, NC 34567
	</p>
</section>
<c:import url="footer.jsp" />
