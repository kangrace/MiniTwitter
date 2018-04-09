<!DOCTYPE html>
<%@ include file="header.jsp" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="styles/login.css" rel="stylesheet" type="text/css"/>
        <title>Login</title>
    </head>
	
    <div class="messages">
		<p>${message}</p>
		<c:remove var="message" scope="session" />
	</div>
			
    <body>
        <script type="text/javascript">
                response.redirect("/membership");
        </script>
        
        
        <div class="login">
        <div class="loginScreen">
        <div class="loginTitle">
                    
            <div><img img style="vertical-align:middle; width:58px;height:58px" 
                 src="http://www.alc.edu/wp-content/uploads/2016/10/13-twitter-logo-vector-png-free-cliparts-that-you-can-download-to-you-Km878c-clipart-300x300.png">
                 <span id="loginHeader">Login</span>
            </div>
	</div>

        <div class="loginForm">
            <form action="membership" id="login" name="login" method="post">
                <!-- email -->
                <input type="text" name="loginEmail" id="loginEmail" class="loginField" required placeholder="Email">

                <!-- password -->
                <input type="password" id="loginPassword" name="loginPassword" class="loginField" required placeholder="Password">
        </div> 
                <!-- remember me-->
            <input type="checkbox" name="rememberMe" id="rememberMe" class="rememberCheckBox">Remember me
          
                <input type="hidden" name="action" value="login">
                <button type="submit" class="loginButton" >Log in</button>
            </form>
               
            <!-- sign up -->
            <form action="membership" id="signup" method="get">
                <input type="hidden" name="action" value="signUp">
              Not registered?
                <button type="submit" class="signUpButton">Sign up</button>
            </form>
            
            <a href="forgotPassword.jsp" class="forgotLink">Forgot password?</a>
         
        </div>
        </div>
    </body>
    
</html>

<%@ include file="footer.jsp" %>