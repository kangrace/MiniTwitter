// step 5: rewrite insert and search, add update and selectAll

package dataAccess;

import business.User;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDB 
{
    public static int insert(User user) throws IOException 
    {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		
		// mySQL insert statement
		String query 
				= "INSERT INTO User(fullName, email, password, dateOfBirth, question, answer) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		try
		{
			// create mySQL insert preparedStatement
			ps = connection.prepareStatement(query);
			ps.setString (1, user.getFullName());
			ps.setString (2, user.getEmail());
			ps.setString (3, user.getPassword());
			ps.setString (4, user.getDateOfBirth());
			ps.setString (5, user.getQuestion());
			ps.setString (6, user.getAnswer());
			return ps.executeUpdate();
		} 
		catch (SQLException e) 
		{
            System.out.println(e);
            return 0;
        } 
		finally 
		{
			pool.freeConnection(connection);
            // close preparedStatement
            DBUtil.closePreparedStatement(ps);
        }
      
    }
    public static User search(String email) throws IOException
    {
        ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM User WHERE email = ?";
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery(query);
			
			User user = new User();
			if(rs.next())
			{
				user.setFullName(rs.getString("fullName"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setDateOfBirth(rs.getString("dateOfBirth"));
				user.setQuestion(rs.getString("question"));
				user.setAnswer(rs.getString("answer"));
			}
			return user;
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		finally 
		{	
			// close ps and rs
			DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
			// free connection
            pool.freeConnection(connection);
        }
		
        return null;
    }
	
	// need to fix this update statement to update a user in the database after the
	// user has fixed all the fields from clicking the profile link
	public static int update(User user) 
	{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE User SET "
                + "fullName = ?, "
                + "dateOfBirth = ? "
				+ "question = ?"
				+ "answer = ?"
                + "WHERE email = ?";
        try 
		{
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getDateOfBirth());
			ps.setString(3, user.getQuestion());
			ps.setString(4, user.getAnswer());
            ps.setString(5, user.getEmail());

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
			// free connection
            pool.freeConnection(connection);
        }
    }
	
	public static ArrayList<User> selectAll()
	{
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM User";
		ArrayList<User> userList = new ArrayList<>();
		
		try
		{
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery(query);
			
			// while there are more users
			while(rs.next())
			{
				// make new user, set attributes and add it to the list
				User user = new User();
				user.setFullName(rs.getString("fullName"));
				user.setEmail(rs.getString("email"));
				user.setDateOfBirth(rs.getString("dateOfBirth"));
				user.setQuestion(rs.getString("question"));
				user.setAnswer(rs.getString("answer"));
				userList.add(user);
			}
			return userList;
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
			// free connection
            pool.freeConnection(connection);
        }
	}
	
	public static boolean validate(String loginEmail, String loginPW, User user)
	{
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String dbEmail, dbPassword;
		String query = "SELECT email, password FROM User";
			
		boolean validated = false;
		try
		{
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery(query);
			
			while(rs.next())
			{
				dbEmail = rs.getString("email");
				dbPassword = rs.getString("password");
				
				if(dbEmail.equals(loginEmail) && dbPassword.equals(loginPW))
				{
					// correct login information
					validated = true;
				}
				
			}
			
			return validated;
				
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
       
		finally
		{
			DBUtil.closeResultSet(rs);
			pool.freeConnection(connection);
            // close preparedStatement
            DBUtil.closePreparedStatement(ps);
		}
		return validated;
	}
}
