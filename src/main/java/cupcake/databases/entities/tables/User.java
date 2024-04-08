package cupcake.databases.entities.tables;
public class User  {

    private Integer userId;  // t
    private String email;  // t
    private String password;  // t
    private Integer roleId;  // t

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

    public String getPassword() {
        return this.password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId( Integer roleId ) {
        this.roleId = roleId;
    }
    
    @Override
    public String toString()
    {
        return "User{" +
               "userId=" + this.userId +
               ", email='" + this.email + '\'' +
               ", password='" + this.password + '\'' +
               ", roleId=" + this.roleId +
               '}';
    }
    
}