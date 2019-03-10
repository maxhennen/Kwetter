package config;

import service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class DBStartup {

    public DBStartup(){
        System.out.println("check");
    }

    @Inject
    private UserService userService;

    public void getUsers(@Observes @Initialized(ApplicationScoped.class) Object init){
        userService.getAllUsers();
    }
}
