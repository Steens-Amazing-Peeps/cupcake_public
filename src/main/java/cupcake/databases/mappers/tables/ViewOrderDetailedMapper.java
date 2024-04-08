package cupcake.databases.mappers.tables;


import cupcake.databases.entities.views.OrderDetailed;
import cupcake.databases.exceptions.DatabaseException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ViewOrderDetailedMapper implements TemplateDtoCreator
{
    private static final ViewOrderDetailedMapper DTO_CREATOR = new ViewOrderDetailedMapper();
    
    private ViewOrderDetailedMapper()
    {
        
    }
    
    public static Map< Integer, OrderDetailed > readAll() throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.order_detailed " +
                "ORDER BY " +
                "   order_id;";
        
        
        return ( Map< Integer, OrderDetailed > ) TemplateSharedCrud.readAll( sql, DTO_CREATOR );
    }
    
    public static Map< Integer, OrderDetailed > readAllByOrderId( int orderId ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.order_detailed " +
                "WHERE " +
                "   order_id = ? " +
                "ORDER BY " +
                "   order_line_id;";
        
        
        return ( Map< Integer, OrderDetailed > ) TemplateSharedCrud.readAll( sql, orderId, DTO_CREATOR );
    }
    
    public static Map< Integer, OrderDetailed > readAllByUserId( int userId ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.order_detailed " +
                "WHERE " +
                "   user_id = ? " +
                "ORDER BY " +
                "   order_id;";
        
        
        return ( Map< Integer, OrderDetailed > ) TemplateSharedCrud.readAll( sql, userId, DTO_CREATOR );
    }
    
    public static Map< Integer, OrderDetailed > readAllByStatusId( int statusId ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.order_detailed " +
                "WHERE " +
                "   status_id = ? " +
                "ORDER BY " +
                "   order_id;";
        
        
        return ( Map< Integer, OrderDetailed > ) TemplateSharedCrud.readAll( sql, statusId, DTO_CREATOR );
    }
    
    public static Map< Integer, OrderDetailed > readAllByTopId( int topId ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.order_detailed " +
                "WHERE " +
                "   top_id = ? " +
                "ORDER BY " +
                "   order_id;";
        
        
        return ( Map< Integer, OrderDetailed > ) TemplateSharedCrud.readAll( sql, topId, DTO_CREATOR );
    }
    
    public static Map< Integer, OrderDetailed > readAllByBottomId( int bottomId ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.order_detailed " +
                "WHERE " +
                "   bottom_id = ? " +
                "ORDER BY " +
                "   order_id;";
        
        
        return ( Map< Integer, OrderDetailed > ) TemplateSharedCrud.readAll( sql, bottomId, DTO_CREATOR );
    }
    
    @Override
    public Object createDto( ResultSet rs ) throws SQLException
    {
        OrderDetailed orderDetailed;
        
        orderDetailed = new OrderDetailed();
        orderDetailed.setOrderId( rs.getInt( "order_id" ) );
        orderDetailed.setUserId( rs.getInt( "user_id" ) );
        orderDetailed.setEmail( rs.getString( "email" ) );
        orderDetailed.setRoleId( rs.getInt( "role_id" ) );
        orderDetailed.setRole( rs.getString( "role" ) );
        orderDetailed.setDateOrdered( rs.getDate( "date_ordered" ) );
        orderDetailed.setDateReady( rs.getDate( "date_ready" ) );
        orderDetailed.setStatusId( rs.getInt( "status_id" ) );
        orderDetailed.setStatus( rs.getString( "status" ) );
        orderDetailed.setOrderLineId( rs.getInt( "order_line_id" ) );
        orderDetailed.setTopId( rs.getInt( "top_id" ) );
        orderDetailed.setTopTaste( rs.getString( "top_taste" ) );
        orderDetailed.setTopPriceOere( rs.getInt( "top_price_oere" ) );
        orderDetailed.setTopObsolete( rs.getBoolean( "top_obsolete" ) );
        orderDetailed.setBottomId( rs.getInt( "bottom_id" ) );
        orderDetailed.setBottomTaste( rs.getString( "bottom_taste" ) );
        orderDetailed.setBottomPriceOere( rs.getInt( "bottom_price_oere" ) );
        orderDetailed.setBottomObsolete( rs.getBoolean( "bottom_obsolete" ) );
        orderDetailed.setAmount( rs.getInt( "amount" ) );
        
        return orderDetailed;
    }
    
    @Override
    public Map< Integer, ? > createDtoMultiple( ResultSet rs ) throws SQLException
    {
        int OrderDetailedId = 0;
        Map< Integer, OrderDetailed > orderDetailedsMap = new LinkedHashMap<>();
        OrderDetailed orderDetailed;
        
        while ( rs.next() ) {
            orderDetailed = new OrderDetailed();
            orderDetailed.setOrderId( rs.getInt( "order_id" ) );
            orderDetailed.setUserId( rs.getInt( "user_id" ) );
            orderDetailed.setEmail( rs.getString( "email" ) );
            orderDetailed.setRoleId( rs.getInt( "role_id" ) );
            orderDetailed.setRole( rs.getString( "role" ) );
            orderDetailed.setDateOrdered( rs.getDate( "date_ordered" ) );
            orderDetailed.setDateReady( rs.getDate( "date_ready" ) );
            orderDetailed.setStatusId( rs.getInt( "status_id" ) );
            orderDetailed.setStatus( rs.getString( "status" ) );
            orderDetailed.setOrderLineId( rs.getInt( "order_line_id" ) );
            orderDetailed.setTopId( rs.getInt( "top_id" ) );
            orderDetailed.setTopTaste( rs.getString( "top_taste" ) );
            orderDetailed.setTopPriceOere( rs.getInt( "top_price_oere" ) );
            orderDetailed.setTopObsolete( rs.getBoolean( "top_obsolete" ) );
            orderDetailed.setBottomId( rs.getInt( "bottom_id" ) );
            orderDetailed.setBottomTaste( rs.getString( "bottom_taste" ) );
            orderDetailed.setBottomPriceOere( rs.getInt( "bottom_price_oere" ) );
            orderDetailed.setBottomObsolete( rs.getBoolean( "bottom_obsolete" ) );
            orderDetailed.setAmount( rs.getInt( "amount" ) );
            
            OrderDetailedId++;
            orderDetailedsMap.put( OrderDetailedId, orderDetailed );
        }
        
        return orderDetailedsMap;
    }
    
    @Override
    public Object setId( Object entity, Integer id ) //Should never run for a view
    {
        OrderDetailed orderDetailed = ( OrderDetailed ) entity;
//        orderDetailed.setOrderDetailedId( id ); Doesn't have one
        return orderDetailed; //Which is the same as returning entity
    }
    
}
