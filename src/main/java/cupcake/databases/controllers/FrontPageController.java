package cupcake.databases.controllers;

import cupcake.constants.*;

import cupcake.databases.entities.extended.OrderExtended;
import cupcake.databases.entities.tables.OrderLine;
import cupcake.databases.exceptions.WebInvalidInputException;

import cupcake.databases.services.FrontPageService;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class FrontPageController
{
    
    public static void addRoutes( Javalin app )
    {
        
        app.get( Pages.INDEX_GET_PAGE, ctx -> indexGet( ctx ) );
        
        app.post( Pages.INDEX_POST_PAGE, ctx -> indexPost( ctx ) );
    }
    
    public static void indexRedirect( Context ctx )
    
    {
        ctx.redirect( Pages.INDEX_GET_PAGE );
    }
    
    
    public static void indexRender( Context ctx )
    {
        if ( ctx.sessionAttribute( CtxGlobalAttributes.topMap ) == null ) {
            ctx.sessionAttribute( CtxGlobalAttributes.topMap, CtxGlobalAttributes.TOP_MAP );
        }
        
        if ( ctx.sessionAttribute( CtxGlobalAttributes.bottomMap ) == null ) {
            ctx.sessionAttribute( CtxGlobalAttributes.bottomMap, CtxGlobalAttributes.BOTTOM_MAP );
        }
        
        ctx.render( Html.INDEX_HTML );
    }
    
    private static void indexGet( Context ctx )
    {
        indexRender( ctx );
    }
    
    private static void indexPost( Context ctx )
    {
        OrderExtended currentOrder;
        
        if ( ctx.sessionAttribute( CtxSessionAttributes.currentOrder ) == null ) {
            currentOrder = new OrderExtended();
            
            ctx.sessionAttribute( CtxSessionAttributes.currentOrder, currentOrder );
            
        } else {
            currentOrder = ctx.sessionAttribute( CtxSessionAttributes.currentOrder );
        }
        
        String bottomIdAsString;
        String topIdAsString;
        String amountAsString;
        
        bottomIdAsString = ctx.formParam( FormParam.newOrderlineBottom );
        topIdAsString = ctx.formParam( FormParam.newOrderlineTop );
        amountAsString = ctx.formParam( FormParam.newOrderlineAmount );

        try {
            OrderLine newOrderLine = FrontPageService.addOrderLine( bottomIdAsString, topIdAsString, amountAsString );
            
            assert currentOrder != null;
            currentOrder.addToOrder( newOrderLine );
            currentOrder.calcSum();
            
        } catch ( WebInvalidInputException e ) {
            
            ctx.attribute( CtxAttributes.msg, e.getUserMessage() );
            indexRender( ctx );
            return;
        }
        
        
        indexRedirect( ctx );
    }
    
    
    
    
    
    
    
}
