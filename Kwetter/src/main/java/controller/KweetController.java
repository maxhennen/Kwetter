package controller;

import domain.Kweet;
import service.KweetService;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import java.time.LocalDateTime;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@ApplicationPath("/api")
@Path("/kweet")
public class KweetController extends Application {

    @Inject
    private KweetService kweetService;

    @Inject
    private UserService userService;

    @GET
    @Path("/findAll")
    @Produces(APPLICATION_JSON)
    public List<Kweet> findAll(){
        return kweetService.getAllKweets();
    }

    @POST
    @Path("/save")
    @Consumes(APPLICATION_JSON)
    public void save(@FormParam("content") String content, @FormParam("username") String username ){
        Kweet kweet = new Kweet(LocalDateTime.now(), content, userService.findByUsername(username));
        kweetService.createKweet(kweet);
    }

    @GET
    @Path("/findById/{id}")
    @Produces(APPLICATION_JSON)
    public Kweet findOneById(@PathParam("id") long id){
        return kweetService.getKweetById(id);
    }

    @DELETE
    @Path("/delete/{id}")
    public void delete(@PathParam("id") long id){
        Kweet kweet = kweetService.getKweetById(id);
        kweetService.removeKweet(kweet);
    }

    @GET
    @Path("/findByUsername/{username}")
    @Produces(APPLICATION_JSON)
    public List<Kweet> findByUsername(@PathParam("username") String username){
        return kweetService.getKweetsByUsername(username);
    }

}
