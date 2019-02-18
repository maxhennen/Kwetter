package domain;

import domain.user.User;
import java.time.LocalDateTime;

public class Like {

    private long id;
    private LocalDateTime dateTime;
    private User user;
    private Kwetter kwetter;

    public Like(long id, LocalDateTime dateTime, User user, Kwetter kwetter) {
        this.id = id;
        this.dateTime = dateTime;
        this.user = user;
        this.kwetter = kwetter;
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

    public Kwetter getKwetter() {
        return kwetter;
    }
}
