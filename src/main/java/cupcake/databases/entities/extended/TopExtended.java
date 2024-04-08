package cupcake.databases.entities.extended;

import cupcake.databases.entities.tables.Top;
import cupcake.util.PriceInOereAndDkk;

public class TopExtended extends Top
{
    private String addToCart;
    private PriceInOereAndDkk sum;
    
    public TopExtended()
    {
    }
    
    public TopExtended( Top top )
    {
        if ( top != null ) {
            this.setTopId( top.getTopId() );
            this.setTaste( top.getTaste() );
            this.setPriceOere( top.getPriceOere() );
            this.setObsolete( top.getObsolete() );
            this.calcSum();
            this.calcAddToCart();
        }
    }
    
    public PriceInOereAndDkk calcSum()
    {
        this.sum = new PriceInOereAndDkk( this.getPriceOere() );
        return this.sum;
    }
    
    public String getString()
    {
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
