package cupcake.databases.controllers.users;

import cupcake.constants.*;
import cupcake.databases.entities.extended.OrderExtended;
import cupcake.databases.entities.tables.User;
import cupcake.databases.exceptions.DatabaseException;
import cupcake.databases.exceptions.NoIdKeyReturnedException;
import cupcake.databases.exceptions.UnexpectedResultDbException;
import cupcake.databases.exceptions.WebInvalidInputException;
import cupcake.databases.services.users.CartService;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class CartController
{
    
    public static void addRoutes( Javalin app )
    {
        
        app.get( Pages.CART_GET_PAGE, ctx -> cartGet( ctx ) );
        
        app.post( Pages.CART_REMOVE_PAGE, ctx -> cartRemove( ctx ) );
        app.post( Pages.CART_BUY_PAGE, ctx -> cartBuy( ctx ) );
    }
    
    public static void cartRedirect( Context ctx )
    {
        ctx.redirect( Pages.CART_GET_PAGE );
    }
    
    public static void cartRender( Context ctx )
    {
        ctx.render( Html.CART_HTML );
    }
    
    private static void cartGet( Context ctx )
    {
        if ( ctx.sessionAttribute( CtxSessionAttributes.currentOrder ) == null ) {
            
            ctx.sessionAttribute( CtxSessionAttributes.currentOrder, new OrderExtended() );
        }
        
        cartRender( ctx );
    }
    
    private static void cartBuy( Context ctx )
    {
        if ( ctx.sessionAttribute( CtxSessionAttributes.currentOrder ) == null ) {
            ctx.sessionAttribute( CtxSessionAttributes.currentOrder, new OrderExtended() );
        }
        
        //Input
        User user;
        OrderExtended orderExtended;
        
        user = ctx.sessionAttribute( CtxSessionAttributes.currentUser );
        orderExtended = ctx.sessionAttribute( CtxSessionAttributes.currentOrder );
        
        try {
            //Magic Process wooooooowoooooooo
            orderExtended = CartService.buy( user, orderExtended );
            
            //Output
            ctx.attribute( CtxAttributes.success, "Your Order ID: " + orderExtended.getOrderId() );
            
            ctx.sessionAttribute( CtxSessionAttributes.currentOrder, new OrderExtended() );
            cartRender( ctx );
            return;
            
        } catch ( WebInvalidInputException | NoIdKeyReturnedException | UnexpectedResultDbException |
                  DatabaseException e ) {
            
            ctx.attribute( CtxAttributes.msg, e.getUserMessage() );
            cartRender( ctx );
            return;
        }
    }
    
    private static void cartRemove( Context ctx )
    {
        //Input
        OrderExtended currentOrder;
        String orderLineIndexAsString;
        
        currentOrder = ctx.sessionAttribute( CtxSessionAttributes.currentOrder );
        orderLineIndexAsString = ctx.formParam( FormParam.deleteCupcake );
        
        //Update
        try {
            currentOrder = CartService.deleteOrderLine( currentOrder, orderLineIndexAsString );
            
            //Output
            ctx.sessionAttribute( CtxSessionAttributes.currentOrder, currentOrder );
            
        } catch ( WebInvalidInputException e ) {
            
            ctx.attribute( CtxAttributes.msg, e.getUserMessage() );
            cartRender( ctx );
            return;
        }
        
        cartRedirect( ctx );
    }
    
}
