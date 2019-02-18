package domain.user;

import java.util.List;

public class Moderator extends User {
    public Moderator(long id, String username, String name, String email, List<Role> roles) {
        super(id, username, name, email, roles);
    }
}
