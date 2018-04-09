// Step 4: tweet javabean 

package business;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Tweet 
{
	private String email;	// sender ID
	private Date postDate;	// posted date
	private String text;	// tweet text
	
	public Tweet()
	{
		email = "";
		postDate = new Date();
		text = "";
		
		// sets the date of the tweet
		GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(postDate);
        postDate.setTime(calendar.getTime().getTime());
	}
	
	public Tweet(String email, Date date, String text)
	{
		this.email = email;
		this.postDate = date;
		this.text = text;
	}
	
	public String getTweetEmail()
	{
		return email;
	}
	
	public void setTweetEmail(String tEmail)
    {
        this.email = tEmail;
    }
	
	public Date getTweetDate()
	{
		return postDate;
	}
	
	public void setTweetDate(Date tDate)
    {
        this.postDate = tDate;
    }
	
	public String getTweetText()
	{
		return text;
	}
	
	public void setTweetText(String tText)
    {
        this.text = tText;
    }
}

