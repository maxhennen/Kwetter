package dao.user;

import domain.Follower;
import domain.Following;
import domain.User;
import utils.AuthenticationUtils;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Stateless
@Default
public class UserDAOImpl implements UserDAO{

    @PersistenceContext(unitName = "NewPersistenceUnit")
    private EntityManager em;

    @Override
    public void createUser(User u) {
        try {
            u.setPassword(AuthenticationUtils.encodeSHA256(u.getPassword()));
            em.persist(u);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editUser(User u) {
        em.merge(u);
    }

    @Override
    public void removeUser(User u) {
        em.remove(u);
    }

    @Override
    public List<User> getAllUsers() {
        return  em.createNamedQuery("User.findAll").getResultList();
    }

    @Override
    public User findUserByEmail(String email) {
        try {
            return (User)em.createNamedQuery("User.findByEmail")
                    .setParameter("email", email).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> getAllFollowing(User u) {
        return (List<User>) em.createNamedQuery("User.getFollowing")
                .setParameter("email", u.getEmail()).getResultList();
    }

    @Override
    public List<User> getAllFollowers(User u) {
        return (List<User>) em.createNamedQuery("User.getFollowers")
                .setParameter("email", u.getEmail()).getResultList();
    }

    @Override
    public void addFollower(String emailFollower, String emailFollowing) {
        Follower follower = new Follower(emailFollowing, emailFollower);
        Following following = new Following(emailFollower, emailFollowing);

        em.persist(follower);
        em.persist(following);
    }

    @Override
    public void removeFollower(Follower follower, Following following) {
        em.remove(follower);
        em.remove(following);
    }

    @Override
    public void removeFollowing(Following following, Follower follower) {
        em.remove(follower);
        em.remove(following);
    }

    @Override
    public Follower getFollowerByEmail(String email) {
        return (Follower)em.createNamedQuery("User.getFollowerByEmail")
                .setParameter("email", email);
    }

    @Override
    public Following getFollowingByEmail(String email) {
        return (Following) em.createNamedQuery("User.getFollowingByEmail")
                .setParameter("email", email);
    }

}
