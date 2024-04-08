package cupcake.databases.exceptions;




/**
 * All our nice exceptions should be extending this
 *
 * IMPORTANT: ALL CHILDREN and THEIR CHILDREN MUST implement the following constructors
 *
 *      --------------------------------------------------------------------------------
 *
 *      public DatabaseException(  String userMessage ){
 *          super( userMessage );
 *      }
 *
 *       public DatabaseException( String userMessage, String systemMessage )
 *       {
 *         super( userMessage, systemMessage );
 *
 *       }
 *
 *       --------------------------------------------------------------------------------
 *
 * I recommend copying the below developer comment version, easier to copy/paste
 *  */


//      public DatabaseException(  String userMessage ){
//         super( userMessage );
//     }
//
//      public DatabaseException( String userMessage, String systemMessage )
//      {
//        super( userMessage, systemMessage );
//
//      }


public abstract class UserFriendyException extends Exception
{
    private String userMessage;
    
    
    
    
    
    //Constructors---------------------------
    public UserFriendyException( String userMessage )
    {
        this.constructorHelperSetupMessages( userMessage );
    }

    public UserFriendyException( String userMessage, String systemMessage )
    {
        super(systemMessage);

        this.constructorHelperSetupMessages( userMessage, systemMessage );
    }
    
    
    
    //Constructor helpers--------------------------------------------------
    /**
     * Should only be used in the constructor
     *
     * @param  userMessage the 'nice' message for the user
     *
     */
    private void constructorHelperSetupMessages(String userMessage)
    {
        this.constructorHelperSetupMessages( userMessage, null );
    }
    
    /**
     * Should only be used in the constructor
     *
     * @param  userMessage the 'nice' message for the user
     *
     * @param  systemMessage the developer message for console and logging
     */
    private void constructorHelperSetupMessages(String userMessage, String systemMessage) {
        if ( userMessage != null && systemMessage != null ) { //Don't print user errors unless we got a systemMessage too
            System.err.println("userMessage: " + userMessage);
        }
        
        if ( systemMessage != null ) {
            System.err.println( "systemMessage: " + systemMessage );
        }
        
        this.userMessage = userMessage;
    }
    
    
    
    
    //Getters-------------------------------------------------------
    public String getUserMessage()
    {
        return this.userMessage;
    }
}
