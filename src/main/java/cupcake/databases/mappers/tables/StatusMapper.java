package cupcake.databases.mappers.tables;

import cupcake.databases.entities.tables.Status;
import cupcake.databases.exceptions.DatabaseException;
import cupcake.databases.exceptions.NoIdKeyReturnedException;
import cupcake.databases.exceptions.UnexpectedResultDbException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public final class StatusMapper implements TemplateDtoCreator
{
    public static final Integer DEFAULT_ID = 1;
    public static final Integer DONE_ID = 7;
    private static final StatusMapper DTO_CREATOR = new StatusMapper();
    
    private StatusMapper()
    {
        
    }
    
    
    
    public static int create( Status status ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException
    {
        String sql = "insert into public.status (status) values (?)";
        
        Object[] parametersForSql = new Object[ 1 ];
        parametersForSql[ 0 ] = status.getStatus();
        
        return TemplateSharedCrud.create( sql, status, parametersForSql, DTO_CREATOR );
    }
    
    public static Map< Integer, Status > readAll() throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.status " +
                "ORDER BY " +
                "   status_id;";
        
        
        return ( Map< Integer, Status > ) TemplateSharedCrud.readAll( sql, DTO_CREATOR );
    }
    
    public static Status readSingle( Integer id ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.status " +
                "WHERE " +
                "   status_id = ?;";
        
        return ( Status ) TemplateSharedCrud.readSingle( sql, id, DTO_CREATOR );
    }
    
    public static int update( Status status ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql = "update public.status set status=? where status_id=? ";
        
        Object[] parametersForSql = new Object[ 2 ];
        parametersForSql[ 0 ] = status.getStatus();
        parametersForSql[ 1 ] = status.getStatusId();
        
        
        return TemplateSharedCrud.update( sql, status, parametersForSql );
    }
    
    public static int delete( Integer id ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql =
                "DELETE FROM public.status " +
                "WHERE status_id = ?;";
        
        return TemplateSharedCrud.delete( sql, "status", id );
    }
    
    
    
    
    
    
    
    
    @Override
    public Object createDto( ResultSet rs ) throws SQLException
    {
        Status status;
        
        status = new Status();
        status.setStatusId( rs.getInt( "status_id" ) );
        status.setStatus( rs.getString( "status" ) );
        
        return status;
        
    }
    
    @Override
    public Map< Integer, ? > createDtoMultiple( ResultSet rs ) throws SQLException
    {
        Map< Integer, Status > statussMap = new LinkedHashMap<>();
        Status status;
        
        while ( rs.next() ) {
            status = new Status();
            status.setStatusId( rs.getInt( "status_id" ) );
            status.setStatus( rs.getString( "status" ) );
            
            statussMap.put( status.getStatusId(), status );
        }
        
        return statussMap;
    }
    
    @Override
    public Object setId( Object entity, Integer id )
    {
        Status status = ( Status ) entity;
        status.setStatusId( id );
        return status; //Which is the same as returning entity
    }
    
    
    
    
}