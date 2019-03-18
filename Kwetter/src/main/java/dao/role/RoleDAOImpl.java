package dao.role;


import domain.Role;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@Default
public class RoleDAOImpl implements RoleDAO{

    @PersistenceContext(unitName = "NewPersistenceUnit")
    private EntityManager em;

    @Override
    public void create(Role r) {
        em.persist(r);
    }

    @Override
    public Role getByName(String name) {
        Query q = em.createQuery("SELECT R FROM Role R WHERE R.role = :name");
        q.setParameter("name", name);

        try{
            return (Role) q.getSingleResult();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
