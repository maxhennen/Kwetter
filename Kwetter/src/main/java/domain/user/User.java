package domain.user;

import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @GeneratedValue
    @Id
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password") @Ignore
    private String password;
    @Column(name = "roles")
    private List<Role> roles;
    @OneToOne(mappedBy = "details")
    private Details details;
    @OneToMany(mappedBy = "user")
    private List<User> followers;
    @OneToMany(mappedBy = "user")
    private List<User> following;

    public User(long id, String username, String name, String email, List<Role> roles, Details details) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.roles = roles;
        this.details = details;
        followers = new ArrayList<>();
        following = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public Details getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                ", details=" + details +
                '}';
    }
}
