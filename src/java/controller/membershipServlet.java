package controller;

import business.Tweet;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.User;
import dataAccess.ConnectionPool;
import dataAccess.DBUtil;
import dataAccess.TweetDB;
import dataAccess.UserDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletContext;

public class membershipServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String message;
        String url = "/login.jsp";
        
        // get current action
        String action = request.getParameter("action");
        if(action == null)
        {
            action = "add";   // default action
        }
        
		// from login.jsp: sign up, add, and login
        if(action.equals("signUp")) 
        {
            url = "/signup.jsp";
        }
        
        else if(action.equals("add"))
        {
            url = addUser(request, response);
        }
        
        else if(action.equals("login"))
        {
            url = checkUser(request, response); 
        }
		
		// from header.jsp: homeButton, notificationButton, profileButton
		else if(action.equals("homeButton"))
		{
			HttpSession session = request.getSession();
			
			// if user is not logged in, they are redirected to login page
			if(session.getAttribute("loggedIn") == null)
			{
				url = "/login.jsp";
				message = "You need to login first.";
				request.setAttribute("message", message);
			}
			else
			{
				url = "/home.jsp";
			}
		}
		
		else if(action.equals("notificationButton"))
		{
			// not linked to anything yet
			url = "/notification.jsp";
		}
		
		else if(action.equals("profileButton"))
		{
			// updating information
			url = "/update.jsp";
		}
		
		else if(action.equals("update"))
		{
			url = updateUser(request, response);
		}
		
//		else if(action.equals("tweet"))
//		{
//			url = postTweet(request, response);
//		}
        
        getServletContext().getRequestDispatcher(url)
                .forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException 
    {
        doPost(request, response);
    } 
    
    private static String addUser(HttpServletRequest request,
            HttpServletResponse response) throws IOException
    {
        String message;
        String url;
        
        // get user data
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password1");
        String confirmPassword = request.getParameter("confirmPassword1");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String answer = request.getParameter("answer");
        String question = request.getParameter("question");
            
        // validate the parameters so it's not empty (server side)
        if(fullName == null || email == null || password == null || confirmPassword == null|| 
                dateOfBirth == null || question == null || answer == null ||
                fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || 
                dateOfBirth.isEmpty() || question.isEmpty() || answer.isEmpty())
        {
            message = "Please fill out all the fields.";
			request.setAttribute("message", message);
            url = "/signup.jsp";
            return url;
        }
        
        // store data in User object
        User user = new User(fullName, email, password, confirmPassword, dateOfBirth, answer, question);
        UserDB.insert(user);
        
        // store the User object as a session attribute
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
		session.setAttribute("email", email);
        
        // set message
        message="";
        request.setAttribute("message", message);
            
        url = "/home.jsp";  
        return url;
    }
    
    private static String checkUser(HttpServletRequest request,
           HttpServletResponse response) throws IOException 
    {
        String message;     // for error message
        String url = null;
		
        // get user's login info
        String lemail = request.getParameter("loginEmail");
        String lpassword = request.getParameter("loginPassword");
            
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
		
		// validate the user's login
		if(UserDB.validate(lemail, lpassword, user) == true)
		{
			// correct login information
			// only set a attribute if they are logged in
			session.setAttribute("loggedIn", true);
			session.setAttribute("user", user);
			session.setAttribute("email", lemail);
			url = "/home.jsp";
		}
		else
		{
			// incorrect login information
			// set message
			message = "No user found. Email/Password is incorrect.";
			session.setAttribute("message", message);
			// redirect to signup page
			url = "/signup.jsp";
		}
			
		return  url;
    }
	
	private static String updateUser(HttpServletRequest request,
           HttpServletResponse response) throws IOException 
    {
		String url = null;
		String email = request.getParameter("updateEmail");
		String fullName = request.getParameter("updateFullName");
		String dateOfBirth = request.getParameter("updateDateOfBirth");
		String question = request.getParameter("updateQuestion");
		// i'm not sure about answer
		String answer = request.getParameter("answer");
		
		User user = UserDB.search(email);
		if(user != null)
		{
			user.setFullName(fullName);
			user.setEmail(email);
			user.setDateOfBirth(dateOfBirth);
			user.setQuestion(question);
			user.setAnswer(answer);
			UserDB.update(user);
			
			// set updated user as session attribute
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("email", email);

			// set message
			//String message = "Updated";
			//request.setAttribute("message", message);
			
			// forward back to home after update
			url = "/home.jsp";
			return url;
		}
		// else just return null?
	return url;
	}
	
    private static String postTweet(HttpServletRequest request,
           HttpServletResponse response)
	{
		HttpSession session = request.getSession(true);
		String tweetText = request.getParameter("tweetText");
		// don't need to set date, it sets itself
		// get email
		String email = request.getSession(false).getAttribute("email").toString();
		
		Tweet tweet = new Tweet();
		tweet.setTweetText(tweetText);
		tweet.setTweetEmail(email);
		
		TweetDB.insert(tweet);
		session.setAttribute("tweet", tweet);
		return "/home.jsp";
	}
}

