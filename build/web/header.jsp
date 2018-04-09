<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="javascript/main.js" type="text/javascript"></script>
		<link href="styles/header.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <%-- signout???????  --%>
	<div class="header">
		
		<div class="button">
			<form action="membership" method="get">
				<input type="hidden" name="action" value="homeButton">
				<button id="homeButton" type="submit">Home</button>
			</form>
		</div>
		
		<div class="button">
			<form action="membership" method="get">
				<input type="hidden" name="action" value="notificationButton">
				<button id="notificationButton" type="submit">Notification</button>
			</form>
		</div>
		
		<div class="button">
			<form action="membership" method="get">
				<input type="hidden" name="action" value="profileButton">
				<button id="profileButton" type="submit">Profile</button>
			</form>
		</div>
		
	</div>	
    </body>
</html>
