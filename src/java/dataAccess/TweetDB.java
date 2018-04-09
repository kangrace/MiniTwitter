// step 6: implement insert(insert a new tweet) and search(load all tweets and returns an array of tweets)
package dataAccess;

import business.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TweetDB
{
    public static int insert(Tweet tweet) 
	{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO Tweet (email, postDate, tweetText)"
                + "VALUES (?, ?, ?)";
        try 
		{
            ps = connection.prepareStatement(query);
            ps.setString(1, tweet.getTweetEmail());
            ps.setDate(2, getCurrentDate());
            ps.setString(3, tweet.getTweetText());
            return ps.executeUpdate();
        } 
		
		catch (SQLException e) 
		{
            System.out.println(e);
            return 0;
        } 
		
		finally 
		{
			DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
	
	private static java.sql.Date getCurrentDate()
	{
		java.util.Date date = new java.util.Date();
		return new java.sql.Date(date.getTime());
	}
	
	public static ArrayList<Tweet> search()
	{
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Tweet> tweetList = new ArrayList<Tweet>();
		
        String query = "SELECT * from Tweet";
		
		try 
		{
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
			
			// put tweets into array
			while(rs.next())
			{
				Tweet tweet;
				tweet = new Tweet(rs.getString("email"), rs.getDate("postDate"), rs.getString("tweetText"));
				tweetList.add(tweet);
			}
			return tweetList;
        } 
		catch (SQLException e) 
		{
            System.out.println(e);
			return null;
        } 
		
		finally 
		{
			DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
	}
	
	public static ArrayList<Tweet> selectAll()
	{
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Tweet ";
        try 
		{
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<Tweet> tweetList = new ArrayList<Tweet>();
            
            Tweet tweet = null;
            
            while (rs.next()) 
			{
                tweet = new Tweet();
                tweet.setTweetEmail(rs.getString("tweetEmail"));
                tweet.setTweetText(rs.getString("tweetText"));
				tweet.setTweetDate(rs.getDate("postDate"));
                tweetList.add(tweet);
            }
            return tweetList;
        } 
		catch (SQLException e) 
		{
            System.out.println(e);
            return null;
        } 
		finally 
		{
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
	}
	
}
