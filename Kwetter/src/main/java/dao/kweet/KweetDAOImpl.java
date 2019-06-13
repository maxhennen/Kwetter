package dao.kweet;


import domain.Kweet;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Stateless
@Default
public class KweetDAOImpl implements KweetDAO {


    @PersistenceContext(unitName = "NewPersistenceUnit")
    private EntityManager em;

    @Override
    public Kweet create(Kweet k) {
        em.persist(k);
        return k;
    }

    @Override
    public Kweet edit(Kweet k) {
        em.merge(k);
        return k;
    }

    @Override
    public boolean removeKweet(Kweet k) {
        if(!em.contains(k)){
            k = em.merge(k);
        }
        em.remove(k);
        return true;
    }

    @Override
    public List<Kweet> findAll() {
        return em.createNamedQuery("Kweet.findAll").getResultList();
    }

    @Override
    public Kweet get(long id) {
        return em.find(Kweet.class, id);
    }

    @Override
    public List<Kweet> getKweetsByEmail(String email) {

        return em.createNamedQuery("Kweet.getKweetsByEmail")
                .setParameter("email", email).getResultList();

    }

}
