package dao.user;

import domain.User;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Default
public class UserDAOImpl implements UserDAO{

    @PersistenceContext(unitName = "NewPersistenceUnit")
    private EntityManager em;

    @Override
    public void createUser(User u) {
        em.persist(u);
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
        return (List<User>) em.createQuery("SELECT U FROM User U").getResultList();
    }

    @Override
    public User findUserByUserName(String username) {
        Query q = em.createQuery("SELECT U FROM User u WHERE U.username = :username");
        q.setParameter("username", username);
        try {
            return (User) q.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User findUserByID(long id) {
        Query q = em.createQuery("SELECT U FROM User u WHERE U.id = :id");
        q.setParameter("id", id);
        try {
            return (User) q.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> getAllFollowing(User u) {
        User found = findUserByUserName(u.getUsername());
        return found.getFollowing();
    }

    @Override
    public List<User> getAllFollowers(User u) {
        User found = findUserByUserName(u.getUsername());
        return found.getFollowers();
    }
}
