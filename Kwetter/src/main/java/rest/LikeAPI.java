package rest;

import domain.Like;
import service.KweetService;
import service.LikeService;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import java.time.LocalDateTime;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/like")
@Stateless
public class LikeAPI extends Application {

    @Inject
    private LikeService likeService;

    @Inject
    private UserService userService;

    @Inject
    private KweetService kweetService;

    @GET
    @Path("/findAll")
    @Produces(APPLICATION_JSON)
    public List<Like> findAll(){
        return likeService.findALlLikes();
    }

    @POST
    @Path("/save")
    @Consumes(APPLICATION_JSON)
    public void save(@FormParam("email") String email, @FormParam("id") long kweetId){
        Like like = new Like(LocalDateTime.now(), userService.findByEmail(email), kweetService.getKweetById(kweetId));
        likeService.createLike(like);
    }

    @GET
    @Path("/findOneById{id}")
    @Produces(APPLICATION_JSON)
    public Like findOneById(@PathParam("id") long id){
        return likeService.getById(id);
    }

    @DELETE
    @Path("/delete/{id}")
    public void delete(@PathParam("id") long id){
        likeService.removeLike(likeService.getById(id));
    }

}