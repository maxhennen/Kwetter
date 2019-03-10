package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "like")
public class Like implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    @OneToOne(mappedBy = "user")
    private User user;
    @OneToOne(mappedBy = "kweet")
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
