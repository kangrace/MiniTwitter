<%-- 
    step 5.2: include in all pages and print current date with some information
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link href="styles/footer.css" rel="stylesheet" type="text/css"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div class="footer">
            <%-- Another way to express: <jsp:useBean id="date" class="java.util.Date" />
            Today's date: <fmt:formatDate value="${date}" pattern="MM/dd/yyyy" />
           <%= new java.util.Date() %> --%>
			
			
			<%-- no jsp code! --%>
			<script>document.write(new Date());</script>
			
        </div>
    </body>
</html>
