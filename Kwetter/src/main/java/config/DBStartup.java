package config;

import service.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@Startup
public class DBStartup {

    public DBStartup(){
        System.out.println("check");
    }

    @Inject
    private UserService userService;

    @PostConstruct
    public void getUsers(){
        userService.getAllUsers();
    }
}
