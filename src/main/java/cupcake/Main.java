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
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/%s?serverTimezone=CET&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String DB = "cupcake";
    
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