package rest;

import service.GroupService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;

@Path("/group")
@Stateless
public class GroupAPI extends Application {

    @Inject
    private GroupService groupService;

    @POST
    @Path("/setup")
    public void setUpRoles(){

    }
}
