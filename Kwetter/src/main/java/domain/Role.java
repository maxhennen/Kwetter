package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "role")
public class Role implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long Id;

    @Column(name = "role", nullable = false, unique = true)
    private String role;

    public Role(String role) {
        this.role = role;
    }

    public Role() {
    }

    public String getRole() {
        return role;
    }
}
