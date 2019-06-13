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
    private String jwtoken;
    @Column(name = "expirationTime")
    private LocalDateTime expirationTime;

    @Ignore
    private String role;

    public Token(String jwtoken, LocalDateTime expirationTime, String role) {
        this.jwtoken = jwtoken;
        this.expirationTime = expirationTime;
        this.role =role;
    }

    public Token() {
    }

    public String getJwtoken() {
        return jwtoken;
    }

    public void setJwtoken(String token) {
        this.jwtoken = token;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }
}
