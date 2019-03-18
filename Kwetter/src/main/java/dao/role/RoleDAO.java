package dao.role;

import domain.Role;

public interface RoleDAO {

    /**
     * Creates a new Like object
     */
    void create(Role r);

    /**
     * Retrieves a role by name
     */
    Role getByName(String name);
}
