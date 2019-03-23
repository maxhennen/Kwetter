package utils;

import dao.group.GroupDAO;
import dao.kweet.KweetDAO;
import dao.user.UserDAO;
import domain.Group;
import domain.Kweet;
import domain.User;
import org.springframework.cglib.core.Local;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.ejb.Singleton;
import java.time.LocalDateTime;

@Singleton
@Startup
public class JPALoader {

    @Inject
    private UserDAO userDAO;
    @Inject
    private GroupDAO groupDAO;
    @Inject
    private KweetDAO kweetDAO;

    @PostConstruct
    public void init(){
        try {
            User user1 = new User("test1@test.nl", "Test123", "Test1");
            User user2 = new User("test2@test.nl", "Test123", "Test2");
            User user3 = new User("test3@test.nl", "Test123", "Test3");

            userDAO.createUser(user1);
            userDAO.createUser(user2);
            userDAO.createUser(user3);

            groupDAO.create(new Group(user1.getEmail(), "ROLE_USER"));
            groupDAO.create(new Group(user2.getEmail(), "ROLE_ADMIN"));
            groupDAO.create(new Group(user3.getEmail(), "ROLE_MOD"));

            kweetDAO.create(new Kweet(LocalDateTime.now(), "Hoi", user1));
            kweetDAO.create(new Kweet(LocalDateTime.now(), "32524t", user3));
            kweetDAO.create(new Kweet(LocalDateTime.now(), "twet", user1));
            kweetDAO.create(new Kweet(LocalDateTime.now(), "sfgd", user2));
            kweetDAO.create(new Kweet(LocalDateTime.now(), "gwet", user1));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
