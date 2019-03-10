package service;

import dao.user.UserDAOTest;
import domain.Kweet;
import domain.Details;
import domain.Location;
import domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    UserService userService;

    @Mock
    UserDAOTest userDAOTest;

    User testUser1;
    User testUser2;

    @Before
    public void setUp() throws Exception {

        userService = new UserService();
        userService.setUserDAO(userDAOTest);

        testUser1 = new User();
        Location location1 = new Location("NL", "ËHV", "Schoolstraat", "3");
        Details details1 = new Details("bio", "website");
        details1.setLocation(location1);
        testUser1.setDetails(details1);
        testUser1.setUsername("test1");
        testUser1.setId(1);
        testUser1.setFollowers(new ArrayList<User>());
        testUser1.setFollowing(new ArrayList<User>());
        testUser1.setKweets(new ArrayList<Kweet>());

        testUser2 = new User();
        Location location2 = new Location("DE", "BER", "Joa", "4a");
        Details details2 = new Details("bio1", "website2");
        details1.setLocation(location1);
        testUser2.setDetails(details1);
        testUser2.setUsername("test12");
        testUser2.setId(1);
        testUser2.setFollowers(new ArrayList<User>());
        testUser2.setFollowing(new ArrayList<User>());
        testUser2.setKweets(new ArrayList<Kweet>());
    }


    @Test
    public void createUser() {
        userService.createUser(testUser1);
        verify(userDAOTest).createUser(testUser1);
    }

    @Test
    public void editUser() {
        testUser1.setUsername("max");
        userService.editUser(testUser1);
        verify(userDAOTest).editUser(testUser1);
    }

    @Test
    public void getFollowing() {
        userService.getFollowing(testUser1);
        verify(userDAOTest).getAllFollowing(testUser1);
    }

    @Test
    public void getFollowers() {
        userService.getFollowers(testUser1);
        verify(userDAOTest).getAllFollowers(testUser1);
    }

    @Test
    public void removeUser() {
        userService.removeUser(testUser1);
        verify(userDAOTest).removeUser(testUser1);
    }

    @Test
    public void getAllUsers() {
        userService.getAllUsers();
        verify(userDAOTest).getAllUsers();
    }

    @Test
    public void findByUsername() {
        userService.findByUsername(testUser1.getUsername());
        verify(userDAOTest).findUserByUserName(testUser1.getUsername());
    }

    @Test
    public void findByID() {
        userService.findByID(1);
        verify(userDAOTest).findUserByID(1);
    }

    @Test
    public void followUser() {
        userService.followUser(testUser1, testUser2);
    }

    @Test
    public void removeFollowing() {
        userService.removeFollowing(testUser1, testUser2);
    }

    @Test
    public void removeFollower() {
        userService.removeFollower(testUser2, testUser1);
    }

    @Test
    public void editDetails() {
        testUser1.getDetails().setBio("hallo");
        userService.editDetails(testUser1.getUsername(), testUser1.getDetails());
    }

    @Test
    public void editLocation() {
        testUser1.getDetails().getLocation().setCity("Hoi");
        userService.editLocation(testUser1.getUsername(), testUser1.getDetails().getLocation());
    }
}