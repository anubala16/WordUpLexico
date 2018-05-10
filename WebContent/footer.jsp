<link rel="stylesheet" href="styles/main.css" type="text/css" />
<%@ page import="java.util.GregorianCalendar, java.util.Calendar" %>
<%  
    GregorianCalendar currentDate = new GregorianCalendar();
    int currentYear = currentDate.get(Calendar.YEAR);
%>
<p class="footer" >&copy; Copyright <%= currentYear %> Anusha Balaji, UNCC</p>

</body>
</html>