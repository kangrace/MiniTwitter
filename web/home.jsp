<!DOCTYPE html>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
	<%@page contentType="text/html" pageEncoding="UTF-8"%>
	<%@ page session="true" %>
    <link href="styles/home.css" rel="stylesheet" type="text/css"/>
    <meta charset="UTF-8">
    <div id="header">
		<%@ include file="header.jsp" %>
	</div>
    <title>Twitter Layout</title>
</head>

<body>
	
	
	<div id="container">
		<div id="header"></div>
		<div id="body">
			<main>
				<aside>
					<div id="profile">
						<div id="profileInfo">
              <img src="images/profile-placeholder.jpg" height="80" width="80">
							<c:out value="${user.fullName}"/>
							<c:out value="${user.emaili}"/>
							lijljoiioj
             
						</div>
							<div id="belowProfile">
							  TWEETS<br>#
							</div>
					</div>
				<div id="trends">
				  trends
				</div>
							
				</aside>
				<section>
					<form action="membership" method="post">
						<input type="hidden" name="action" value="postTweet">
						<div class="post">
						<div id="insidePost">
						  Post: <textarea name="tweetText" id="tweetText"> </textarea>
						<button type="submit" class="tweetButton">TWEET</button>
						</div>
						</div>
					</form>
					  
					<article>
						<input type="hidden" name="action" value="manage">
						<c:forEach var="tweet" items="${tweetList}">
							${tweet.email} 
							${tweet.tweetText}
							${tweet.postDate}
						</c:forEach>
					</article>

				</section>
				<div id="dashboard">
				  <div id="dashboard-body">Who To Follow</div>
				  <div id="dashboard-footer"></div>
				</div>

			</main>
			
		</div>
		
		<div id="footer">
			<%@ include file="footer.jsp" %></div>
		</div>
    <nav>
		<%@ include file="header.jsp" %>
</nav>
    
</body>

</html>
