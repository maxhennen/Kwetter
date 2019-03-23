package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@NamedQueries({
        @NamedQuery(name = "User.findAll", query = "SELECT U FROM User U"),
        @NamedQuery(name = "User.findByEmail", query = "SELECT U FROM User u WHERE U.email = :email"),
        @NamedQuery(name = "User.getFollowers", query = "SELECT U From User U inner join Follower F on U.email = F.emailFollower where F.emailUser = :email"),
        @NamedQuery(name = "User.getFollowing", query = "SELECT U FROM User U inner join Following F on U.email = F.emailFollowing where F.emailUser = :email"),
        @NamedQuery(name = "User.getFollowerByEmail", query = "SELECT F FROM Follower F where F.emailFollower = :email"),
        @NamedQuery(name = "User.getFollowingByEmail", query = "SELECT F FROM Following F where F.emailFollowing = :email")
})
public class User implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.PERSIST)
    private Details details;

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "user_followers" ,joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "Id", nullable = false)
//              , inverseJoinColumns = @JoinColumn(name = "follower_id", referencedColumnName = "Id", nullable = false))
//    @JsonIgnore
//    private List<User> followers;
//
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "user_following" ,joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "Id", nullable = false)
//            , inverseJoinColumns = @JoinColumn(name = "following_id", referencedColumnName = "Id", nullable = false))
//    @JsonIgnore
//    private List<User> following;


    @OneToMany(mappedBy = "user")
    private List<Kweet> kweets;

    @OneToMany(mappedBy = "user")
    private List<Like> likes;

    public User(long id, String name, String email, List<Group> groups, Details details) {
        this.id = id;
        this.name = name;
        this.email = email;
//        this.groups = groups;
        this.details = details;
        kweets = new ArrayList<>();
    }

    public User(String email, String password, String name) {
        this.name = name;
        this.email = email;
        this.password = password;
        kweets = new ArrayList<>();
//        groups = new ArrayList<>();
        details = new Details();
    }

    public User() {
        kweets = new ArrayList<>();
//        groups = new ArrayList<>();
        details = new Details();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

//    public List<Group> getGroups() {
//        return groups;
//    }

    public Details getDetails() {
        return details;
    }

    public List<Kweet> getKweets() {
        return kweets;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public void setGroups(List<Group> groups) {
//        this.groups = groups;
//    }

    public void setDetails(Details details) {
        this.details = details;
    }

//    public void setFollowers(List<User> followers) {
//        this.followers = followers;
//    }

//    public void setFollowing(List<User> following) {
//        this.following = following;
//    }

    public void setKweets(List<Kweet> kweets) {
        this.kweets = kweets;
    }

//    public List<User> getFollowers() {
//        return followers;
//    }
//
//    public List<User> getFollowing() {
//        return following;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    /**
//     * @param follow
//     */
//    public void addFollowing(User follow) {
//        if (follow != null && following != null) {
//            following.add(follow);
//            follow.addFollower(this);
//        }
//    }
//
//    private void addFollower(User user) {
//        followers.add(user);
//    }
//
//    public void removeFollowing(User follow) {
//        for (User u:following) if(u.getEmail().equals(follow.getEmail())) {
//            following.remove(u);
//            break;
//        }
//        follow.removeFollower(this);
//    }
//
//    public void removeFollower(User follower) {
//        for (User u:followers) if(u.getEmail().equals(follower.getEmail())) {
//            followers.remove(u);
//            u.getFollowing().remove(this);
//            break;
//        }
//    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
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
