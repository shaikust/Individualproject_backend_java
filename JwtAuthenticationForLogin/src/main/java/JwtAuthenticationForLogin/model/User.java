package JwtAuthenticationForLogin.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "userstables",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"emailid"})})
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Integer id;
    private String emailid;
    private  String name;
    private String phone;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name="user_roles",joinColumns = @JoinColumn(name="user_id",referencedColumnName = "id"),inverseJoinColumns =
    @JoinColumn(name="role_id",referencedColumnName = "id"))
    private Set<Roles> roles;





//    public User(String emailid, String name, String phone) {
//        this.emailid = emailid;
//        this.name = name;
//        this.phone = phone;
//        this.password=password;
//        this.id=id;
//
//    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }
}
