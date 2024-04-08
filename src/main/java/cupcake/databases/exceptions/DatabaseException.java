package cupcake.databases.exceptions;

public class DatabaseException extends UserFriendyException
{

      public DatabaseException(  String userMessage ){
         super( userMessage );
     }

      public DatabaseException( String userMessage, String systemMessage )
      {
        super( userMessage, systemMessage );

      }
    
}