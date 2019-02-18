package domain;

import java.time.LocalDateTime;

public class Kwetter {

    private long id;
    private LocalDateTime DateTime;
    private String content;

    public Kwetter(long id, LocalDateTime dateTime, String content) {
        this.id = id;
        DateTime = dateTime;
        this.content = content;
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
}
