package domain.user;

import javax.xml.soap.Detail;
import java.util.ArrayList;
import java.util.List;

public class User {

    private long id;
    private String username;
    private String name;
    private String email;
    private List<Role> roles;
    private Details details;
    private List<User> followers;
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
