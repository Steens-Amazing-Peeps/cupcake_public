package cupcake.databases.mappers.tables;

import cupcake.databases.entities.tables.Order;
import cupcake.databases.exceptions.DatabaseException;
import cupcake.databases.exceptions.NoIdKeyReturnedException;
import cupcake.databases.exceptions.UnexpectedResultDbException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public final class OrderMapper implements TemplateDtoCreator
{
    
    private static final OrderMapper DTO_CREATOR = new OrderMapper();
    
    private OrderMapper()
    {
        
    }
    
    
    
    public static int create( Order order ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException
    {
        if ( order.getStatusId() == null ) {
            order.setStatusId( StatusMapper.DEFAULT_ID );
        }
        
        String sql = "insert into public.order (user_id, date_ordered, date_ready, status_id) values (?, ?, ?, ?)";
        
        Object[] parametersForSql = new Object[ 4 ];
        parametersForSql[ 0 ] = order.getUserId();
        parametersForSql[ 1 ] = order.getDateOrdered();
        parametersForSql[ 2 ] = order.getDateReady();
        parametersForSql[ 3 ] = order.getStatusId();
        
        
        return TemplateSharedCrud.create( sql, order, parametersForSql, DTO_CREATOR );
    }
    
    public static Map< Integer, Order > readAll() throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.order " +
                "ORDER BY " +
                "   order_id;";
        
        
        return ( Map< Integer, Order > ) TemplateSharedCrud.readAll( sql, DTO_CREATOR );
    }
    
    public static Map< Integer, Order > readAllByUserId( int userId ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.order " +
                "WHERE " +
                "   user_id = ? " +
                "ORDER BY " +
                "   order_id;";
        
        
        return ( Map< Integer, Order > ) TemplateSharedCrud.readAll( sql, userId, DTO_CREATOR );
    }
    
    public static Map< Integer, Order > readAllByStatusId( int statusId ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.order " +
                "WHERE " +
                "   status_id = ? " +
                "ORDER BY " +
                "   order_id;";
        
        
        return ( Map< Integer, Order > ) TemplateSharedCrud.readAll( sql, statusId, DTO_CREATOR );
    }
    
    public static Order readSingle( Integer id ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.order " +
                "WHERE " +
                "   order_id = ?;";
        
        return ( Order ) TemplateSharedCrud.readSingle( sql, id, DTO_CREATOR );
    }
    
    public static int update( Order order ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql = "update public.order set user_id=?, date_ordered=?, date_ready=?, status_id=? where order_id=? ";
        
        Object[] parametersForSql = new Object[ 5 ];
        parametersForSql[ 0 ] = order.getUserId();
        parametersForSql[ 1 ] = order.getDateOrdered();
        parametersForSql[ 2 ] = order.getDateReady();
        parametersForSql[ 3 ] = order.getStatusId();
        parametersForSql[ 4 ] = order.getOrderId();
        
        
        return TemplateSharedCrud.update( sql, order, parametersForSql );
    }
    
    public static int delete( Integer id ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql =
                "DELETE FROM public.order " +
                "WHERE order_id = ?;";
        
        return TemplateSharedCrud.delete( sql, "order", id );
    }
    
    
    
    
    
    
    
    
    @Override
    public Object createDto( ResultSet rs ) throws SQLException
    {
        Order order;
        
        order = new Order();
        order.setOrderId( rs.getInt( "order_id" ) );
        order.setUserId( rs.getInt( "user_id" ) );
        order.setDateOrdered( rs.getDate( "date_ordered" ) );
        order.setDateReady( rs.getDate( "date_ready" ) );
        order.setStatusId( rs.getInt( "status_id" ) );
        
        return order;
    }
    
    @Override
    public Map< Integer, ? > createDtoMultiple( ResultSet rs ) throws SQLException
    {
        Map< Integer, Order > ordersMap = new LinkedHashMap<>();
        Order order;
        
        while ( rs.next() ) {
            order = new Order();
            order.setOrderId( rs.getInt( "order_id" ) );
            order.setUserId( rs.getInt( "user_id" ) );
            order.setDateOrdered( rs.getDate( "date_ordered" ) );
            order.setDateReady( rs.getDate( "date_ready" ) );
            order.setStatusId( rs.getInt( "status_id" ) );
            
            ordersMap.put( order.getOrderId(), order );
        }
        
        return ordersMap;
    }
    
    @Override
    public Object setId( Object entity, Integer id )
    {
        Order order = ( Order ) entity;
        order.setOrderId( id );
        return order; //Which is the same as returning entity
    }
    
    
    
}