package domain.user;

import java.util.List;

public class Administrator extends Moderator {
    public Administrator(long id, String username, String name, String email, List<Role> roles, Details details) {
        super(id, username, name, email, roles, details);
    }
}
