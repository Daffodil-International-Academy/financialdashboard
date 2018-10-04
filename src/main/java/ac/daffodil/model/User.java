package ac.daffodil.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by codin on 3/20/2018.
 */

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email")
    @Email(message ="*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    @Column(name = "password")
    @Length(min = 4, message = "*Your password must have at least 4 characters")
    private String password;

    @Column(name = "firstName")
    @NotEmpty(message = "*Please provide your first name")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "*Please provide your last name")
    private String lastName;

    @Column(name = "mobile")
    @NotEmpty(message = "*Please provide your mobile number")
    private String mobileNumber;

    @Column(name = "active")
    private int active;

    @Column(name = "roleId")
    private long roleId;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Organization> organizations;


    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name="user_role",joinColumns = @JoinColumn(name = "user_id",
            referencedColumnName = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id",
            referencedColumnName = "role_id"))
    private Set<Role> roles;


    public User() {

    }


    public User(User user){
        this.id=user.getId();
        this.email=user.getEmail();
        this.firstName=user.getFirstName();
        this.active=user.getActive();
        this.roleId=user.getRoleId();
        this.roles=user.getRoles();
        this.mobileNumber=user.getMobileNumber();
        this.lastName=user.getLastName();
        this.password=user.getPassword();

    }

    public User(@Email(message = "*Please provide a valid Email") @NotEmpty(message = "*Please provide an email") String email, @Length(min = 4, message = "*Your password must have at least 4 characters") String password, @NotEmpty(message = "*Please provide your first name") String firstName, @NotEmpty(message = "*Please provide your last name") String lastName, @NotEmpty(message = "*Please provide your mobile number") String mobileNumber, int active, long roleId, Long organizationId, List<Organization> organizations, Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.active = active;
        this.roleId = roleId;
        this.organizations = organizations;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", active=" + active +
                ", roleId=" + roleId +
                ", roles=" + roles +
                '}';
    }
}
