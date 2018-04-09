// Step 8: tweet controller

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dataAccess.TweetDB;
import business.Tweet;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

public class tweetServlet extends HttpServlet 
{
	
	// list of tweets have been updated, list of tweets in sessoin updated
	// list of users updated, list of users in session updated
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String message;
        String url = "/home.jsp";
        
        // get current action
        String action = request.getParameter("action");
        if(action == null)
        {
            action = "insert";   // default action
        }
        
		// what does the action equal
		else if(action.equals("signUp")) 
        {
            url = "/signup.jsp";
        }
		else if(action.equals("postTweet"))
		{
			url = postTweet(request, response);
		}
		
		
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
	
	public static String postTweet(HttpServletRequest request, HttpServletResponse response)
	{
		// list all the tweets?
		manageTweet(request, response);
		String tweetEmail = request.getParameter("tweetEmail");
		// date is the time that this is processed?
		java.util.Date date = new java.util.Date();
		Date tweetDate = new java.sql.Date(date.getTime());
		
		String tweetText = request.getParameter("tweetText");
		
		// when a user posts a tweet, insert into database using tweetDB.insert
		// then redirect to home.jsp
		Tweet tweet = new Tweet(tweetEmail, tweetDate, tweetText);
		TweetDB.insert(tweet);
		
		HttpSession session = request.getSession();
		session.setAttribute("tweet", tweet);
		
		// update the list of tweets?
		manageTweet(request, response);
		String url = "/home.jsp";
		return url;
	}
	
	private static String manageTweet(HttpServletRequest request,
            HttpServletResponse response)
	{
		ArrayList<Tweet> tweetList = TweetDB.selectAll();
		
		// set attribute
		request.setAttribute("tweetList", tweetList);
		
        
        return "/home.jsp";
	}

}
