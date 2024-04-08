package cupcake.languages.english;

import static cupcake.languages.english.Error_Prefix.DATABASE_ERROR;
import static cupcake.languages.english.Error_Prefix.INPUT_ERROR;

public interface Lang_Service
{
    String METH_FRONT_PAGE = "at frontpage: ";
    String METH_LOGIN = "at login: ";
    String METH_CREATE_ACCOUNT = "at create account: ";
    
    
    
    
    
    
    
    //FrontPage
    static String frontpageErrorUser_invalidBottom()
    {
        return INPUT_ERROR + "You must select a bottom";
    }
    static String frontpageErrorUser_invalidTop()
    {
        return INPUT_ERROR + "You must select a top";
    }
    static String frontpageErrorUser_invalidAmount()
    {
        return INPUT_ERROR + "You must select an amount";
    }
    
    static String frontpageErrorUser_invalidBottomNotNumber()
    {
        return INPUT_ERROR + "Bottom must be a number";
    }
    static String frontpageErrorUser_invalidTopNotNumber()
    {
        return INPUT_ERROR + "Top must be a number";
    }
    static String frontpageErrorUser_invalidAmountNotNumber()
    {
        return INPUT_ERROR + "Amount must be a number";
    }
    
    
    
 
    
    
    
    //Login
    static String loginErrorUser_invalidEmail( String email )
    {
        return INPUT_ERROR + "User with the email was not found. The email = '" + email + "'";
    }
    

    
    static String loginErrorUser_invalidPassword()
    {
        return INPUT_ERROR + "Wrong Password";
    }
    

    
    static String loginErrorUser_multipleAccountsWithEmail( String email )
    {
        return DATABASE_ERROR + "Found multiple accounts with the email, contact an administrator. The email = '" + email + "'";
    }
    
    static String loginErrorSys_multipleAccountsWithEmail( String email )
    {
        return DATABASE_ERROR + METH_LOGIN + "Found multiple accounts with the email, contact an administrator. The email = '" + email + "'";
    }
    
    
    
    
    
    
    //CreateAccount
    static String loginErrorUser_invalidTakenEmail( String email )
    {
        return INPUT_ERROR + "Email already used, login instead. The email = '" + email + "'";
    }
    

    
    static String loginErrorUser_invalidAttemptedEmail( String email )
    {
        return INPUT_ERROR + "Not a valid email. The email = '" + email + "'";
    }
    

    
    static String createErrorUser_invalidRepeatPassword()
    {
        return INPUT_ERROR + "Passwords do not match";
    }
    

    static String createErrorUser_invalidLongPassword()
    {
        return INPUT_ERROR + "Password too long";
    }
    

    static String createErrorUser_invalidShortPassword()
    {
        return INPUT_ERROR + "Password too short";
    }
    
    static String createErrorSys_multipleAccountsWithEmail( String email )
    {
        return DATABASE_ERROR + METH_CREATE_ACCOUNT + "Found multiple accounts with the email, contact an administrator. The email = '" + email + "'";
    }
    

}
