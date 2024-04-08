package cupcake.databases.services.users;

import cupcake.constants.FormParam;
import cupcake.databases.entities.extended.OrderExtended;
import cupcake.databases.entities.extended.UserExtended;
import cupcake.databases.entities.tables.Order;
import cupcake.databases.entities.tables.OrderLine;
import cupcake.databases.entities.tables.User;
import cupcake.databases.exceptions.DatabaseException;
import cupcake.databases.exceptions.UnexpectedResultDbException;
import cupcake.databases.exceptions.WebInvalidInputException;
import cupcake.databases.mappers.tables.*;
import cupcake.languages.english.Lang_Service;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import static cupcake.databases.services.FrontPageService.addErrMsg;

public class HistoryService
{
    
    public static Map< Integer, OrderExtended > getHistoryForUser( User user ) throws DatabaseException
    {
        return getHistoryForUser( user.getUserId() );
    }
    
    public static Map< Integer, OrderExtended > getHistoryForUser( Integer userId ) throws DatabaseException
    {
        Map< Integer, Order > orderMap = OrderMapper.readAllByUserId( userId );
        
        return orderMapToOrderExtendedMap( orderMap );
    }
    
    
    
    public static Map< Integer, UserExtended > loadSortedUsers( String filterAsString, Map< Integer, User > userMap, User currentUser ) throws WebInvalidInputException, DatabaseException
    {
        if ( filterAsString == null ) {
            throw new WebInvalidInputException( "Received no filter, cannot update", "Failed to retrieve admin only filter" );
        }
        if ( currentUser == null ) {
            throw new WebInvalidInputException( "Received no user, cannot update", "Failed to retrieve user for admin only filter" );
        }
        
        if ( currentUser.getRoleId() != RoleMapper.ADMIN_ID ) {
            throw new WebInvalidInputException( "Illegal action, not an admin" );
        }
        
        Map< Integer, UserExtended > userExtendedMap = new LinkedHashMap<>();
        
        //ALL
        if ( Objects.equals( filterAsString, FormParam.filterAll ) ) {
            
            
            Map< Integer, Order > orderMap = OrderMapper.readAll();
            
            Map< Integer, OrderExtended > orderExtendedMap = orderMapToOrderExtendedMap( orderMap );
            
            for ( User user : userMap.values() ) {
                userExtendedMap.put( user.getUserId(), new UserExtended( user, orderExtendedMap ) );
            }
            
            return userExtendedMap;
        }
        
        
        int filterUserId;
        User user;
        
        //Me
        if ( Objects.equals( filterAsString, FormParam.filterMe ) ) {
            
            filterUserId = currentUser.getUserId();
            user = currentUser;
            
        } else {
            
            //Specific User
            try {
                filterUserId = Integer.parseInt( filterAsString );
                user = UserMapper.readSingle( filterUserId );
                
                if ( user == null ) {
                    throw new WebInvalidInputException( "Your chosen user was invalid", "tried to search for orders for an invalid user with ID= " + filterUserId );
                }
                
            } catch ( NumberFormatException e ) {
                throw new WebInvalidInputException( "Not a valid user", "Invalid user ID for order history, not a number" + e.getMessage() );
            }
            
        }
        
        
        Map< Integer, OrderExtended > orderExtendedMap = getHistoryForUser( filterUserId );
        
        userExtendedMap.put( user.getUserId(), new UserExtended( user, orderExtendedMap ) );
        
        return userExtendedMap;
    }
    
    public static Map< Integer, UserExtended > markOrderAsDone( String doneOrderIdAsString, Map< Integer, UserExtended > userExtendedMap, String userIdAsString ) throws WebInvalidInputException, UnexpectedResultDbException, DatabaseException
    {
        if ( userExtendedMap == null ) {
            throw new WebInvalidInputException( "Order map does not exist, cannot update" );
        }
        
        Integer doneOrderId = verifyNumberParameterAsString( doneOrderIdAsString );
        Integer userId = verifyNumberParameterAsString( userIdAsString );
        
        UserExtended userOrderOwner = userExtendedMap.get( userId );
        
        OrderExtended orderLoaded = userOrderOwner.getOrdersExtended().get( doneOrderId );
        
        if ( orderLoaded == null ) {
            throw new WebInvalidInputException( "Order does not exist, cannot update" );
        }
        
        if ( Objects.equals( orderLoaded.getStatusId(), StatusMapper.DONE_ID ) ) {
            throw new WebInvalidInputException( "Order is already done" );
        }
        
        Order orderCopy = new Order(); //Exists so we don't change the original unless DB goes through
        orderCopy.setOrderId( orderLoaded.getOrderId() );
        orderCopy.setUserId( orderLoaded.getUserId() );
        orderCopy.setDateOrdered( orderLoaded.getDateOrdered() );
//        orderCopy.setDateReady( orderLoaded.getDateReady() );
//        orderCopy.setStatusId( orderLoaded.getStatusId() );
        
        java.sql.Date today = new java.sql.Date( System.currentTimeMillis() );
        
        orderCopy.setDateReady( today );  //TODO: Ability to select date
        orderCopy.setStatusId( StatusMapper.DONE_ID );
        
        OrderMapper.update( orderCopy ); //DB goes through
        
        //We change the original
        orderLoaded.setStatusId( orderCopy.getStatusId() );
        orderLoaded.setDateReady( orderCopy.getDateReady() );
        
//      Overwrite the original with the changed values of the double, but only if we succeed.
        userOrderOwner.getOrdersExtended().put( orderCopy.getOrderId(), orderLoaded );
        
        return userExtendedMap;
    }
    
    public static Map< Integer, UserExtended > deleteOrder( String deleteOrderIdAsString, Map< Integer, UserExtended > userExtendedMap, String userIdAsString ) throws WebInvalidInputException, UnexpectedResultDbException, DatabaseException
    {
        if ( userExtendedMap == null ) {
            throw new WebInvalidInputException( "Order map does not exist, cannot delete" );
        }
        
        Integer deleteOrderId = verifyNumberParameterAsString( deleteOrderIdAsString );
        Integer userId = verifyNumberParameterAsString( userIdAsString );
        
        if ( userExtendedMap.get( userId ) == null ) {
            throw new WebInvalidInputException( "Owner does not exist, cannot delete order" );
        }
        
        if ( userExtendedMap.get( userId ).getOrdersExtended().get( deleteOrderId ) == null ) {
            throw new WebInvalidInputException( "Order does not exist, cannot delete order" );
        }
        
        OrderLineMapper.deleteByOrder( deleteOrderId );
        
        OrderMapper.delete( deleteOrderId );
        
        UserExtended userOrderOwner = userExtendedMap.get( userId );
        
        userOrderOwner.getOrdersExtended().remove( deleteOrderId );
        
        userOrderOwner.calcSum();
        
        return userExtendedMap;
    }
    
    private static Integer verifyNumberParameterAsString( String numberParameterAsString ) throws WebInvalidInputException
    {
        StringBuilder stringBuilderErrMsg = new StringBuilder();
        boolean isError = false;
        
        if ( numberParameterAsString == null ) {
            isError = true;
            addErrMsg( stringBuilderErrMsg, Lang_Service.frontpageErrorUser_invalidBottom() );
        }
        
        Integer numberParameter = null;
        
        if ( numberParameterAsString != null ) {
            try {
                numberParameter = Integer.parseInt( numberParameterAsString );
            } catch ( NumberFormatException e ) {
                isError = true;
                stringBuilderErrMsg.append( Lang_Service.frontpageErrorUser_invalidBottomNotNumber() );
            }
        }
        
        if ( isError ) {
            throw new WebInvalidInputException( stringBuilderErrMsg.toString() );
        }
        
        return numberParameter;
    }
    
    @NotNull
    private static Map< Integer, OrderExtended > orderMapToOrderExtendedMap( Map< Integer, Order > orderMap ) throws DatabaseException
    {
        Map< Integer, OrderExtended > orderExtendedMap = new LinkedHashMap<>();
        
        Map< Integer, OrderLine > orderLineMap;
        OrderExtended orderExtended;
        
        for ( Order order : orderMap.values() ) {
            orderLineMap = OrderLineMapper.readAllByOrderId( order.getOrderId() );
            
            orderExtended = new OrderExtended( order, orderLineMap );
            
            orderExtendedMap.put( orderExtended.getOrderId(), orderExtended );
        }
        
        return orderExtendedMap;
    }
    
}
