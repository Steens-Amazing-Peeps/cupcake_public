package cupcake.databases.entities.tables;

public class Role  {

    private Integer roleId;  // t
    private String role;  // t

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
    
    @Override
    public String toString()
    {
        return "Role{" +
               "roleId=" + this.roleId +
               ", role='" + this.role + '\'' +
               '}';
    }
    
}