package cupcake.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class PriceInOereAndDkk
{
    private static final int DIGITS_POST_COMMA_IN_A_PRICE = 2;
    private static final BigDecimal OERE_PR_DKK = new BigDecimal( 100 );
    private static final String DKK_STRING = " Kr.";
    
    private Integer priceInOere;
    private BigDecimal priceInDkk;
    
    
    
    //Constructors-----------------------------------------------------------
    
    //Oere
    public PriceInOereAndDkk( Integer priceInOere )
    {
        this.setPriceInOere( priceInOere );
    }
    
    public PriceInOereAndDkk()
    {
        this( 0 );
    }
    
    //DKK
    public PriceInOereAndDkk( BigDecimal priceInDkk )
    {
        this.setPriceInDkk( priceInDkk );
    }
    
    
    //Methods------------------------------------------------------------
    
    //Oere
    private Integer convertToOere( BigDecimal priceInDkk )
    {
        BigDecimal priceInOereBigDecimal = priceInDkk.multiply( OERE_PR_DKK );
        
        Integer priceInOere = priceInOereBigDecimal.intValue();
        
        return priceInOere;
    }
    
    //DKK
    private BigDecimal convertToDkk( Integer priceInOere )
    {
        BigDecimal priceInDkk = new BigDecimal( priceInOere );
        
        priceInDkk = priceInDkk.setScale( DIGITS_POST_COMMA_IN_A_PRICE, RoundingMode.HALF_UP );
        
        priceInDkk = priceInDkk.divide( OERE_PR_DKK, RoundingMode.HALF_UP );
        
        return priceInDkk;
    }
    
    
    //Getters And Setters------------------------------------
    
    //Oere
    public Integer getPriceInOere()
    {
        return this.priceInOere;
    }
    
    public void setPriceInOere( Integer priceInOere )
    {
        Integer checkedPriceInOere;
        
        checkedPriceInOere = Objects.requireNonNullElse( priceInOere, 0 );
        
        this.priceInOere = checkedPriceInOere;
        this.priceInDkk = this.convertToDkk( checkedPriceInOere );
    }
    
    
    //DKK
    public BigDecimal getPriceInDkk()
    {
        return this.priceInDkk;
    }
    
    public void setPriceInDkk( BigDecimal priceInDkk )
    {
        BigDecimal checkedPriceInDkk;
        
        checkedPriceInDkk = Objects.requireNonNullElseGet( priceInDkk, () -> new BigDecimal( 0 ) );
        
        if ( checkedPriceInDkk.scale() != DIGITS_POST_COMMA_IN_A_PRICE ) {
            checkedPriceInDkk = checkedPriceInDkk.setScale( DIGITS_POST_COMMA_IN_A_PRICE, RoundingMode.HALF_UP );
        }
        
        this.priceInOere = this.convertToOere( checkedPriceInDkk );
        this.priceInDkk = checkedPriceInDkk;
    }
    
    
    
    
    
    @Override
    public String toString()
    {
        return this.priceInDkk + DKK_STRING;
    }
    
}
