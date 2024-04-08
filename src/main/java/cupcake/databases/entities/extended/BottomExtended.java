package cupcake.databases.entities.extended;

import cupcake.databases.entities.tables.Bottom;
import cupcake.util.PriceInOereAndDkk;

public class BottomExtended extends Bottom
{
    private String addToCart;
    private PriceInOereAndDkk sum;
    
    public BottomExtended()
    {
    }
    
    public BottomExtended( Bottom bottom )
    {
        if ( bottom != null ) {
            this.setBottomId( bottom.getBottomId() );
            this.setTaste( bottom.getTaste() );
            this.setPriceOere( bottom.getPriceOere() );
            this.setObsolete( bottom.getObsolete() );
            this.calcSum();
            this.calcAddToCart();
        }
    }
    
    public PriceInOereAndDkk calcSum()
    {
        this.sum = new PriceInOereAndDkk( this.getPriceOere() );
        return this.sum;
    }
    
    public String getString(){
        if ( this.sum == null ) {
            this.calcSum();
        }
        
        return this.sum.toString();
    }
    
    private String calcAddToCart()
    {
        this.addToCart = this.getTaste() + " - " + this.getString();
        return this.addToCart;
    }
    
    public String getAddToCart()
    {
        return this.addToCart;
    }
}
