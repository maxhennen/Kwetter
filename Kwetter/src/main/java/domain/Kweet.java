package domain;

import domain.user.User;
import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "kweet")
public class Kweet {

    @GeneratedValue
    @Id
    private long id;
    @Column(name = "date_time")
    private LocalDateTime DateTime;
    @Column(name = "content")
    private String content;
    @OneToMany(mappedBy = "like")
    private List<Like> likes;
    @OneToOne(mappedBy = "user")
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
