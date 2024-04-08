package cupcake.databases.services.users;



import cupcake.databases.entities.extended.OrderExtended;
import cupcake.databases.entities.extended.OrderLineExtended;
import cupcake.databases.entities.tables.User;
import cupcake.databases.entities.tables.Wallet;
import cupcake.databases.exceptions.DatabaseException;
import cupcake.databases.exceptions.NoIdKeyReturnedException;
import cupcake.databases.exceptions.UnexpectedResultDbException;
import cupcake.databases.exceptions.WebInvalidInputException;
import cupcake.databases.mappers.tables.OrderLineMapper;
import cupcake.databases.mappers.tables.OrderMapper;
import cupcake.databases.mappers.tables.WalletMapper;

import java.util.Date;
import java.util.Map;

public class CartService
{
    
    public static OrderExtended buy( User currentUser, OrderExtended orderExtended ) throws WebInvalidInputException, DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException
    {
        if ( currentUser == null ) {
            throw new WebInvalidInputException( "Log in eller lav en konto for at købe dine valgte cupcakes" );
        }
        
        if ( orderExtended == null || orderExtended.getOrderLinesExtended().isEmpty() ) {
            throw new WebInvalidInputException( "Du kan ikke købe en tom kurv!" );
        }
        
        //Fixup orderExtended with its missing info
        orderExtended.setUserId( currentUser.getUserId() );
        orderExtended.setDateOrdered( new Date() );
        
        Map< Integer, Wallet > wallets = WalletMapper.readAllByUserId( currentUser.getUserId() );
        
        Wallet wallet = null;
        
        if ( wallets.isEmpty() ) { //Has no wallet :(
            //Ok fam, we make one for ya
            wallet = new Wallet();
            wallet.setUserId( currentUser.getUserId() );
            
            WalletMapper.create( wallet );
            
        } else {
            
            for ( Wallet walletOne : wallets.values() ) {
                wallet = walletOne;
                break; //TODO: Support multiple wallets better
            }
            
        }
        
        int priceToPay = orderExtended.calcSum();
        int moneyInWallet = wallet.getBalanceOere();
        
        if ( moneyInWallet < priceToPay ) {
            throw new DatabaseException( "Du har ikke råd til at købe denne kurv" );
        }
        
        //ADD ORDER TO DB
        OrderMapper.create( orderExtended );
        
        //Now has an order ID we can set with
        
        //ADD ORDERLINES TO DB
        for ( OrderLineExtended orderLineExtended : orderExtended.getOrderLinesExtended() ) {
            orderLineExtended.setOrderId( orderExtended.getOrderId() );
            OrderLineMapper.create( orderLineExtended );
        }
        
        //TIME TO PAY
        moneyInWallet = moneyInWallet - priceToPay;
        
        wallet.setBalanceOere( moneyInWallet );
        
        //ADD POOR WALLET TO DB
        WalletMapper.update( wallet );
        
        return orderExtended;
    }
    
    public static OrderExtended deleteOrderLine( OrderExtended currentOrder, String orderLineIndexAsString ) throws WebInvalidInputException
    {
        if ( currentOrder == null ) {
            throw new WebInvalidInputException( "No Cart Found", "CART WAS NOT FOUND BUT SHOULD ALWAYS BE FOUND" );
        }
        
        if ( orderLineIndexAsString == null ) {
            throw new WebInvalidInputException( "No cupcake found to delete", "Orderline not found for deletion" );
        }
        
        int orderLineIndex;
        
        try {
            orderLineIndex = Integer.parseInt( orderLineIndexAsString );
            
        } catch ( NumberFormatException e ) {
            throw new WebInvalidInputException( "Not a valid number for selected cupcake", "CUPCAKE INDEX WAS NOT A NUMBER! " + e.getMessage() );
        }
        
        try {
            currentOrder.getOrderLinesExtended().remove( orderLineIndex );
            
        } catch ( ArrayIndexOutOfBoundsException e ) {
            throw new WebInvalidInputException( "Cupcake not ordered, could not remove", "CUPCAKE INDEX OUT OF BOUNDS!" + e.getMessage() );
        }
        
        currentOrder.calcSum();
        
        
        return currentOrder;
    }
    
}
