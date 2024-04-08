package cupcake.databases.entities.tables;

public class Bottom  {

    private Integer bottomId;  // t
    private String taste;  // t
    private Integer priceOere;  // t
    boolean obsolete;

    public Integer getBottomId() {
        return this.bottomId;
    }

    public void setBottomId( Integer bottomId ) {
        this.bottomId = bottomId;
    }

    public String getTaste() {
        return this.taste;
    }

    public void setTaste( String taste ) {
        this.taste = taste;
    }

    public Integer getPriceOere() {
        return this.priceOere;
    }

    public void setPriceOere( Integer priceOere ) {
        this.priceOere = priceOere;
    }
    public boolean getObsolete()
    {
        return this.obsolete;
    }
    
    public void setObsolete( boolean obsolete )
    {
        this.obsolete = obsolete;
    }
    
    @Override
    public String toString()
    {
        return "Bottom{" +
               "bottomId=" + this.bottomId +
               ", taste='" + this.taste + '\'' +
               ", priceOere=" + this.priceOere +
               '}';
    }
    
}