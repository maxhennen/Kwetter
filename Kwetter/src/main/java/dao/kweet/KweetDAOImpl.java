package dao.kweet;


import domain.Kweet;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@ApplicationScoped
public class KweetDAOImpl implements KweetDAO {


    @PersistenceContext(unitName = "kwetter-db")
    private EntityManager em;

    @Override
    public void create(Kweet k) {
        em.persist(k);
    }

    @Override
    public void edit(Kweet k) {
        em.merge(k);
    }

    @Override
    public void removeKweet(Kweet k) {
        em.remove(k);
    }

    @Override
    public List<Kweet> findAll() {
        return (List<Kweet>)em.createQuery("SELECT K from Kweet K").getResultList();
    }

    @Override
    public Kweet get(long id) {
        Query q = em.createQuery("SELECT K FROM Kweet K where k.id = :id");
        q.setParameter("id", id);
        try {
            return (Kweet) q.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Kweet> getKweetsByUsername(String username) {
        Query q = em.createQuery("SELECT K FROM Kweet K INNER JOIN User u ON K.user.id = U.id where U.username = :username");
        q.setParameter("username", username);
        return (List<Kweet>) q.getResultList();
    }

}
