package dao.user;

import domain.Follower;
import domain.Following;
import domain.Token;
import domain.User;
import utils.AuthenticationUtils;

import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Default
public class UserDAOImpl implements UserDAO{

    @PersistenceContext(unitName = "NewPersistenceUnit")
    private EntityManager em;

    private static String emailParameter = "email";

    @Override
    public User createUser(User u) {
        u.setPassword(AuthenticationUtils.encodeSHA256(u.getPassword()));
        em.persist(u);
        return u;
    }

    @Override
    public User editUser(User u) {
        em.merge(u);
        return u;
    }

    @Override
    public boolean removeUser(User u) {
        em.remove(u);
        return em.find(User.class, u) == null;
    }

    @Override
    public List<User> getAllUsers() {
        return  em.createNamedQuery("User.findAll").getResultList();
    }

    @Override
    public User findUserByEmail(String email) {
        return (User)em.createNamedQuery("User.findByEmail")
                .setParameter(emailParameter, email).getSingleResult();
    }

    @Override
    public List<User> getAllFollowing(User u) {
        return (List<User>) em.createNamedQuery("User.getFollowing")
                .setParameter(emailParameter, u.getEmail()).getResultList();
    }

    @Override
    public List<User> getAllFollowers(User u) {
        return (List<User>) em.createNamedQuery("User.getFollowers")
                .setParameter(emailParameter, u.getEmail()).getResultList();
    }

    @Override
    public Follower addFollower(String emailFollower, String emailFollowing) {
        Follower follower = new Follower(emailFollowing, emailFollower);
        Following following = new Following(emailFollower, emailFollowing);

        em.persist(follower);
        em.persist(following);

        Follower follower1 = em.find(Follower.class, follower.getId());
        Following following1 = em.find(Following.class, following.getId());

        em.persist(follower1);
        em.persist(following1);

        return follower;
    }

    @Override
    public boolean removeFollower(Follower follower, Following following) {
        return removeFollowingFollower(following, follower);
    }

    @Override
    public boolean removeFollowing(Following following, Follower follower) {
        return removeFollowingFollower(following, follower);
    }

    private boolean removeFollowingFollower(Following following, Follower follower){

        em.remove(follower);
        em.remove(following);

        Following following1 = em.find(Following.class, following);
        Follower follower1 = em.find(Follower.class, follower);

        return follower1 == null && following1 == null;
    }

    @Override
    public Follower getFollowerByEmail(String email) {
        return (Follower)em.createNamedQuery("User.getFollowerByEmail")
                .setParameter(emailParameter, email);
    }

    @Override
    public Following getFollowingByEmail(String email) {
        return (Following) em.createNamedQuery("User.getFollowingByEmail")
                .setParameter(emailParameter, email);
    }

    @Override
    public User login(String email, String password) {
        return (User) em.createNamedQuery("User.login")
                .setParameter(emailParameter, email)
                .setParameter("password", password)
                .getSingleResult();
    }

    @Override
    public Token addToken(Token token) {
        em.persist(token);
        return token;
    }

    @Override
    public Token getToken(String token) {
        return (Token) em.createNamedQuery("User.getToken")
                .setParameter("token", token).getSingleResult();
    }

    @PreDestroy
    public void destroy(){
        em.close();
    }

}
