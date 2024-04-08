package cupcake.databases.controllers.users;

import cupcake.constants.*;
import cupcake.databases.controllers.FrontPageController;
import cupcake.databases.entities.tables.User;
import cupcake.databases.exceptions.DatabaseException;
import cupcake.databases.exceptions.NoIdKeyReturnedException;
import cupcake.databases.exceptions.UnexpectedResultDbException;
import cupcake.databases.exceptions.WebInvalidInputException;
import cupcake.databases.services.users.CreateAccountService;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class CreateAccountController
{
    
    public static void addRoutes( Javalin app )
    {
        
        app.get( Pages.CREATE_ACCOUNT_GET_PAGE, ctx -> createAccountGet( ctx ) );

        app.post( Pages.CREATE_ACCOUNT_POST_PAGE, ctx -> createAccountPost( ctx ) );
    }
    
    public static void createAccountRedirect( Context ctx )
    {
        ctx.redirect( Pages.CREATE_ACCOUNT_GET_PAGE );
    }
    
    public static void createAccountRender( Context ctx )
    {
        ctx.render( Html.CREATE_ACCOUNT_HTML );
    }
    
    private static void createAccountGet( Context ctx )
    {
        createAccountRender( ctx );
    }
    
    private static void createAccountPost( Context ctx )
    {
        String email;
        String password;
        String passwordAgain;
        
        email = ctx.formParam(FormParam.email);
        password = ctx.formParam(FormParam.password);
        passwordAgain = ctx.formParam(FormParam.verifyPassword);

        try {
            User user = CreateAccountService.createAccount( email, password, passwordAgain );
            ctx.sessionAttribute(CtxSessionAttributes.currentUser, user);
            ctx.attribute( CtxAttributes.msg, "" );
            
        } catch ( WebInvalidInputException | NoIdKeyReturnedException | UnexpectedResultDbException |
                  DatabaseException e ) {
            
            ctx.attribute( CtxAttributes.msg, e.getUserMessage() );
            createAccountRender(ctx);
            return;
            
        }
        
        
        FrontPageController.indexRedirect( ctx );
    }
    
}
