package controller;

import domain.Role;
import service.RoleService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/role")
@Stateless
public class RoleController extends Application {

    @Inject
    private RoleService roleService;

    @POST
    @Path("/setup")
    public void setUpRoles(){
        Role user = new Role("ROLE_USER");
        Role admin = new Role("ROLE_ADMIN");
        Role moder = new Role("ROLE_MODERATOR");

        roleService.createRole(user);
        roleService.createRole(admin);
        roleService.createRole(moder);
    }
}
