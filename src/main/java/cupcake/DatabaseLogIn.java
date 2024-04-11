package cupcake;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

final class DatabaseLogIn
{
    
    //DATABASE NAME, IF THERE IS ONLY 1 DATABASE
    final static String DATABASE_GLOBAL_NAME = "cupcake"; //Change this to the database ya need <----------------------- Throw it into 'new DataStoreManager( DATABASE_NAME )'
    
    //DATABASE DRIVER-----------------------------
    final static String DB_DRIVER = "org.postgresql.Driver";
    
    //DEFAULT CONFIG----------
    private final static String USER_LOCALHOST = "postgres";
    private final static String PASSWORD_LOCALHOST = "postgres";
    private final static String IP_LOCALHOST = "localhost";
//    NAME = DATABASE_GLOBAL_NAME
    
    //Load sensitive data-array from private file
    private final static String[] usernamePasswordIpArray = fileReader();
    

    //STEENS LAPTOP REMOTE CONFIG--------
    private final static String USER_STEENS_LAPTOP = usernamePasswordIpArray[0];
    private final static String PASSWORD_STEENS_LAPTOP = usernamePasswordIpArray[1];
    private final static String IP_STEENS_LAPTOP = usernamePasswordIpArray[2];
//    NAME = DATABASE_GLOBAL_NAME
    
    
    
    //Selector Logic--------
    final static int CONFIG_LOCALHOST = 0;
    
    //Below requires the file-reader to succeed to work.
    final static int CONFIG_STEENS_LAPTOP_REMOTE = 1;
    final static int CONFIG_STEENS_LAPTOP_LOCAL = 2; //Same as remote, but with localhost as IP




//  ----------------END OF FINAL VALUES----------------
    
    
    
    private static int pickedConfig = 0; //Currently selected// log-in config, default = CONFIG_LOCALHOST
    
    //Currently Selected Info------------------------------------
    //All of these are set by the configs above
    private static String user; //Current username
    private static String password; //Current password
    private static String ip; //Current IP
    private static String name; //Current database name
    
    static {
        updateInfo(); //Sets the above to localhost as default
    }
    
    
    
    
    
    
    
    
    
    
    //Methods--------------------------------------------------
    
    static void setDatabaseConfig( int config )
    {
        pickedConfig = config; //Set current config to something
        
        updateInfo(); //Update the info based on the newly set current config
    }
    
    private static void updateInfo()
    {
        
        switch ( pickedConfig ) { //1 Case for each config
            
            case CONFIG_LOCALHOST: //Default
                user = USER_LOCALHOST;
                password = PASSWORD_LOCALHOST;
                ip = IP_LOCALHOST;
                name = DATABASE_GLOBAL_NAME;
                return;
            
            case CONFIG_STEENS_LAPTOP_REMOTE:
                user = USER_STEENS_LAPTOP;
                password = PASSWORD_STEENS_LAPTOP;
                ip = IP_STEENS_LAPTOP;
                name = DATABASE_GLOBAL_NAME;
                return;
            
            case CONFIG_STEENS_LAPTOP_LOCAL:
                user = USER_STEENS_LAPTOP;
                password = PASSWORD_STEENS_LAPTOP;
                ip = IP_LOCALHOST;
                name = DATABASE_GLOBAL_NAME;
                return;
            
            default:
                return; //If the config does not exist, do nothing.
        }
        
    }
    
    //Gotta keep sensitive private data private, unlike facebook
    private static String[] fileReader()
    {
        String[] usernamePasswordIpArray = new String[ 3 ];
        
        File databaseInfoFile = new File( "databaseInfoPRIVATE.txt" );
        
        try {
            Scanner databaseInfoScanner = new Scanner( databaseInfoFile );
            
            usernamePasswordIpArray[ 0 ] = databaseInfoScanner.nextLine();
            usernamePasswordIpArray[ 1 ] = databaseInfoScanner.nextLine();
            usernamePasswordIpArray[ 2 ] = databaseInfoScanner.nextLine();
            
        } catch ( FileNotFoundException e ) {
            System.err.println("ERROR!: DatabaseLogIn COULD NOT FIND databaseInfoPRIVATE.txt, STEEN'S LAPTOP CONFIGS WILL BE INVALID!");
            
            usernamePasswordIpArray[ 0 ] = null;
            usernamePasswordIpArray[ 1 ] = null;
            usernamePasswordIpArray[ 2 ] = null;
        }
        
        
        return usernamePasswordIpArray;
    }
    
    
    
    
    
    
    
    
    
    //Getters-----------------------------------------------
    static String getUser()
    {
        return user;
    }
    
    static String getPassword()
    {
        return password;
    }
    
    static String getUrl( String databaseName )
    {
        return "jdbc:postgresql://" + ip + ":5432/" + databaseName + "?serverTimezone=CET&useSSL=false&allowPublicKeyRetrieval=true";
    }
    
    static String getUrl()
    {
        return getUrl( "%s" ); //For jon's stuff
    }
    
    static String getName()
    {
        return name;
    }
    
    
    
    
    
    
}
