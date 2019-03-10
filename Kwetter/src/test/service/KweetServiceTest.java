package service;

import dao.kweet.KweetDAOTest;
import dao.user.UserDAOTest;
import domain.Kweet;
import domain.Like;
import domain.Details;
import domain.Location;
import domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class KweetServiceTest {

    KweetService kweetService;

    @Mock
    KweetDAOTest kweetDAOTest;

    @Mock
    UserDAOTest userDAOTest;

    User testUser1;

    Kweet testKweet1;
    Kweet testKweet2;


    @Before
    public void setUp() throws Exception {
        kweetService = new KweetService();
        kweetService.setKweetDAO(kweetDAOTest);
        kweetService.setUserDAO(userDAOTest);

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

        testKweet1 = new Kweet();
        testKweet1.setId(1);
        testKweet1.setDateTime(LocalDateTime.now());
        testKweet1.setContent("blablabla");
        testKweet1.setUser(testUser1);
        testKweet1.setLikes(new ArrayList<Like>());
    }


    @Test
    public void createKweet() {
        kweetService.createKweet(testKweet1);
        verify(kweetDAOTest, Mockito.times(1)).create(testKweet1);
    }

    @Test
    public void editKweet() {
        testKweet1.setContent("hallo");
        kweetService.editKweet(testKweet1);
        verify(kweetDAOTest, Mockito.times(1)).edit(testKweet1);
    }

    @Test
    public void removeKweet() {
        kweetService.removeKweet(testKweet1);
        verify(kweetDAOTest, Mockito.times(1)).removeKweet(testKweet1);
    }

    @Test
    public void getAllKweets() {
        kweetService.getAllKweets();
        verify(kweetDAOTest, Mockito.times(1)).findAll();
    }

    @Test
    public void getKweetById() {
        kweetService.getKweetById(1);
        verify(kweetDAOTest, Mockito.times(1)).get(1);
    }

    @Test
    public void getKweetsByUsername() {
        kweetService.getKweetsByUsername("test12");
        verify(kweetDAOTest, Mockito.times(1)).getKweetsByUsername("test12");
    }
}