package cupcake.databases.controllers.users;

import cupcake.constants.*;
import cupcake.databases.controllers.FrontPageController;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class LogoutController
{
    public static void addRoutes( Javalin app ){
        
        app.post( Pages.LOGOUT_POST_PAGE, ctx -> logoutPost( ctx ) );
    }
    
    private static void logoutPost( Context ctx )
    {
        ctx.req().getSession().invalidate();
        
        FrontPageController.indexRedirect( ctx );
    }
}
