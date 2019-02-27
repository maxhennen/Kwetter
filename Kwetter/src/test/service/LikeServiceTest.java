package service;

import dao.kweet.KweetDAOTest;
import dao.like.LikeDAO;
import dao.like.LikeDAOTest;
import dao.user.UserDAOTest;
import domain.Kweet;
import domain.Like;
import domain.user.Details;
import domain.user.Location;
import domain.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import java.time.LocalDateTime;
import java.util.ArrayList;


@RunWith(MockitoJUnitRunner.class)
public class LikeServiceTest {

    LikeService likeService;

    @Mock
    LikeDAOTest likeDAOTest;

    User testUser1;
    Kweet testKweet1;
    Like testLike1;

    @Before
    public void setUp() throws Exception {

        likeService = new LikeService();
        likeService.setLikeDAO(likeDAOTest);

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

        testLike1 = new Like();
        testLike1.setDateTime(LocalDateTime.now());
        testLike1.setId(1);
        testLike1.setUser(testUser1);
        testLike1.setKweet(testKweet1);
    }


    @Test
    public void createLike() {
        likeService.createLike(testLike1);
        verify(likeDAOTest, Mockito.times(1)).create(testLike1);
    }

    @Test
    public void removeLike() {
        likeService.removeLike(testLike1);
        verify(likeDAOTest, Mockito.times(1)).removeLike(testLike1);
    }

    @Test
    public void findALlLikes() {
        likeService.findALlLikes();
        verify(likeDAOTest).findAll();
    }

    @Test
    public void getById() {
        likeService.getById(1);
        verify(likeDAOTest, Mockito.times(1)).get(1);
    }
}