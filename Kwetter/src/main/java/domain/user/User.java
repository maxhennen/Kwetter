package domain.user;

import domain.Kweet;
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
    @ManyToMany(mappedBy = "role")
    private List<Role> roles;
    @OneToOne(mappedBy = "details")
    private Details details;
    @ManyToMany
    @JoinTable(name = "user_followers" ,joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "Id", nullable = false)
              , inverseJoinColumns = @JoinColumn(name = "follower_id", referencedColumnName = "Id", nullable = false))
    private List<User> followers;
    @ManyToMany(mappedBy = "user")
    private List<User> following;

    @OneToMany(mappedBy = "kweeter")
    private List<Kweet> kweets;

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
    }

    public User() {
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
                ", roles=" + roles +
                ", details=" + details +
                '}';
    }

    /**
     * @param kweet
     */
    public void addKweet(Kweet kweet) {
        if (kweet != null && kweets != null) {
            kweets.add(kweet);
//            if (kweet.getPoster() != this)
//                kweet.setPoster(this);
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
