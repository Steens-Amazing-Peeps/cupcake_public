package cupcake.databases.mappers.tables;

import cupcake.databases.entities.tables.OrderLine;
import cupcake.databases.exceptions.DatabaseException;
import cupcake.databases.exceptions.NoIdKeyReturnedException;
import cupcake.databases.exceptions.UnexpectedResultDbException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public final class OrderLineMapper implements TemplateDtoCreator
{
    
    private static final OrderLineMapper DTO_CREATOR = new OrderLineMapper();
    
    private OrderLineMapper()
    {
        
    }
    
    
    
    public static int create( OrderLine orderLine ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException
    {
        String sql = "insert into public.order_line (order_id, top_id, bottom_id, amount) values (?, ?, ?, ?)";
        
        Object[] parametersForSql = new Object[ 4 ];
        parametersForSql[ 0 ] = orderLine.getOrderId();
        parametersForSql[ 1 ] = orderLine.getTopId();
        parametersForSql[ 2 ] = orderLine.getBottomId();
        parametersForSql[ 3 ] = orderLine.getAmount();
        
        return TemplateSharedCrud.create( sql, orderLine, parametersForSql, DTO_CREATOR );
    }
    
    public static Map< Integer, OrderLine > readAll() throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.order_line " +
                "ORDER BY " +
                "   order_line_id;";
        
        
        return ( Map< Integer, OrderLine > ) TemplateSharedCrud.readAll( sql, DTO_CREATOR );
    }
    
    public static Map< Integer, OrderLine > readAllByOrderId( int orderId ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.order_line " +
                "WHERE " +
                "   order_id = ? " +
                "ORDER BY " +
                "   order_line_id;";
        
        
        return ( Map< Integer, OrderLine > ) TemplateSharedCrud.readAll( sql, orderId, DTO_CREATOR );
    }
    
    public static Map< Integer, OrderLine > readAllByTopId( int topId ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.order_line " +
                "WHERE " +
                "   top_id = ? " +
                "ORDER BY " +
                "   order_line_id;";
        
        
        return ( Map< Integer, OrderLine > ) TemplateSharedCrud.readAll( sql, topId, DTO_CREATOR );
    }
    
    public static Map< Integer, OrderLine > readAllByBottomId( int bottomId ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.order_line " +
                "WHERE " +
                "   bottom_id = ? " +
                "ORDER BY " +
                "   order_line_id;";
        
        
        return ( Map< Integer, OrderLine > ) TemplateSharedCrud.readAll( sql, bottomId, DTO_CREATOR );
    }
    
    public static OrderLine readSingle( Integer id ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.order_line " +
                "WHERE " +
                "   order_line_id = ?;";
        
        return ( OrderLine ) TemplateSharedCrud.readSingle( sql, id, DTO_CREATOR );
    }
    
    public static int update( OrderLine orderLine ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql = "update public.order_line set order_id=?, top_id=?, bottom_id=?, amount=? where order_line_id=? ";
        
        Object[] parametersForSql = new Object[ 5 ];
        parametersForSql[ 0 ] = orderLine.getOrderId();
        parametersForSql[ 1 ] = orderLine.getTopId();
        parametersForSql[ 2 ] = orderLine.getBottomId();
        parametersForSql[ 3 ] = orderLine.getAmount();
        parametersForSql[ 4 ] = orderLine.getOrderLineId();
        
        
        return TemplateSharedCrud.update( sql, orderLine, parametersForSql );
    }
    
    public static int delete( Integer id ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql =
                "DELETE FROM public.order_line " +
                "WHERE order_line_id = ?;";
        
        return TemplateSharedCrud.delete( sql, "order_line", id );
    }
    
    public static int deleteByOrder( Integer id ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql =
                "DELETE FROM public.order_line " +
                "WHERE order_id = ?;";
        
        return TemplateSharedCrud.delete( sql, "order_line", id );
    }
    
    
    
    
    
    
    
    
    @Override
    public Object createDto( ResultSet rs ) throws SQLException
    {
        OrderLine orderLine;
        
        orderLine = new OrderLine();
        orderLine.setOrderLineId( rs.getInt( "order_line_id" ) );
        orderLine.setOrderId( rs.getInt( "order_id" ) );
        orderLine.setTopId( rs.getInt( "top_id" ) );
        orderLine.setBottomId( rs.getInt( "bottom_id" ) );
        orderLine.setAmount( rs.getInt( "amount" ) );
        
        return orderLine;
    }
    
    @Override
    public Map< Integer, ? > createDtoMultiple( ResultSet rs ) throws SQLException
    {
        Map< Integer, OrderLine > orderLinesMap = new LinkedHashMap<>();
        OrderLine orderLine;
        
        while ( rs.next() ) {
            orderLine = new OrderLine();
            orderLine.setOrderLineId( rs.getInt( "order_line_id" ) );
            orderLine.setOrderId( rs.getInt( "order_id" ) );
            orderLine.setTopId( rs.getInt( "top_id" ) );
            orderLine.setBottomId( rs.getInt( "bottom_id" ) );
            orderLine.setAmount( rs.getInt( "amount" ) );
            
            orderLinesMap.put( orderLine.getOrderLineId(), orderLine );
        }
        
        return orderLinesMap;
    }
    
    @Override
    public Object setId( Object entity, Integer id )
    {
        OrderLine orderLine = ( OrderLine ) entity;
        orderLine.setOrderLineId( id );
        return orderLine; //Which is the same as returning entity
    }
    
}