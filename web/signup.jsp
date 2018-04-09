<!DOCTYPE html>
<%@ include file="header.jsp" %>
<html>
    <head>
        <title>Mini Twitter Sign Up</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="styles/main.css" rel="stylesheet" type="text/css"/>
        <script src="javascript/main.js" type="text/javascript"></script>
    </head>   
    
<body>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    <form id="register1" name="register1" action="membership" method="post" onsubmit="return validateForm()">
        <div id="errorMessage" class="notVisible"></div>
        
       <fieldset>        
            <legend>
                  Sign up!
            </legend>
        <div class="messages">
			<p>${message}</p>
			<c:remove var="message" scope="session" />
		</div> 
        <input type="hidden" name="action" value="add">
        <label>Full Name:</label>
        <input type="text" id="fullName" name="fullName" required class="inputBox" value="<c:out value='${user.fullName}'/>">
        <span id="fullName_error" class="notVisible">*</span>
            
            
        <label>Email:</label>
        <input type="email" id="email" name="email" required class="inputBox" value="<c:out value='${user.email}'/>">
        <span id="email_error" class="notVisible">*</span>
           
        
		<label>Password:</label>
		<input type="password" id="password1" name="password1" required class="inputBox" value="${user.password}">
		<span id="password_error" class="notVisible">*</span>
     
			
        <label>Confirm Password:</label>
        <input type="password" id="confirmPassword1" name="confirmPassword1" required class="inputBox" value="${user.confirmPassword}">
        <span id="confirmPassword_error" class="notVisible">*</span>
           
            
        <label>Date of Birth:</label>
        <input type="date" id="dateOfBirth" name="dateOfBirth" required class="inputBox" value="${user.dateOfBirth}"> 
        <span id="confirmDateOfBirth" class="notVisible">*</span>
            
        <label>Security Question:</label>
        <select id="question" name="question" required onchange="showInputBox()" value="${user.question}">
            <option selected="selected">Select a question!</option>
            <option value="firstPet">What was your first pet?</option>
            <option value="firstCar">What was your first car?</option>
            <option value="firstSchool">What was your first school?</option>
        </select>
        <div id="securityAnswer"></div>
        
        <input type="submit" id="btnSubmit" value="Sign up">
        
        </fieldset>
    </form>
    </body>
    
</html>

<%@ include file="footer.jsp" %>