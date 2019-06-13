package websockets;

import authentication.Secured;
import domain.User;
import service.KweetService;
import service.UserService;

import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.List;

@Secured
@ServerEndpoint(value = "/socket/{email}")
public class WebsocketAPI {

    @Inject
    private KweetService kweetService;

    @Inject
    private UserService userService;

    @OnOpen
    public void onOpen(Session session, @PathParam("email") String email){
        SessionListener.getInstance().getSessionMap().put(email, session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("email") String email){
        SessionListener.getInstance().getSessionMap().remove(email);
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("email") String email){
        List<String> userEmails = SessionListener.getInstance().getActiveUsers();
        User user = userService.findByEmail(email);
        user.setFollowings(userService.getFollowing(user));
        for(User following : user.getFollowings()){
            if(userEmails.contains(following.getEmail())){
                SessionListener.getInstance().getSessionMap().get(following.getEmail()).getAsyncRemote().sendText("New kweet: " + message + " placed by " + user.getName());
            }
        }
        SessionListener.getInstance().getSessionMap().get(user.getEmail()).getAsyncRemote().sendText("You placed the following kweet: " + message);
    }
}
