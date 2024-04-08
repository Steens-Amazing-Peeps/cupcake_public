package cupcake.databases.persistence;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * Singleton
 * Allows you to get connection from the private connectionPool no matter where you are
 *
 * Does not allow you to actually close the connection or do anything else
 *
 * You can't even cast to fix it, I know you thought about it!
 *
 */
public final class ConnectionPoolAccess implements ConnectionPoolAccessIf
{
    private static ConnectionPoolAccessIf connectionPool = null;
    
    private ConnectionPoolAccess()
    {
    }
    
    public static ConnectionPoolAccessIf setConnectionPoolAccess(ConnectionPoolAccessIf connectionPoolInput) {
        if ( connectionPool == null ) {
            connectionPool = connectionPoolInput;
            return new ConnectionPoolAccess();
        }
        return null;
    }
    
    @Override
    public synchronized Connection getConnection() throws SQLException
    {
        return connectionPool.getConnection();
    }
    
}
