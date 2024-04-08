package cupcake.databases.entities.tables;

import java.util.Date;

public class Order  {

    private Integer orderId;  // t
    private Integer userId;  // t
    private Date dateOrdered;  // t
    private Date dateReady;  // t
    private Integer statusId;  // t

    public Integer getOrderId() {
        return this.orderId;
    }

    public void setOrderId( Integer orderId ) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId( Integer userId ) {
        this.userId = userId;
    }

    public java.util.Date getDateOrdered() {
        return this.dateOrdered;
    }

    public void setDateOrdered(java.util.Date dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    public java.util.Date getDateReady() {
        return this.dateReady;
    }

    public void setDateReady(java.util.Date dateReady) {
        this.dateReady = dateReady;
    }

    public Integer getStatusId() {
        return this.statusId;
    }

    public void setStatusId( Integer statusId ) {
        this.statusId = statusId;
    }
    
    @Override
    public String toString()
    {
        return "Order{" +
               "orderId=" + this.orderId +
               ", userId=" + this.userId +
               ", dateOrdered=" + this.dateOrdered +
               ", dateReady=" + this.dateReady +
               ", statusId=" + this.statusId +
               '}';
    }
    
}