package domain;

import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "token")
public class Token implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="token", unique = true)
    private String token;
    @Column(name = "expirationTime")
    private LocalDateTime expirationTime;

    @Ignore
    private String role;

    public Token(String token, LocalDateTime expirationTime, String role) {
        this.token = token;
        this.expirationTime = expirationTime;
        this.role =role;
    }

    public Token() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }
}
