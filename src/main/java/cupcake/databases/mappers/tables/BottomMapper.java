package cupcake.databases.mappers.tables;

import cupcake.databases.entities.tables.Bottom;
import cupcake.databases.exceptions.DatabaseException;
import cupcake.databases.exceptions.NoIdKeyReturnedException;
import cupcake.databases.exceptions.UnexpectedResultDbException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public final class BottomMapper implements TemplateDtoCreator
{
    
    private static final BottomMapper DTO_CREATOR = new BottomMapper();
    
    private BottomMapper()
    {
    
    }
    
    
    
    public static int create( Bottom bottom ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException
    {
        String sql = "insert into public.bottom (taste, price_oere) values (?, ?)";
        
        Object[] parametersForSql = new Object[ 2 ];
        parametersForSql[ 0 ] = bottom.getTaste();
        parametersForSql[ 1 ] = bottom.getPriceOere();
        
        return TemplateSharedCrud.create( sql, bottom, parametersForSql, DTO_CREATOR );
    }
    
    public static Map< Integer, Bottom > readAll() throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.bottom " +
                "ORDER BY " +
                "   bottom_id;";
        
        
        return ( Map< Integer, Bottom > ) TemplateSharedCrud.readAll( sql, DTO_CREATOR );
    }
    
    public static Map< Integer, Bottom > readAllNotObsolete() throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.bottom " +
                "WHERE " +
                "   obsolete = FALSE"+
                "ORDER BY " +
                "   bottom_id;";
        
        
        return ( Map< Integer, Bottom > ) TemplateSharedCrud.readAll( sql, DTO_CREATOR );
    }
    
    public static Bottom readSingle( Integer id ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.bottom " +
                "WHERE " +
                "   bottom_id = ?;";
        
        return ( Bottom ) TemplateSharedCrud.readSingle( sql, id, DTO_CREATOR );
    }
    
    public static int update( Bottom bottom ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql = "update public.bottom set user_id=?, balance_oere=?, obsolete=? where bottom_id=? ";
        
        Object[] parametersForSql = new Object[ 4 ];
        parametersForSql[ 0 ] = bottom.getTaste();
        parametersForSql[ 1 ] = bottom.getPriceOere();
        parametersForSql[ 2 ] = bottom.getObsolete();
        parametersForSql[ 3 ] = bottom.getBottomId();
        
        
        return TemplateSharedCrud.update( sql, bottom, parametersForSql );
    }
    
    public static int updateObsoleteAllActiveWithThisTaste( Bottom bottom ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql = "update public.bottom set obsolete=TRUE where taste=? AND obsolete=FALSE";
        
        Object[] parametersForSql = new Object[ 1 ];
        parametersForSql[ 0 ] = bottom.getTaste();
        
        
        return TemplateSharedCrud.update( sql, bottom, parametersForSql );
    }
    
    public static int delete( Integer id ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql =
                "DELETE FROM public.bottom " +
                "WHERE bottom_id = ?;";
        
        return TemplateSharedCrud.delete( sql, "bottom", id );
    }
    
    
    
    
    
    
    
    
    @Override
    public Object createDto( ResultSet rs ) throws SQLException
    {
        Bottom bottom;
        
        bottom = new Bottom();
        bottom.setBottomId( rs.getInt( "bottom_id" ) );
        bottom.setTaste( rs.getString( "taste" ) );
        bottom.setPriceOere( rs.getInt( "price_oere" ) );
        bottom.setObsolete( rs.getBoolean( "obsolete" ) );
        
        return bottom;
        
    }
    
    @Override
    public Map< Integer, ? > createDtoMultiple( ResultSet rs ) throws SQLException
    {
        Map< Integer, Bottom > bottomsMap = new LinkedHashMap<>();
        Bottom bottom;
        
        while ( rs.next() ) {
            bottom = new Bottom();
            bottom.setBottomId( rs.getInt( "bottom_id" ) );
            bottom.setTaste( rs.getString( "taste" ) );
            bottom.setPriceOere( rs.getInt( "price_oere" ) );
            bottom.setObsolete( rs.getBoolean( "obsolete" ) );
            
            bottomsMap.put( bottom.getBottomId(), bottom );
        }
        
        return bottomsMap;
    }
    
    @Override
    public Object setId( Object entity, Integer id )
    {
        Bottom bottom = ( Bottom ) entity;
        bottom.setBottomId( id );
        return bottom; //Which is the same as returning entity
    }
    

}