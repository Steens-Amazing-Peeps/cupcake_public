package cupcake.databases.services.users;

import cupcake.constants.CtxGlobalAttributes;
import cupcake.databases.entities.tables.User;
import cupcake.databases.exceptions.DatabaseException;
import cupcake.databases.exceptions.NoIdKeyReturnedException;
import cupcake.databases.exceptions.UnexpectedResultDbException;
import cupcake.databases.exceptions.WebInvalidInputException;
import cupcake.databases.mappers.tables.UserMapper;
import cupcake.languages.english.Lang_Service;

import java.util.Map;
import java.util.Objects;

public class CreateAccountService
{
    
    public static User createAccount( String email, String password, String passwordAgain ) throws DatabaseException, WebInvalidInputException, UnexpectedResultDbException, NoIdKeyReturnedException
    {
        return createAccount( email, password, passwordAgain, null );
    }
    
    public static User createAccount( String email, String password, String passwordAgain, Integer roleId ) throws DatabaseException, WebInvalidInputException, UnexpectedResultDbException, NoIdKeyReturnedException
    {
        if ( !validEmail( email ) ) {
            throw new WebInvalidInputException( Lang_Service.loginErrorUser_invalidAttemptedEmail( email ) );
        }
        
        if ( !Objects.equals( password, passwordAgain ) ) {
            throw new WebInvalidInputException( Lang_Service.createErrorUser_invalidRepeatPassword() );
        }
        
        if ( password.length() > UserMapper.PASSWORD_MAX_LENGTH ) {
            throw new WebInvalidInputException( Lang_Service.createErrorUser_invalidLongPassword() );
        }
        
        if ( password.length() < UserMapper.PASSWORD_MIN_LENGTH ) {
            throw new WebInvalidInputException( Lang_Service.createErrorUser_invalidShortPassword() );
        }
        
        Map< Integer, User > singleUserMap = UserMapper.readAllByEmail( email );
        
        if ( !singleUserMap.isEmpty() ) {
            throw new WebInvalidInputException( Lang_Service.loginErrorUser_invalidTakenEmail( email ) );
        }
        
        if ( singleUserMap.size() > 1 ) {
            throw new UnexpectedResultDbException( Lang_Service.loginErrorUser_multipleAccountsWithEmail( email ), Lang_Service.createErrorSys_multipleAccountsWithEmail( email ) );
        }
        
        User user = new User();
        user.setEmail( email );
        user.setPassword( password );
        user.setRoleId( roleId );
        
        UserMapper.create( user );
        
        CtxGlobalAttributes.USER_MAP.put( user.getUserId(), user );
        
        return user;
    }
    
    private static boolean validEmail( String email ) //TODO: Make this not suck
    {
        if ( email.contains( "@" ) && email.contains( "." ) ) {
            return true;
        }
        return false;
    }
    
}
