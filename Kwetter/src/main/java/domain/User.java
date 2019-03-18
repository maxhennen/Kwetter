package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.junit.Ignore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "users_roles",
                joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "Id"),
                uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})})
    private List<Role> roles;

    @OneToOne(mappedBy = "user", cascade = CascadeType.PERSIST)
    private Details details;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_followers" ,joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "Id", nullable = false)
              , inverseJoinColumns = @JoinColumn(name = "follower_id", referencedColumnName = "Id", nullable = false))
    @JsonIgnore
    private List<User> followers;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_following" ,joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "Id", nullable = false)
            , inverseJoinColumns = @JoinColumn(name = "following_id", referencedColumnName = "Id", nullable = false))
    @JsonIgnore
    private List<User> following;


    @OneToMany(mappedBy = "user")
    private List<Kweet> kweets;

    @OneToMany(mappedBy = "user")
    private List<Like> likes;

    public User(long id, String username, String name, String email, List<Role> roles, Details details) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.roles = roles;
        this.details = details;
        followers = new ArrayList<>();
        following = new ArrayList<>();
        kweets = new ArrayList<>();
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        followers = new ArrayList<>();
        following = new ArrayList<>();
        kweets = new ArrayList<>();
        roles = new ArrayList<>();
        details = new Details();
    }

    public User() {
        followers = new ArrayList<>();
        following = new ArrayList<>();
        kweets = new ArrayList<>();
        roles = new ArrayList<>();
        details = new Details();
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

    public List<Kweet> getKweets() {
        return kweets;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public void setKweets(List<Kweet> kweets) {
        this.kweets = kweets;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public List<User> getFollowing() {
        return following;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param follow
     */
    public void addFollowing(User follow) {
        if (follow != null && following != null) {
            following.add(follow);
            follow.addFollower(this);
        }
    }

    private void addFollower(User user) {
        followers.add(user);
    }

    public void removeFollowing(User follow) {
        for (User u:following) if(u.getUsername().equals(follow.getUsername())) {
            following.remove(u);
            break;
        }
        follow.removeFollower(this);
    }

    public void removeFollower(User follower) {
        for (User u:followers) if(u.getUsername().equals(follower.getUsername())) {
            followers.remove(u);
            u.getFollowing().remove(this);
            break;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", details=" + details +
                '}';
    }

    /**
     * @param kweet
     */
    public void addKweet(Kweet kweet) {
        if (kweet != null && kweets != null) {
            kweets.add(kweet);
        }
    }

    /**
     * @param kweet
     */
    public void removeKweet(Kweet kweet) {
        if (kweet != null && kweets != null && kweets.contains(kweet)) {
            this.kweets.remove(kweet);
        }
    }
}
