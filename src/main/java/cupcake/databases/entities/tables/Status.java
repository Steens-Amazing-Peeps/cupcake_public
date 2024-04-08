package cupcake.databases.entities.tables;

public class Status  {

    private Integer statusId;  // t
    private String status;  // t

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
    
    @Override
    public String toString()
    {
        return "Status{" +
               "statusId=" + this.statusId +
               ", status='" + this.status + '\'' +
               '}';
    }
    
}