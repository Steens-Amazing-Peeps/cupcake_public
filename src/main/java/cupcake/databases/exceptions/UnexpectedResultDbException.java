package cupcake.databases.exceptions;

public class UnexpectedResultDbException extends UserFriendyException
{
    public UnexpectedResultDbException( String userMessage ){
        super( userMessage );
    }
    
    public UnexpectedResultDbException( String userMessage, String systemMessage )
    {
        super( userMessage, systemMessage );
        
    }
}
