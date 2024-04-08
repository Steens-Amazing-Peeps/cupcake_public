package cupcake.databases.services.users;

import cupcake.databases.entities.tables.User;
import cupcake.databases.exceptions.DatabaseException;
import cupcake.databases.exceptions.UnexpectedResultDbException;
import cupcake.databases.exceptions.WebInvalidInputException;
import cupcake.databases.mappers.tables.UserMapper;
import cupcake.languages.english.Lang_Service;

import java.util.Map;
import java.util.Objects;

public class LoginService
{
    
    
    public static User login( String email, String password ) throws DatabaseException, WebInvalidInputException, UnexpectedResultDbException
    {
        Map< Integer, User > singleUserMap = UserMapper.readAllByEmail( email );
        
        if ( singleUserMap.isEmpty() ) {
            throw new WebInvalidInputException( Lang_Service.loginErrorUser_invalidEmail( email ) );
        }
        
        if ( singleUserMap.size() > 1 ) {
            throw new UnexpectedResultDbException( Lang_Service.loginErrorUser_multipleAccountsWithEmail( email ), Lang_Service.loginErrorSys_multipleAccountsWithEmail( email ) );
        }
        
        User user = null;
        for ( User userInMap : singleUserMap.values() ) {
            user = userInMap;
        }
        
        if ( !Objects.equals( user.getPassword(), password ) ) {
            throw new WebInvalidInputException( Lang_Service.loginErrorUser_invalidPassword() );
        }
        
        return user;
    }
    
    
    
}
