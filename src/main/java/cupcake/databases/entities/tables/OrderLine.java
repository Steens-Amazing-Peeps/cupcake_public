package cupcake.databases.entities.tables;

public class OrderLine  {

    private Integer orderLineId;  // t
    private Integer orderId;  // t
    private Integer topId;  // t
    private Integer bottomId;  // t
    private Integer amount;  // t

    public Integer getOrderLineId() {
        return this.orderLineId;
    }

    public void setOrderLineId( Integer orderLineId ) {
        this.orderLineId = orderLineId;
    }

    public Integer getOrderId() {
        return this.orderId;
    }

    public void setOrderId( Integer orderId ) {
        this.orderId = orderId;
    }

    public Integer getTopId() {
        return this.topId;
    }

    public void setTopId( Integer topId ) {
        this.topId = topId;
    }

    public Integer getBottomId() {
        return this.bottomId;
    }

    public void setBottomId( Integer bottomId ) {
        this.bottomId = bottomId;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount( Integer amount ) {
        this.amount = amount;
    }
    
    @Override
    public String toString()
    {
        return "OrderLine{" +
               "orderLineId=" + this.orderLineId +
               ", orderId=" + this.orderId +
               ", topId=" + this.topId +
               ", bottomId=" + this.bottomId +
               ", amount=" + this.amount +
               '}';
    }
    
}