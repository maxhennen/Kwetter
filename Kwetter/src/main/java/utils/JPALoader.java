package utils;

import domain.Role;
import org.apache.maven.plugins.annotations.Component;
import org.springframework.context.event.EventListener;
import service.RoleService;

import javax.inject.Inject;

public class JPALoader {

    @Inject
    private RoleService roleService;

    @EventListener
    public void initRoles() {
        Role user = new Role("ROLE_USER");
        Role admin = new Role("ROLE_ADMIN");
        Role mod = new Role("ROLE_MOD");
        roleService.createRole(user);
        roleService.createRole(admin);
        roleService.createRole(mod);
    }
}
