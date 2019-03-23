package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "kweetLike")
@NamedQueries({
        @NamedQuery(name = "Like.findAll", query = "SELECT L FROM Like L"),
        @NamedQuery(name = "Like.getByID", query = "SELECT L FROM Like L WHERE L.id = :id")
})
public class Like implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    @ManyToOne
    private User user;
    @ManyToOne
    private Kweet kweet;

    public Like(LocalDateTime dateTime, User user, Kweet kweet) {
        this.dateTime = dateTime;
        this.user = user;
        this.kweet = kweet;
    }

    public Like() {
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public User getUser() {
        return user;
    }

    public Kweet getKweet() {
        return kweet;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setKweet(Kweet kweet) {
        this.kweet = kweet;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", user=" + user +
                ", kweet=" + kweet +
                '}';
    }
}
