package cupcake.databases.entities.views;

import java.util.Date;

public class OrderDetailed
{

    private Integer orderId;  // t
    private Integer userId;  // t
    private String email;  // t
    private Integer roleId;  // t
    private String role;  // t
    private Date dateOrdered;  // t
    private Date dateReady;  // t
    private Integer statusId;  // t
    private String status;  // t
    private Integer orderLineId;  // t
    private Integer topId;  // t
    private String topTaste;  // t
    private Integer topPriceOere;  // t
    private Boolean topObsolete;  // t
    private Integer bottomId;  // t
    private String bottomTaste;  // t
    private Integer bottomPriceOere;  // t
    private Boolean bottomObsolete;  // t
    private Integer amount;  // t

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

    public String getEmail() {
        return this.email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId( Integer roleId ) {
        this.roleId = roleId;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole( String role ) {
        this.role = role;
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

    public String getStatus() {
        return this.status;
    }

    public void setStatus( String status ) {
        this.status = status;
    }

    public Integer getOrderLineId() {
        return this.orderLineId;
    }

    public void setOrderLineId( Integer orderLineId ) {
        this.orderLineId = orderLineId;
    }

    public Integer getTopId() {
        return this.topId;
    }

    public void setTopId( Integer topId ) {
        this.topId = topId;
    }

    public String getTopTaste() {
        return this.topTaste;
    }

    public void setTopTaste( String topTaste ) {
        this.topTaste = topTaste;
    }

    public Integer getTopPriceOere() {
        return this.topPriceOere;
    }

    public void setTopPriceOere( Integer topPriceOere ) {
        this.topPriceOere = topPriceOere;
    }

    public Boolean getTopObsolete() {
        return this.topObsolete;
    }

    public void setTopObsolete( Boolean topObsolete ) {
        this.topObsolete = topObsolete;
    }

    public Integer getBottomId() {
        return this.bottomId;
    }

    public void setBottomId( Integer bottomId ) {
        this.bottomId = bottomId;
    }

    public String getBottomTaste() {
        return this.bottomTaste;
    }

    public void setBottomTaste( String bottomTaste ) {
        this.bottomTaste = bottomTaste;
    }

    public Integer getBottomPriceOere() {
        return this.bottomPriceOere;
    }

    public void setBottomPriceOere( Integer bottomPriceOere ) {
        this.bottomPriceOere = bottomPriceOere;
    }

    public Boolean getBottomObsolete() {
        return this.bottomObsolete;
    }

    public void setBottomObsolete( Boolean bottomObsolete ) {
        this.bottomObsolete = bottomObsolete;
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
        return "OrderDetailed{" +
               "orderId=" + this.orderId +
               ", userId=" + this.userId +
               ", email='" + this.email + '\'' +
               ", roleId=" + this.roleId +
               ", role='" + this.role + '\'' +
               ", dateOrdered=" + this.dateOrdered +
               ", dateReady=" + this.dateReady +
               ", statusId=" + this.statusId +
               ", status='" + this.status + '\'' +
               ", orderLineId=" + this.orderLineId +
               ", topId=" + this.topId +
               ", topTaste='" + this.topTaste + '\'' +
               ", topPriceOere=" + this.topPriceOere +
               ", topObsolete=" + this.topObsolete +
               ", bottomId=" + this.bottomId +
               ", bottomTaste='" + this.bottomTaste + '\'' +
               ", bottomPriceOere=" + this.bottomPriceOere +
               ", bottomObsolete=" + this.bottomObsolete +
               ", amount=" + this.amount +
               '}';
    }
    
}