package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "kweet")
@NamedQueries({
        @NamedQuery(name = "Kweet.findAll", query = "SELECT K from Kweet K"),
        @NamedQuery(name = "Kweet.getByID", query = "SELECT K FROM Kweet K where k.id = :id"),
        @NamedQuery(name = "Kweet.getKweetsByEmail", query = "SELECT K FROM Kweet K INNER JOIN User u ON K.user.id = U.id where U.email = :email")
})
public class Kweet implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    @Column(name = "date_time")
    private LocalDateTime DateTime;
    @Column(name = "content")
    private String content;
    @OneToMany(mappedBy = "kweet")
    private List<Like> likes;
    @ManyToOne
    private User user;

    public Kweet(LocalDateTime dateTime, String content, User user) {
        DateTime = dateTime;
        this.content = content;
        this.user = user;
        likes = new ArrayList<>();
    }

    public Kweet() {
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return DateTime;
    }

    public String getContent() {
        return content;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public User getUser() {
        return user;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDateTime(LocalDateTime dateTime) {
        DateTime = dateTime;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Kweet{" +
                "id=" + id +
                ", DateTime=" + DateTime +
                ", content='" + content + '\'' +
                '}';
    }
}
