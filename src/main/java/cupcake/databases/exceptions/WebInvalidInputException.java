package cupcake.databases.exceptions;

public class WebInvalidInputException extends UserFriendyException
{
    
    public WebInvalidInputException( String userMessage )
    {
        super( userMessage );
    }
    
    public WebInvalidInputException( String userMessage, String systemMessage )
    {
        super( userMessage, systemMessage );
        
    }
}
