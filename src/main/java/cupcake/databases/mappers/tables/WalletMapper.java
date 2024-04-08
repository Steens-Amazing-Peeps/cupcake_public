package cupcake.databases.mappers.tables;

import cupcake.databases.entities.tables.Wallet;
import cupcake.databases.exceptions.DatabaseException;
import cupcake.databases.exceptions.NoIdKeyReturnedException;
import cupcake.databases.exceptions.UnexpectedResultDbException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public final class WalletMapper implements TemplateDtoCreator
{
    public static final int DEFAULT_WALLET_MONEY_IN_OERE = 50000; //500 Kr.
    private static final WalletMapper DTO_CREATOR = new WalletMapper();
    
    private WalletMapper()
    {
        
    }
    
    
    
    public static int create( Wallet wallet ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException
    {
        if ( wallet.getBalanceOere() == null ) {
            wallet.setBalanceOere( WalletMapper.DEFAULT_WALLET_MONEY_IN_OERE );
        }
        
        String sql = "insert into public.wallet (user_id, balance_oere) values (?, ?)";
        
        Object[] parametersForSql = new Object[ 2 ];
        parametersForSql[ 0 ] = wallet.getUserId();
        parametersForSql[ 1 ] = wallet.getBalanceOere();
        
        return TemplateSharedCrud.create( sql, wallet, parametersForSql, DTO_CREATOR );
    }
    
    public static Map< Integer, Wallet > readAll() throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.wallet " +
                "ORDER BY " +
                "   wallet_id;";
        
        
        return ( Map< Integer, Wallet > ) TemplateSharedCrud.readAll( sql, DTO_CREATOR );
    }
    
    public static Map< Integer, Wallet > readAllByUserId(int userId) throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.wallet " +
                "WHERE "+
                "   user_id = ? " +
                "ORDER BY " +
                "   wallet_id;";
        
        
        return ( Map< Integer, Wallet > ) TemplateSharedCrud.readAll( sql, userId, DTO_CREATOR );
    }
    
    public static Wallet readSingle( Integer id ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.wallet " +
                "WHERE " +
                "   wallet_id = ?;";
        
        return ( Wallet ) TemplateSharedCrud.readSingle( sql, id, DTO_CREATOR );
    }
    
    public static int update( Wallet wallet ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql = "update public.wallet set user_id=?, balance_oere=? where wallet_id=? ";
        
        Object[] parametersForSql = new Object[ 3 ];
        parametersForSql[ 0 ] = wallet.getUserId();
        parametersForSql[ 1 ] = wallet.getBalanceOere();
        parametersForSql[ 2 ] = wallet.getWalletId();
        
        
        return TemplateSharedCrud.update( sql, wallet, parametersForSql );
    }
    
    public static int delete( Integer id ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql =
                "DELETE FROM public.wallet " +
                "WHERE wallet_id = ?;";
        
        return TemplateSharedCrud.delete( sql, "wallet", id );
    }
    
    
    
    
    
    
    
    
    @Override
    public Object createDto( ResultSet rs ) throws SQLException
    {
        Wallet wallet;
        
        wallet = new Wallet();
        wallet.setWalletId( rs.getInt( "wallet_id" ) );
        wallet.setUserId( rs.getInt( "user_id" ) );
        wallet.setBalanceOere( rs.getInt( "balance_oere" ) );
        
        return wallet;
        
    }
    
    @Override
    public Map< Integer, ? > createDtoMultiple( ResultSet rs ) throws SQLException
    {
        Map< Integer, Wallet > walletsMap = new LinkedHashMap<>();
        Wallet wallet;
        
        while ( rs.next() ) {
            wallet = new Wallet();
            wallet.setWalletId( rs.getInt( "wallet_id" ) );
            wallet.setUserId( rs.getInt( "user_id" ) );
            wallet.setBalanceOere( rs.getInt( "balance_oere" ) );
            
            walletsMap.put( wallet.getWalletId(), wallet );
        }
        
        return walletsMap;
    }
    
    @Override
    public Object setId( Object entity, Integer id )
    {
        Wallet wallet = ( Wallet ) entity;
        wallet.setWalletId( id );
        return wallet; //Which is the same as returning entity
    }
    
}