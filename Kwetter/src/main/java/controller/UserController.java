package controller;

import domain.Details;
import domain.Location;
import domain.User;
import service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/user")
@ApplicationScoped
public class UserController extends Application {

    @Inject
    private UserService userService;

    @GET
    @Path("/findAll")
    @Produces(APPLICATION_JSON)
    public List<User> findAll(){
        return userService.getAllUsers();
    }

    @POST
    @Path("/createUser")
    @Consumes(APPLICATION_JSON)
    public void save(@FormParam("username") String username, @FormParam("password") String password){
        User user = new User(username, password);
        userService.createUser(user);
    }

    @GET
    @Path("/findOneById/{id}")
    @Consumes(APPLICATION_JSON)
    public User findOneById(@PathParam("id") long id){
        return userService.findByID(id);
    }

    @DELETE
    @Path("/delete")
    public void delete(@FormParam("username") String username){
        User user = userService.findByUsername(username);
        userService.removeUser(user);
    }

    @GET
    @Path("/findByUsername/{username}")
    @Consumes(APPLICATION_JSON)
    public void findByUsername(@PathParam("username") String username){
        userService.findByUsername(username);
    }

    @GET
    @Path("/following/{username}")
    @Produces(APPLICATION_JSON)
    public List<User> getFollowing(@PathParam("username") String username){
        User user = userService.findByUsername(username);
        return userService.getFollowing(user);
    }

    @GET
    @Path("/followers/{username}")
    @Produces(APPLICATION_JSON)
    public List<User> getFollowers(@PathParam("username") String username){
        User user = userService.findByUsername(username);
        return userService.getFollowing(user);
    }

    @DELETE
    @Path("/unfollow")
    public void removeFollower(@FormParam("follower") String usernameFollower, @FormParam("following") String usernameFollowing){
        User follower = userService.findByUsername(usernameFollower);
        User following = userService.findByUsername(usernameFollowing);
        userService.removeFollowing(follower, following);
    }

    @POST
    @Path("/addFollow")
    public void addFollower(@FormParam("follower") String usernameFollower, @FormParam("following") String usernameFollowing){
        User follower = userService.findByUsername(usernameFollower);
        User following = userService.findByUsername(usernameFollowing);
        userService.followUser(follower, following);
    }

    @POST
    @Path("/changeDetails")
    public void changeDetails(@FormParam("bio") String bio, @FormParam("website") String website, @FormParam("username") String username){
        Details details = new Details(bio, website);
        userService.editDetails(username, details);
    }

    @POST
    @Path("/changeLocation")
    public void changeLocation(@FormParam("country") String country, @FormParam("city") String city, @FormParam("Street") String street, @FormParam("housenumber") String housenumber, @FormParam("username") String username){
        Location location = new Location(country, city, street, housenumber);
        userService.editLocation(username, location);
    }

}
