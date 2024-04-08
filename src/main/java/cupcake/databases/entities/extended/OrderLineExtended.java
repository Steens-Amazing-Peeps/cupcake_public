package cupcake.databases.entities.extended;

import cupcake.constants.CtxGlobalAttributes;
import cupcake.databases.entities.tables.Bottom;
import cupcake.databases.entities.tables.OrderLine;
import cupcake.databases.entities.tables.Top;
import cupcake.databases.exceptions.WebInvalidInputException;
import cupcake.util.PriceInOereAndDkk;

public class OrderLineExtended extends OrderLine
{
    
    private PriceInOereAndDkk sum;
    
    public OrderLineExtended()
    {
    }
    
    public OrderLineExtended( OrderLine orderLine )
    {
        if ( orderLine != null ) {
            this.setOrderLineId( orderLine.getOrderLineId() );
            this.setOrderId( orderLine.getOrderId() );
            this.setBottomId( orderLine.getBottomId() );
            this.setTopId( orderLine.getTopId() );
            this.setAmount( orderLine.getAmount() );
        }
    }
    
    
    
    public Integer calcSum() throws WebInvalidInputException
    {
        Integer bottomId = this.getBottomId();
        Integer topId = this.getTopId();
        Integer amount = this.getAmount();
        
        if ( bottomId == null ) {
            throw new WebInvalidInputException( "Bottom Id null", "Bottom Id null" );
        }
        
        if ( topId == null ) {
            throw new WebInvalidInputException( "Top Id null", "Top Id null" );
        }
        
        if ( amount == null ) {
            throw new WebInvalidInputException( "Amount null", "Amount null" );
        }
        
        Bottom bottom = CtxGlobalAttributes.BOTTOM_MAP.get( bottomId );
        Top top = CtxGlobalAttributes.TOP_MAP.get( topId );
        
        if ( bottom == null ) {
            throw new WebInvalidInputException( "No such bottom", "Invalid bottom ordered" );
        }
        
        if ( top == null ) {
            throw new WebInvalidInputException( "No such top", "Invalid top ordered" );
        }
        
        Integer topPrice = top.getPriceOere();
        Integer bottomPrice = bottom.getPriceOere();
        
        Integer priceInOere = ( topPrice + bottomPrice ) * amount;
        
        this.sum = new PriceInOereAndDkk( priceInOere );
        return priceInOere;
    }
    
    public PriceInOereAndDkk getSum()
    {
        return this.sum;
    }
    
    public String getString() throws WebInvalidInputException
    {
        if ( this.sum == null ) {
            this.calcSum();
        }
        
        return this.sum.toString();
    }

    public String getOrderLineBottomTaste(){
        BottomExtended bottomExtended = CtxGlobalAttributes.BOTTOM_MAP.get( this.getBottomId() );
        return bottomExtended.getAddToCart();
    }

    public String getOrderLineTopTaste(){
        TopExtended topExtended = CtxGlobalAttributes.TOP_MAP.get( this.getTopId() );
        return topExtended.getAddToCart();
    }

    
    public String getMeta() //TODO: Placeholder, use table in html instead
    {
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append( "Bund: " );
        stringBuilder.append( CtxGlobalAttributes.BOTTOM_MAP.get( this.getBottomId() ).getTaste() );
        
        stringBuilder.append( " - Top: " );
        stringBuilder.append( CtxGlobalAttributes.TOP_MAP.get( this.getTopId() ).getTaste() );
        
        stringBuilder.append( " - Antal: " );
        stringBuilder.append( this.getAmount() );
        
        try {
            String price = this.getString();
            stringBuilder.append( " - Pris: " );
            stringBuilder.append( price );
            
        } catch ( WebInvalidInputException ignored ) {
        
        }
        
        stringBuilder.append( System.lineSeparator() );
        
        return stringBuilder.toString();
    }
    
}
