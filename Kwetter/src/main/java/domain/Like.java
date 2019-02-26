package domain;

import domain.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "like")
public class Like {

    @GeneratedValue
    @Id
    private long id;
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    @OneToOne(mappedBy = "user")
    private User user;
    @OneToOne(mappedBy = "kweet")
    private Kweet kweet;

    public Like(long id, LocalDateTime dateTime, User user, Kweet kweet) {
        this.id = id;
        this.dateTime = dateTime;
        this.user = user;
        this.kweet = kweet;
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
