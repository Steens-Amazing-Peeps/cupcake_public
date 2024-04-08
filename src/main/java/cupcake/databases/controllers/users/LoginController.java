package cupcake.databases.controllers.users;

import cupcake.constants.*;
import cupcake.databases.controllers.FrontPageController;
import cupcake.databases.entities.tables.User;
import cupcake.databases.exceptions.DatabaseException;
import cupcake.databases.exceptions.UnexpectedResultDbException;
import cupcake.databases.exceptions.WebInvalidInputException;
import cupcake.databases.services.users.LoginService;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class LoginController
{
    
    public static void addRoutes( Javalin app ){
        
        app.get( Pages.LOGIN_GET_PAGE, ctx -> loginGet( ctx ) );

        app.post( Pages.LOGIN_POST_PAGE, ctx -> loginPost( ctx ) );
    }
    
    public static void loginRedirect( Context ctx )
    {
        ctx.redirect( Pages.LOGIN_GET_PAGE );
    }
    
    public static void loginRender( Context ctx )
    {
        ctx.render( Html.LOGIN_HTML );
    }
    
    private static void loginGet( Context ctx )
    {
        loginRender( ctx );
    }
    
    private static void loginPost( Context ctx )
    {
        String email;
        String password;
        
        email = ctx.formParam(FormParam.email);
        password = ctx.formParam(FormParam.password);
        
        try {
            User user = LoginService.login( email, password );
            ctx.sessionAttribute(CtxSessionAttributes.currentUser, user);
            ctx.attribute( CtxAttributes.msg, "" );
            
        } catch ( WebInvalidInputException | UnexpectedResultDbException | DatabaseException e ) {
            
            ctx.attribute( CtxAttributes.msg, e.getUserMessage() );
            loginRender( ctx );
            return;
            
        }
        
        
        FrontPageController.indexRedirect( ctx );
    }
    
}
