package domain;

import domain.user.User;
import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    public Kweet(LocalDateTime dateTime, String content, List<Like> likes, User user) {
        DateTime = dateTime;
        this.content = content;
        this.likes = likes;
        this.user = user;
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

    @Override
    public String toString() {
        return "Kweet{" +
                "id=" + id +
                ", DateTime=" + DateTime +
                ", content='" + content + '\'' +
                '}';
    }
}
