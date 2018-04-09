function validateForm()
{
    // valid passwords
    var password = document.getElementById('password1');
    var confirmPassword = document.getElementById('confirmPassword1');
    if(password.value !== confirmPassword.value)
    {
        var error1 = document.getElementById('errorMessage');
        error1.innerHTML = ' Error: Passwords do not match.';
        error1.className = 'isVisible';
        
        // for the *
        var span1 = document.getElementById('password_error');
        var span2 = document.getElementById('confirmPassword_error');
        
        span1.className = 'isVisible';
        span2.className = 'isVisible';
        
        // change color of background to yellow
        password.style.backgroundColor = 'yellow';
        confirmPassword.style.backgroundColor = 'yellow';
        
        return false;
    }   
    
    // valid fullname
    var fullName1 = document.getElementById('fullName').value;      // get name value
     
    // split it at the 'space' and if the number of elements in the array
    // is less than 2, means there's only one word
    if(fullName1.split(' ').length < 2)   
    {
        var error2 = document.getElementById('errorMessage');
        error2.innerHTML = 'Error: Full name is not valid.';
        error2.className = 'isVisible';
        
        // for the *
        var spanName = document.getElementById('fullName_error');
        spanName.className = 'isVisible';
        
        // change color of background to yellow
        document.getElementById("fullName").style.backgroundColor = 'yellow';
        
        return false;
    }
    
  
        
    // check for single quotation
    var form = document.getElementById('register1');    // the whole form
    var inputs = form.getElementsByTagName('input');    // all inputs
    var input1 = null;    
    
        for(var i = 0; i < inputs.length; i++)
        {
            input1 = inputs[i];                  // check all inputs
            if(input1.value.indexOf("'") !== -1) // will return a -1 if the element is not there   
            {
                var error4 = document.getElementById('errorMessage');
                error4.innerHTML = 'Error: Input has invalid characters.';
                error4.className = 'isVisible';

                // yellow background for field with the '
                input1.style.backgroundColor = 'yellow';
                return false;
            }
        }
    
    var password2 = document.getElementById('password1').value; 
    var password3 = document.getElementById('confirmPassword1').value;
        // password contains lower and upper case and number 
    
    var re = new RegExp("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])");
    if(!re.test(password2) && !re.test(password3)) // if pw's don't contiain these
    {
        var error3 = document.getElementById('errorMessage');
        error3.innerHTML = 'Error: Password must contain one lower case letter, one uppercase letter, and one number.';
        error3.className = 'isVisible';
            
        // for the *
        var span4 = document.getElementById('password_error');
        span4.className='isVisible';
            
        // yellow background
        document.getElementById('password1').style.backgroundColor = 'yellow';
        
        return false;
    }
    asf;
    return true;
}

// dynamic form: show response input when a question is selected
function showInputBox()
{
    document.getElementById('securityAnswer').innerHTML ='<input type="text" id="answer" name="answer" class="inputBox value="${user.answer}"/>';
}
