//package rest;
//
//import authentication.Secured;
//import domain.*;
//import service.KweetService;
//
//import javax.ejb.Stateless;
//import javax.inject.Inject;
//import javax.ws.rs.*;
//import javax.ws.rs.core.Application;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import java.time.LocalDateTime;
//import java.util.List;
//
//
//@Stateless
//@Path("kweet")
//@Produces({MediaType.APPLICATION_JSON})
//@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
//@Secured
//public class KweetAPI extends Application {
//
//    @Inject
//    private KweetService kweetService;
//
//    @GET
//    public Response findAll(){
//        List<Kweet> kweets = kweetService.getAllKweets();
//
//        if(kweets != null){
//            return Response.ok().entity(kweets).build();
//        } else {
//            return Response.noContent().build();
//        }
//    }
//
//    @POST
//    public Response save(@FormParam("content") String content, @FormParam("email") String email ){
//        Kweet kweet = new Kweet(LocalDateTime.now(), content, email);
//        if(kweetService.createKweet(kweet) != null){
//            return Response.ok().entity(kweet).build();
//        } else {
//            return Response.status(409).build();
//        }
//    }
//
//    @GET
//    @Path("id/{id}")
//    public Response findById(@PathParam("id") long id){
//        Kweet kweet = kweetService.getKweetById(id);
//
//        if(kweet != null ){
//            return Response.ok().entity(kweet).build();
//        } else {
//            return Response.noContent().build();
//        }
//    }
//
//    @DELETE
//    @Path("{id}")
//    public Response delete(@PathParam("id") long id){
//        Kweet kweet = kweetService.getKweetById(id);
//        if(kweetService.removeKweet(kweet)){
//            return Response.ok().entity("Kweet is removed.").build();
//        } else {
//            return Response.status(409).build();
//        }
//    }
//
//    @GET
//    @Path("{email}")
//    public Response findByEmail(@PathParam("email") String email){
//        List<Kweet> kweets = kweetService.getKweetsByEmail(email);
//        if(kweets != null && !kweets.isEmpty()){
//            return Response.ok(kweets).build();
//        } else {
//            return Response.noContent().build();
//        }
//    }
//}