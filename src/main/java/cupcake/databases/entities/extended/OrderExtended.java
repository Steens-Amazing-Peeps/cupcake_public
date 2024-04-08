package cupcake.databases.entities.extended;



import cupcake.constants.CtxGlobalAttributes;
import cupcake.databases.entities.tables.Order;
import cupcake.databases.entities.tables.OrderLine;
import cupcake.databases.exceptions.WebInvalidInputException;
import cupcake.util.PriceInOereAndDkk;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderExtended extends Order
{
    
    private PriceInOereAndDkk sum;
    private final List< OrderLineExtended > orderLinesExtended = new ArrayList<>();
    
    
    public OrderExtended()
    {
    }
    
    public OrderExtended( Order order )
    {
        if ( order != null ) {
            this.setOrderId( order.getOrderId() );
            this.setUserId( order.getUserId() );
            this.setDateOrdered( order.getDateOrdered() );
            this.setDateReady( order.getDateReady() );
            this.setStatusId( order.getStatusId() );
        }
    }
    
    public OrderExtended( Order order, Map< Integer, OrderLine > orderLineMap )
    {
        this( order );
        
        if ( orderLineMap != null ) {
            for ( OrderLine orderLine : orderLineMap.values() ) {
                this.orderLinesExtended.add( new OrderLineExtended( orderLine ) );
            }
        }
    }
    
    
    
    public List< OrderLineExtended > getOrderLinesExtended()
    {
        return this.orderLinesExtended;
    }
    
    public OrderLineExtended addToOrder( OrderLine orderLine )
    {
        if ( orderLine == null ) {
            return null;
        }
        
        OrderLineExtended orderLineExtended = new OrderLineExtended( orderLine );
        this.orderLinesExtended.add( orderLineExtended );
        return orderLineExtended;
        
        
    }
    
    public Integer calcSum() throws WebInvalidInputException
    {
        Integer priceInOere = 0;
        
        
        for ( OrderLineExtended orderLineExtended : this.orderLinesExtended ) {
            priceInOere = priceInOere + orderLineExtended.calcSum();
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
        
        stringBuilder.append( "Bestilling Id: " );
        stringBuilder.append( this.getOrderId() );
        
        stringBuilder.append( " - Dato Bestilt: " );
        stringBuilder.append( this.getDateOrdered() );
        
        stringBuilder.append( " - Dato Klar: " );
        stringBuilder.append( this.getDateReady() );
        
        stringBuilder.append( " - Status: " );
        stringBuilder.append( CtxGlobalAttributes.STATUS_MAP.get( this.getStatusId() ).getStatus() );
        
        try {
            stringBuilder.append( " - TOTAL: " );
            stringBuilder.append( this.getString() );
            
        } catch ( WebInvalidInputException ignored ) {
        
        }
        
        
        stringBuilder.append( System.lineSeparator() );
        
        return stringBuilder.toString();
    }
    
    public String getContent()  //TODO: Placeholder, use table in html instead
    {
        StringBuilder stringBuilder = new StringBuilder();
        
        for ( OrderLineExtended orderLineExtended : this.orderLinesExtended ) {
            stringBuilder.append( "        " );
            stringBuilder.append( orderLineExtended.getMeta() );
        }
        
        try {
            stringBuilder.append( " - TOTAL: " );
            stringBuilder.append( this.getString() );
            
            stringBuilder.append( System.lineSeparator() );
            
        } catch ( WebInvalidInputException ignored ) {
        
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
