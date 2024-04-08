package cupcake.databases.mappers.tables;

import cupcake.databases.entities.tables.User;
import cupcake.databases.exceptions.DatabaseException;
import cupcake.databases.exceptions.NoIdKeyReturnedException;
import cupcake.databases.exceptions.UnexpectedResultDbException;

import java.sql.*;
import java.util.*;

/**
 * Singleton
 */
public final class UserMapper implements TemplateDtoCreator
{
    
    public static final int PASSWORD_MAX_LENGTH = 100;
    public static final int PASSWORD_MIN_LENGTH = 4;
    private static final int DEFAULT_USER_ROLE_ID = 1;
    private static final UserMapper DTO_CREATOR = new UserMapper();
    
    private UserMapper()
    {
    
    }
    
    
    
    public static int create( User user ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException
    {
        if ( user.getRoleId() == null ) {
            user.setRoleId( DEFAULT_USER_ROLE_ID );
        }
        
        
        String sql =
                "INSERT INTO public.user " +
                "   ( email, password, role_id) " +
                "VALUES " +
                "   (?, ?, ?);";
        
        Object[] parametersForSql = new Object[ 3 ];
        parametersForSql[ 0 ] = user.getEmail();
        parametersForSql[ 1 ] = user.getPassword();
        parametersForSql[ 2 ] = user.getRoleId();
        
        return TemplateSharedCrud.create( sql, user, parametersForSql, DTO_CREATOR );
    }
    
    public static Map< Integer, User > readAll() throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.user " +
                "ORDER BY " +
                "   user_id;";
        
        
        return ( Map< Integer, User > ) TemplateSharedCrud.readAll( sql, DTO_CREATOR );
    }
    
    public static Map< Integer, User > readAllByEmail( String email ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.user " +
                "WHERE " +
                "   email = ?;";
        
        return ( Map< Integer, User > ) TemplateSharedCrud.readAll( sql, new Object[]{ email }, DTO_CREATOR );
    }
    
    public static User readSingle( Integer id ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.user " +
                "WHERE " +
                "   user_id = ?;";
        
        return ( User ) TemplateSharedCrud.readSingle( sql, id, DTO_CREATOR );
    }
    
    
    
    public static int update( User user ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql =
                "UPDATE public.user " +
                "SET email = ?, password = ?, role_id = ? " +
                "WHERE user_id = ?;";
        
        Object[] parametersForSql = new Object[ 4 ];
        parametersForSql[ 0 ] = user.getEmail();
        parametersForSql[ 1 ] = user.getPassword();
        parametersForSql[ 2 ] = user.getRoleId();
        parametersForSql[ 3 ] = user.getUserId();
        
        
        return TemplateSharedCrud.update( sql, user, parametersForSql );
    }
    
    public static int delete( Integer id ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql =
                "DELETE FROM public.user " +
                "WHERE user_id = ?;";
        
        return TemplateSharedCrud.delete( sql, "user", id );
    }
    
    
    
    
    
    
    
    
    @Override
    public Object createDto( ResultSet rs ) throws SQLException
    {
        User user;
        
        user = new User();
        user.setUserId( rs.getInt( "user_id" ) );
        user.setEmail( rs.getString( "email" ) );
        user.setPassword( rs.getString( "password" ) );
        user.setRoleId( rs.getInt( "role_id" ) );
        
        return user;
        
    }
    
    @Override
    public Map< Integer, ? > createDtoMultiple( ResultSet rs ) throws SQLException
    {
        Map< Integer, User > usersMap = new LinkedHashMap<>();
        User user;
        
        while ( rs.next() ) {
            user = new User();
            user.setUserId( rs.getInt( "user_id" ) );
            user.setEmail( rs.getString( "email" ) );
            user.setPassword( rs.getString( "password" ) );
            user.setRoleId( rs.getInt( "role_id" ) );
            
            usersMap.put( user.getUserId(), user );
        }
        
        return usersMap;
    }
    
    @Override
    public Object setId( Object entity, Integer id )
    {
        User user = ( User ) entity;
        user.setUserId( id );
        return user; //Which is the same as returning entity
    }
    
}