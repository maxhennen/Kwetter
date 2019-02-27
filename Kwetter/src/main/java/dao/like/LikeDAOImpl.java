package dao.like;

import domain.Like;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class LikeDAOImpl implements LikeDAO {

    @PersistenceContext(unitName = "kwetter-db")
    private EntityManager em;

    @Override
    public void create(Like l) {
        em.persist(l);
    }

    @Override
    public void removeLike(Like l) {
        em.remove(l);
    }

    @Override
    public List<Like> findAll() {
        return (List<Like>) em.createQuery("SELECT L FROM Like L").getResultList();
    }

    @Override
    public Like get(long id) {
        Query q = em.createQuery("SELECT L FROM Like L WHERE L.id = :id");
        q.setParameter("id", id);
        try {
            return (Like)q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
