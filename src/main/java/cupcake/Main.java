package cupcake;

import cupcake.constants.CtxGlobalAttributes;
import cupcake.databases.controllers.FrontPageController;
import cupcake.databases.controllers.users.*;
import cupcake.databases.mappers.tables.TemplateMappersStartUp;
import cupcake.databases.persistence.ConnectionPoolAccess;
import cupcake.databases.persistence.ConnectionPoolAccessIf;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;
import cupcake.config.ThymeleafConfig;
import cupcake.databases.persistence.ConnectionPool;

public class Main
{
    static {//Change these to change the log-in location of the database

//        DatabaseLogIn.setDatabaseConfig( DatabaseLogIn.CONFIG_LOCALHOST );

//        DatabaseLogIn.setDatabaseConfig( DatabaseLogIn.CONFIG_STEENS_LAPTOP_LOCAL );
        DatabaseLogIn.setDatabaseConfig( DatabaseLogIn.CONFIG_STEENS_LAPTOP_REMOTE );
    }
    
    private static final String USER = DatabaseLogIn.getUser();
    private static final String PASSWORD = DatabaseLogIn.getPassword();
    private static final String URL = DatabaseLogIn.getUrl();
    private static final String DB = DatabaseLogIn.getName();
    
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance( USER, PASSWORD, URL, DB );
    
    private static final ConnectionPoolAccessIf CONNECTION_POOL_ACCESS = ConnectionPoolAccess.setConnectionPoolAccess( CONNECTION_POOL );
    
    public static void main( String[] args )
    {
        // Initializing Javalin and Jetty webserver
        
        Javalin app = Javalin.create( config -> {
            config.staticFiles.add( "/web/public" );
            config.fileRenderer( new JavalinThymeleaf( ThymeleafConfig.templateEngine() ) );
            
            TemplateMappersStartUp.startUpSetConnectionPoolAccess( CONNECTION_POOL_ACCESS );
            CtxGlobalAttributes.startUp();
            
        } ).start( 7070 );
        
        
        // Routing
        
        FrontPageController.addRoutes( app );
        
        CreateAccountController.addRoutes( app );
        LoginController.addRoutes( app );
        LogoutController.addRoutes( app );
        
        CartController.addRoutes( app );
        HistoryController.addRoutes( app );
        
    }
    
}