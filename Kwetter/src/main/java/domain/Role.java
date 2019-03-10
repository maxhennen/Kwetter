package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "role")
public class Role implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long Id;

    @Column(name = "role", nullable = false, unique = true)
    private String role;

    @ManyToMany
    @JoinTable(name="user_roles"
            , joinColumns = @JoinColumn(name = "Id", referencedColumnName = "Id")
            , inverseJoinColumns = @JoinColumn(name = "Id", referencedColumnName = "Id"))
    private List<User> users;

    public Role(String role) {
        this.role = role;
    }

    public Role() {
    }

    public String getRole() {
        return role;
    }
}
