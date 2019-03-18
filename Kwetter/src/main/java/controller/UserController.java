package controller;

import domain.Details;
import domain.Location;
import domain.Role;
import domain.User;
import service.RoleService;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/user")
@Stateless
public class UserController extends Application {

    @Inject
    private UserService userService;

    @Inject
    private RoleService roleService;

    @GET
    @Path("/findAll")
    @Produces(APPLICATION_JSON)
    public List<User> findAll(){
        return userService.getAllUsers();
    }

    @POST
    @Path("/createUser")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void save(@FormParam("username") String username, @FormParam("password") String password){
        User user = new User(username, password);
        Role role = roleService.getRoleByName("ROLE_USER");
        user.getRoles().add(role);
        userService.createUser(user);
    }

    @GET
    @Path("/findOneById")
    @Produces(APPLICATION_JSON)
    public User findOneById(@QueryParam("id") long id){
        return userService.findByID(id);
    }

    @DELETE
    @Path("/delete")
    public void delete(@QueryParam("username") String username){
        User user = userService.findByUsername(username);
        userService.removeUser(user);
    }

    @GET
    @Path("/findByUsername")
    @Produces(APPLICATION_JSON)
    public User findByUsername(@QueryParam("username") String username){
        return userService.findByUsername(username);
    }

    @GET
    @Path("/following")
    @Produces(APPLICATION_JSON)
    public List<User> getFollowing(@QueryParam("username") String username){
        User user = userService.findByUsername(username);
        return userService.getFollowing(user);
    }

    @GET
    @Path("/followers")
    @Produces(APPLICATION_JSON)
    public List<User> getFollowers(@QueryParam("username") String username){
        User user = userService.findByUsername(username);
        return userService.getFollowing(user);
    }

    @DELETE
    @Path("/unfollow")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void removeFollower(@FormParam("follower") String usernameFollower, @FormParam("following") String usernameFollowing){
        User follower = userService.findByUsername(usernameFollower);
        User following = userService.findByUsername(usernameFollowing);
        userService.removeFollowing(follower, following);
    }

    @POST
    @Path("/addFollow")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void addFollower(@FormParam("follower") String usernameFollower, @FormParam("following") String usernameFollowing){
        User follower = userService.findByUsername(usernameFollower);
        User following = userService.findByUsername(usernameFollowing);
        userService.followUser(follower, following);
    }

    @POST
    @Path("/changeDetails")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void changeDetails(@FormParam("bio") String bio, @FormParam("website") String website, @FormParam("username") String username){
        Details details = new Details(bio, website);
        userService.editDetails(username, details);
    }

    @POST
    @Path("/changeLocation")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void changeLocation(@FormParam("country") String country, @FormParam("city") String city, @FormParam("Street") String street, @FormParam("housenumber") String housenumber, @FormParam("username") String username){
        Location location = new Location(country, city, street, housenumber);
        userService.editLocation(username, location);
    }

}
