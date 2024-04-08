package cupcake.databases.mappers.tables;


import cupcake.databases.entities.tables.Top;
import cupcake.databases.exceptions.DatabaseException;
import cupcake.databases.exceptions.NoIdKeyReturnedException;
import cupcake.databases.exceptions.UnexpectedResultDbException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public final class TopMapper implements TemplateDtoCreator
{
    
    private static final TopMapper DTO_CREATOR = new TopMapper();
    
    private TopMapper()
    {
        
    }
    
    
    
    public static int create( Top top ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException
    {
        String sql = "insert into public.top (taste, price_oere) values (?, ?)";
        
        Object[] parametersForSql = new Object[ 2 ];
        parametersForSql[ 0 ] = top.getTaste();
        parametersForSql[ 1 ] = top.getPriceOere();
        
        return TemplateSharedCrud.create( sql, top, parametersForSql, DTO_CREATOR );
    }
    
    public static Map< Integer, Top > readAll() throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.top " +
                "ORDER BY " +
                "   top_id;";
        
        
        return ( Map< Integer, Top > ) TemplateSharedCrud.readAll( sql, DTO_CREATOR );
    }
    
    public static Map< Integer, Top > readAllNotObsolete() throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.top " +
                "WHERE " +
                "   obsolete = FALSE"+
                "ORDER BY " +
                "   top_id;";
        
        
        return ( Map< Integer, Top > ) TemplateSharedCrud.readAll( sql, DTO_CREATOR );
    }
    
    public static Top readSingle( Integer id ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.top " +
                "WHERE " +
                "   top_id = ?;";
        
        return ( Top ) TemplateSharedCrud.readSingle( sql, id, DTO_CREATOR );
    }
    
    public static int update( Top top ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql = "update public.top set user_id=?, balance_oere=?, obsolete=? where top_id=? ";
        
        Object[] parametersForSql = new Object[ 4 ];
        parametersForSql[ 0 ] = top.getTaste();
        parametersForSql[ 1 ] = top.getPriceOere();
        parametersForSql[ 2 ] = top.getObsolete();
        parametersForSql[ 3 ] = top.getTopId();
        
        
        return TemplateSharedCrud.update( sql, top, parametersForSql );
    }
    
    public static int updateObsoleteAllActiveWithThisTaste( Top top ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql = "update public.top set obsolete=TRUE where taste=? AND obsolete=FALSE";
        
        Object[] parametersForSql = new Object[ 1 ];
        parametersForSql[ 0 ] = top.getTaste();
        
        
        return TemplateSharedCrud.update( sql, top, parametersForSql );
    }
    
    public static int delete( Integer id ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql =
                "DELETE FROM public.top " +
                "WHERE top_id = ?;";
        
        return TemplateSharedCrud.delete( sql, "top", id );
    }
    
    
    
    
    
    
    
    
    @Override
    public Object createDto( ResultSet rs ) throws SQLException
    {
        Top top;
        
        top = new Top();
        top.setTopId( rs.getInt( "top_id" ) );
        top.setTaste( rs.getString( "taste" ) );
        top.setPriceOere( rs.getInt( "price_oere" ) );
        top.setObsolete( rs.getBoolean( "obsolete" ) );
        
        return top;
        
    }
    
    @Override
    public Map< Integer, ? > createDtoMultiple( ResultSet rs ) throws SQLException
    {
        Map< Integer, Top > topsMap = new LinkedHashMap<>();
        Top top;
        
        while ( rs.next() ) {
            top = new Top();
            top.setTopId( rs.getInt( "top_id" ) );
            top.setTaste( rs.getString( "taste" ) );
            top.setPriceOere( rs.getInt( "price_oere" ) );
            top.setObsolete( rs.getBoolean( "obsolete" ) );
            
            topsMap.put( top.getTopId(), top );
        }
        
        return topsMap;
    }
    
    @Override
    public Object setId( Object entity, Integer id )
    {
        Top top = ( Top ) entity;
        top.setTopId( id );
        return top; //Which is the same as returning entity
    }
    
}