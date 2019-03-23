package rest;

import domain.*;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/user")
@Stateless
public class UserAPI extends Application {

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
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void save(@FormParam("email") String email, @FormParam("password") String password, @FormParam("name") String name){
        User user = new User(name, email, password);
        userService.createUser(user);
    }

    @DELETE
    @Path("/delete")
    public void delete(@QueryParam("email") String email){
        User user = userService.findByEmail(email);
        userService.removeUser(user);
    }

    @GET
    @Path("/findByEmail")
    @Produces(APPLICATION_JSON)
    public User findByEmail(@QueryParam("email") String email){
        return userService.findByEmail(email);
    }

    @GET
    @Path("/following")
    @Produces(APPLICATION_JSON)
    public List<User> getFollowing(@QueryParam("email") String email){
        User user = userService.findByEmail(email);
        return userService.getFollowing(user);
    }

    @GET
    @Path("/followers")
    @Produces(APPLICATION_JSON)
    public List<User> getFollowers(@QueryParam("email") String email){
        User user = userService.findByEmail(email);
        return userService.getFollowing(user);
    }

    @DELETE
    @Path("/unfollow")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void removeFollowing(@FormParam("follower") String emailFollower, @FormParam("following") String emailFollowing){
        userService.removeFollowing(userService.getFollowerByEmail(emailFollower), userService.getFollowingByEmail(emailFollowing));
    }

    @DELETE
    @Path("/removeFollower")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void removeFollower(@FormParam("follower") String emailFollower, @FormParam("following") String emailFollowing){
        userService.removeFollower(userService.getFollowerByEmail(emailFollower), userService.getFollowingByEmail(emailFollowing));
    }

    @POST
    @Path("/addFollow")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void addFollower(@FormParam("follower") String emailFollower, @FormParam("following") String emailFollowing){
        User follower = userService.findByEmail(emailFollower);
        User following = userService.findByEmail(emailFollowing);
        userService.followUser(follower, following);
    }

    @POST
    @Path("/changeDetails")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void changeDetails(@FormParam("bio") String bio, @FormParam("website") String website, @FormParam("email") String email){
        Details details = new Details(bio, website);
        userService.editDetails(email, details);
    }

    @POST
    @Path("/changeLocation")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void changeLocation(@FormParam("country") String country, @FormParam("city") String city, @FormParam("Street") String street, @FormParam("housenumber") String housenumber, @FormParam("email") String email){
        Location location = new Location(country, city, street, housenumber);
        userService.editLocation(email, location);
    }

}
