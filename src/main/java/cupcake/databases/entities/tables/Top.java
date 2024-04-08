package cupcake.databases.entities.tables;

public class Top  {

    private Integer topId;  // t
    private String taste;  // t
    private Integer priceOere;  // t
    private boolean obsolete;

    public Integer getTopId() {
        return this.topId;
    }

    public void setTopId( Integer topId ) {
        this.topId = topId;
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
        return "Top{" +
               "topId=" + this.topId +
               ", taste='" + this.taste + '\'' +
               ", priceOere=" + this.priceOere +
               '}';
    }
    
}