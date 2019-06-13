package rest;

import authentication.RandomToken;
import domain.Token;
import domain.User;
import service.GroupService;
import service.KweetService;
import service.UserService;
import utils.AuthenticationUtils;
import utils.LoginResponse;
import websockets.SessionListener;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;


@Stateless
@Path("auth")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
public class AuthAPI extends Application {

    @Inject
    private UserService userService;

    @Inject
    private KweetService kweetService;

    @Inject
    private GroupService groupService;

    @POST
    public Response token(@FormParam("email") String email, @FormParam("password") String password){

        try {
            User user = authenticate(email, password);

            String token = new RandomToken().createJWT(user);

            userService.addToken(new Token(token, LocalDateTime.now().plusMinutes(60), groupService.getGroupByName(user.getEmail()).getGroupname()));

            return Response.ok(new LoginResponse(token, user)).build();
        } catch (Exception e){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

    }

    private User authenticate(String email, String password){
        User user = userService.login(email, AuthenticationUtils.encodeSHA256(password));

        if(user == null){
            throw new SecurityException("Invalid email or password");
        }
        user.setKweets(kweetService.getKweetsByEmail(email));
        user.setFollowers(userService.getFollowers(user));
        user.setFollowings(userService.getFollowing(user));

        if (!SessionListener.getInstance().getActiveUsers().contains(email)) {
            SessionListener.getInstance().getActiveUsers().add(email);
        }

        return user;
    }

    @POST
    @Path("logout")
    public void logout(@FormParam("email") String email) {
        try {
            SessionListener.getInstance().getActiveUsers().remove(email);
        } catch (Exception e){
            //error
        }
    }

    @POST
    @Path("create")
    public Response createUser(User user){
        if(userService.createUser(user) != null){
            return Response.ok().entity(user).build();
        } else {
            return Response.status(409).build();
        }
    }
}
