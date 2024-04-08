package cupcake.databases.exceptions;

public class NoIdKeyReturnedException extends UserFriendyException
{
    
    public NoIdKeyReturnedException( String userMessage ){
        super( userMessage );
    }
    
    public NoIdKeyReturnedException( String userMessage, String systemMessage )
    {
        super( userMessage, systemMessage );
        
    }
    
}
