package cupcake.databases.mappers.tables;

import cupcake.databases.entities.tables.Role;
import cupcake.databases.exceptions.DatabaseException;
import cupcake.databases.exceptions.NoIdKeyReturnedException;
import cupcake.databases.exceptions.UnexpectedResultDbException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public final class RoleMapper implements TemplateDtoCreator
{
    public static final int ADMIN_ID = 2;
    private static final RoleMapper DTO_CREATOR = new RoleMapper();
    
    private RoleMapper()
    {
        
    }
    
    
    
    public static int create( Role role ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException
    {
        String sql = "insert into public.role (role) values (?)";
        
        Object[] parametersForSql = new Object[ 1 ];
        parametersForSql[ 0 ] = role.getRole();
        
        return TemplateSharedCrud.create( sql, role, parametersForSql, DTO_CREATOR );
    }
    
    public static Map< Integer, Role > readAll() throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.role " +
                "ORDER BY " +
                "   role_id;";
        
        
        return ( Map< Integer, Role > ) TemplateSharedCrud.readAll( sql, DTO_CREATOR );
    }
    
    public static Role readSingle( Integer id ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.role " +
                "WHERE " +
                "   role_id = ?;";
        
        return ( Role ) TemplateSharedCrud.readSingle( sql, id, DTO_CREATOR );
    }
    
    public static int update( Role role ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql = "update public.role set role=? where role_id=? ";
        
        Object[] parametersForSql = new Object[ 2 ];
        parametersForSql[ 0 ] = role.getRole();
        parametersForSql[ 1 ] = role.getRoleId();
        
        
        return TemplateSharedCrud.update( sql, role, parametersForSql );
    }
    
    public static int delete( Integer id ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql =
                "DELETE FROM public.role " +
                "WHERE role_id = ?;";
        
        return TemplateSharedCrud.delete( sql, "role", id );
    }
    
    
    
    
    
    
    
    
    @Override
    public Object createDto( ResultSet rs ) throws SQLException
    {
        Role role;
        
        role = new Role();
        role.setRoleId( rs.getInt( "role_id" ) );
        role.setRole( rs.getString( "role" ) );
        
        return role;
        
    }
    
    @Override
    public Map< Integer, ? > createDtoMultiple( ResultSet rs ) throws SQLException
    {
        Map< Integer, Role > rolesMap = new LinkedHashMap<>();
        Role role;
        
        while ( rs.next() ) {
            role = new Role();
            role.setRoleId( rs.getInt( "role_id" ) );
            role.setRole( rs.getString( "role" ) );
            
            rolesMap.put( role.getRoleId(), role );
        }
        
        return rolesMap;
    }
    
    @Override
    public Object setId( Object entity, Integer id )
    {
        Role role = ( Role ) entity;
        role.setRoleId( id );
        return role; //Which is the same as returning entity
    }
    
    
    
    
}