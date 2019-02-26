package domain.user;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {

    @GeneratedValue
    @Id
    private long Id;

    @Column(name = "role", nullable = false)
    private String role;

    public Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
