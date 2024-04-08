package cupcake.databases.persistence;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Allows you to get connection from the private connectionPool no matter where you are
 *
 * Does not allow you to actually close the connection or do anything else
 *
 * ( unless you cast )
 */
public interface ConnectionPoolAccessIf
{
    
    Connection getConnection() throws SQLException;
    
}
