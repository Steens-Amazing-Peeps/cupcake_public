package cupcake.databases.entities.tables;

public class Wallet  {

    private Integer walletId;  // t
    private Integer userId;  // t
    private Integer balanceOere;  // t

    public Integer getWalletId() {
        return this.walletId;
    }

    public void setWalletId( Integer walletId ) {
        this.walletId = walletId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId( Integer userId ) {
        this.userId = userId;
    }

    public Integer getBalanceOere() {
        return this.balanceOere;
    }

    public void setBalanceOere( Integer balanceOere ) {
        this.balanceOere = balanceOere;
    }
    
    @Override
    public String toString()
    {
        return "Wallet{" +
               "walletId=" + this.walletId +
               ", userId=" + this.userId +
               ", balanceOere=" + this.balanceOere +
               '}';
    }
    
}