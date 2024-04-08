package cupcake.databases.controllers.users;

import cupcake.constants.*;
import cupcake.databases.entities.extended.OrderExtended;
import cupcake.databases.entities.extended.UserExtended;
import cupcake.databases.entities.tables.User;
import cupcake.databases.exceptions.DatabaseException;
import cupcake.databases.exceptions.UnexpectedResultDbException;
import cupcake.databases.exceptions.WebInvalidInputException;
import cupcake.databases.mappers.tables.RoleMapper;
import cupcake.databases.services.users.HistoryService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.LinkedHashMap;
import java.util.Map;

public class HistoryController
{
    
    public static void addRoutes( Javalin app )
    {
        
        app.get( Pages.ORDER_HISTORY_GET_PAGE, ctx -> historyGet( ctx ) );
        
        app.post( Pages.ORDER_HISTORY_SORT_PAGE, ctx -> historySort( ctx ) );
        
        app.post( Pages.ORDER_HISTORY_DONE_PAGE, ctx -> historyDone( ctx ) );
        app.post( Pages.ORDER_HISTORY_REMOVE_PAGE, ctx -> historyRemove( ctx ) );
    }
    
    public static void historyRedirect( Context ctx )
    {
        ctx.redirect( Pages.ORDER_HISTORY_GET_PAGE );
    }
    
    public static void historyRender( Context ctx )
    {
        ctx.render( Html.HISTORY_HTML );
    }
    
    private static void historyGet( Context ctx )
    {
        if ( shouldLoadOrderHistoryMap( ctx ) ) {
            return;
        }
        
        if (  ctx.sessionAttribute(CtxGlobalAttributes.userMap) == null ) {
            ctx.sessionAttribute( CtxGlobalAttributes.userMap, CtxGlobalAttributes.USER_MAP);
        }
        
        historyRender( ctx );
    }
    
    
    private static void historyDone( Context ctx )
    {
        if ( shouldLoadOrderHistoryMap( ctx ) ) {
            return;
        }
        
        
        String userIdAsString = ctx.formParam( FormParam.orderOwnerId );
        
        
        try {
            if ( userIdAsString == null ) {
                throw new WebInvalidInputException( "Must be looged in to do that" );
            }
//          Get Loaded copy
            
            Map< Integer, UserExtended > currentOrdersMap = ctx.sessionAttribute( CtxSessionAttributes.currentOrderHistory );

//          Updated database
            String doneOrderIdAsString = ctx.formParam( FormParam.orderId );
            
            currentOrdersMap = HistoryService.markOrderAsDone( doneOrderIdAsString, currentOrdersMap, userIdAsString );

//          Updated loaded map of database
            ctx.sessionAttribute( CtxSessionAttributes.currentOrderHistory, currentOrdersMap );
            
            
        } catch ( WebInvalidInputException | UnexpectedResultDbException | DatabaseException e ) {
            
            ctx.attribute( CtxAttributes.msg, e.getUserMessage() );
            historyRender( ctx );
            return;
        }
        
        
        historyRedirect( ctx );
    }
    
    
    private static void historyRemove( Context ctx )
    {
        if ( shouldLoadOrderHistoryMap( ctx ) ) {
            return;
        }
        
        String userIdAsString = ctx.formParam( FormParam.orderOwnerId );
        
        try {
            if ( userIdAsString == null ) {
                throw new WebInvalidInputException( "Must be looged in to do that" );
            }
//          Get Loaded copy
            Map< Integer, UserExtended > currentOrdersMap = ctx.sessionAttribute( CtxSessionAttributes.currentOrderHistory );

//          Updated database
            String doneOrderIdAsString = ctx.formParam( FormParam.orderId );
            
            currentOrdersMap = HistoryService.deleteOrder( doneOrderIdAsString, currentOrdersMap, userIdAsString );

//          Updated loaded map of database
            ctx.sessionAttribute( CtxSessionAttributes.currentOrderHistory, currentOrdersMap );
            
        } catch ( WebInvalidInputException | UnexpectedResultDbException | DatabaseException e ) {
            
            ctx.attribute( CtxAttributes.msg, e.getUserMessage() );
            historyRender( ctx );
            return;
        }
        
        historyRedirect( ctx );
    }
    
    private static void historySort( Context ctx )
    {
        String filterAsString;
        User currentUser;
        
        filterAsString = ctx.formParam( FormParam.filter );
        currentUser = ctx.sessionAttribute( CtxSessionAttributes.currentUser );
        
        try {
            Map<Integer, UserExtended> usersExtendedMap = HistoryService.loadSortedUsers( filterAsString, CtxGlobalAttributes.USER_MAP, currentUser );
            ctx.sessionAttribute( CtxSessionAttributes.currentOrderHistory, usersExtendedMap );
            ctx.attribute( CtxAttributes.msg, "" );
            
        } catch ( DatabaseException | WebInvalidInputException e ) {
            
            ctx.attribute( CtxAttributes.msg, e.getUserMessage() );
            historyRender( ctx );
            return;
        }
        
        historyRedirect( ctx );
    }
    
    private static boolean shouldLoadOrderHistoryMap( Context ctx )
    {
        User user;
        
        user = ctx.sessionAttribute( CtxSessionAttributes.currentUser );
        
        if ( user != null && (ctx.sessionAttribute( CtxSessionAttributes.currentOrderHistory ) == null || user.getRoleId() != RoleMapper.ADMIN_ID )) {
            
            try {
                Map< Integer, OrderExtended > currentUserOrdersMap = HistoryService.getHistoryForUser( user );
                
                Map< Integer, UserExtended > userExtendedMap = new LinkedHashMap<>();
                
                userExtendedMap.put( user.getUserId(), new UserExtended( user, currentUserOrdersMap ) );
                
                ctx.sessionAttribute( CtxSessionAttributes.currentOrderHistory, userExtendedMap );
                
            } catch ( DatabaseException e ) {
                
                ctx.attribute( CtxAttributes.msg, e.getUserMessage() );
                historyRender( ctx );
                return true;
            }
            
        }
        return false;
    }
    
}
