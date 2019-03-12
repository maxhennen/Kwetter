package domain;

import org.junit.Before;
import org.junit.Test;
import service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class UserTest {

    User testUser1;
    User testUser2;

    Kweet testKweet1;

    @Before
    public void setUp() throws Exception {

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
        details1.setLocation(location2);
        testUser2.setDetails(details2);
        testUser2.setUsername("test12");
        testUser2.setId(1);
        testUser2.setFollowers(new ArrayList<User>());
        testUser2.setFollowing(new ArrayList<User>());
        testUser2.setKweets(new ArrayList<Kweet>());

        testKweet1 = new Kweet();
        testKweet1.setId(1);
        testKweet1.setDateTime(LocalDateTime.now());
        testKweet1.setContent("blablabla");
        testKweet1.setUser(testUser1);
        testKweet1.setLikes(new ArrayList<Like>());
    }

    @Test
    public void addFollowing() {
        testUser1.addFollowing(testUser2);

        assertTrue(testUser2.getFollowers().contains(testUser1));
        assertTrue(testUser1.getFollowing().contains(testUser2));
    }

    @Test
    public void removeFollowing() {
        testUser1.removeFollowing(testUser2);
        assertFalse(testUser2.getFollowers().contains(testUser1));
        assertFalse(testUser1.getFollowing().contains(testUser2));
    }

    @Test
    public void removeFollower() {
        testUser1.addFollowing(testUser2);

        testUser2.removeFollower(testUser1);

        assertFalse(testUser2.getFollowers().contains(testUser1));
        assertFalse(testUser1.getFollowing().contains(testUser2));
    }

    @Test
    public void addKweet() {
        testUser1.addKweet(testKweet1);

        assertTrue(testUser1.getKweets().contains(testKweet1));
    }

    @Test
    public void removeKweet() {
        testUser1.removeKweet(testKweet1);
        assertFalse(testUser1.getKweets().contains(testKweet1));
    }
}