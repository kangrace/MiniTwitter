/* Step 6: create a javaBean for User that includes all user attributes from 
   signup page and all get/set methods. */

package business;


public class User 
{
    private String fullName;
    private String email;
    private String password1;
    private String confirmPassword1;
    private String dateOfBirth;
    private String answer;
    private String question;
    
    public User() 
    {
        fullName = "";
        email = "";
        password1 = "";
        confirmPassword1 = "";
        dateOfBirth = "";
        answer = "";
        question = "";
    }

    public User(String fullName, String email, String password, String confirmPassword, String dateOfBirth, String answer, String question) 
    {
        this.fullName = fullName;
        this.email = email;
        this.password1 = password;
        this.confirmPassword1 = confirmPassword;
        this.dateOfBirth = dateOfBirth;
        this.answer = answer;
        this.question = question;
        
    }
    
    public User(String fromString)
    {
        String[] data = fromString.replace("[", "").split(",");
        this.setFullName(data[0]);
        this.setEmail(data[1]);
        this.setPassword(data[2]);
        this.setConfirmPassword(data[3]);
        this.setDateOfBirth(data[4]);
        this.setAnswer(data[5]);
        this.setQuestion(data[6]);
    }
    
    public String getFullName()
    {
        return fullName;
    }
    public void setFullName(String fullName1)
    {
        this.fullName = fullName1;
    }
    
    public String getPassword()
    {
        return password1;
    }
    public void setPassword(String password)
    {
        this.password1 = password;
    }
    
    public String getConfirmPassword()
    {
        return confirmPassword1;
    }
    public void setConfirmPassword(String password)
    {
        this.confirmPassword1 = password;
    }
    
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email1)
    {
        this.email = email1;
    }
    
    public String getDateOfBirth()
    {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dob)
    {
        this.dateOfBirth = dob;
    }
    
    public String getAnswer()
    {
        return answer;
    }
    public void setAnswer(String answer1)
    {
        this.answer = answer1;
    }
    
    public String getQuestion()
    {
        return question;
    }
    public void setQuestion(String question1)
    {
        this.question = question1;
    }
	
    @Override
    public String toString()
    {
      StringBuilder sb = new StringBuilder();
      sb.append(String.format("[%s,%s]", this.getFullName(), this.getEmail(), this.getPassword(), 
                                         this.getConfirmPassword(), this.getEmail(), this.getDateOfBirth(), 
                                         this.getAnswer(), this.getQuestion()));
      return sb.toString();
    }
}
