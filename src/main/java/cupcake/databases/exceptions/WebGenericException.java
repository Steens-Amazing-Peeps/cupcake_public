package cupcake.databases.exceptions;

import cupcake.databases.exceptions.UserFriendyException;

public class WebGenericException extends UserFriendyException
{
    
    public WebGenericException( String userMessage )
    {
        super( userMessage );
    }
    
    public WebGenericException( String userMessage, String systemMessage )
    {
        super( userMessage, systemMessage );
        
    }
}
