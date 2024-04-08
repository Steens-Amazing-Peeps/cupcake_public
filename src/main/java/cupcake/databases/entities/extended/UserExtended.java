package cupcake.databases.entities.extended;

import cupcake.constants.CtxGlobalAttributes;
import cupcake.databases.entities.tables.User;
import cupcake.databases.exceptions.WebInvalidInputException;
import cupcake.util.PriceInOereAndDkk;

import java.util.*;

public class UserExtended extends User
{
    
    
    private PriceInOereAndDkk sum;
    private final Map< Integer, OrderExtended > ordersExtended = new LinkedHashMap<>();
    
    
    public UserExtended()
    {
    }
    
    public UserExtended( User user )
    {
        if ( user != null ) {
            this.setUserId( user.getUserId() );
            this.setEmail( user.getEmail() );
            this.setPassword( user.getPassword() );
            this.setRoleId( user.getRoleId() );
        }
    }
    
    public UserExtended( User user, Map< Integer, OrderExtended > orderExtendedMap )
    {
        this( user );
        
        if ( orderExtendedMap != null ) {
            
            for ( Map.Entry< Integer, OrderExtended > orderExtendedEntry : orderExtendedMap.entrySet() ) {
                if ( Objects.equals( orderExtendedEntry.getValue().getUserId(), this.getUserId() ) ) {
                    this.ordersExtended.put( orderExtendedEntry.getKey(), orderExtendedEntry.getValue() );
                }
            }
            
            Set<Integer> ordersExtendedKeyset = this.ordersExtended.keySet();
            Set<Integer> orderExtendedMapKeyset = orderExtendedMap.keySet();
            
            orderExtendedMapKeyset.removeAll( ordersExtendedKeyset );

            
            
        }
    }
    
    
    
    public Map< Integer, OrderExtended > getOrdersExtended()
    {
        return this.ordersExtended;
    }
    
    public OrderExtended addOrderExtended( OrderExtended orderExtended )
    {
        if ( orderExtended == null ) {
            return null;
        }
        
        this.ordersExtended.put( orderExtended.getOrderId(), orderExtended );
        return orderExtended;
        
        
    }
    
    public Integer calcSum() throws WebInvalidInputException
    {
        Integer priceInOere = 0;
        
        
        for ( OrderExtended orderExtended : this.ordersExtended.values() ) {
            priceInOere = priceInOere + orderExtended.calcSum();
        }
        
        this.sum = new PriceInOereAndDkk( priceInOere );
        return priceInOere;
    }
    
    public PriceInOereAndDkk getSum()
    {
        return this.sum;
    }
    
    public String getMeta()  //TODO: Placeholder, use table in html instead
    {
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append( "Bruger Id: " );
        stringBuilder.append( this.getUserId() );
        
        stringBuilder.append( " - Email: " );
        stringBuilder.append( this.getEmail() );
        
        stringBuilder.append( " - Rolle: " );
        stringBuilder.append( CtxGlobalAttributes.ROLE_MAP.get( this.getRoleId() ).getRole() );
        
        try {
            stringBuilder.append( " - Penge Brugt I alt: " );
            stringBuilder.append( this.getString() );
        } catch ( WebInvalidInputException ignored ) {
        
        }
        
        stringBuilder.append( System.lineSeparator() );
        
        return stringBuilder.toString();
    }
    
    public String getContent()  //TODO: Placeholder, use table in html instead
    {
        StringBuilder stringBuilder = new StringBuilder();
        
        for ( OrderExtended orderExtended : this.ordersExtended.values() ) {
            stringBuilder.append( "        " );
            stringBuilder.append( orderExtended.getMeta() );
        }
        
        stringBuilder.append( System.lineSeparator() );
        
        return stringBuilder.toString();
    }
    
    public String getString() throws WebInvalidInputException
    {
        if ( this.sum == null ) {
            this.calcSum();
        }
        
        return this.sum.toString();
    }
    
    
}
