package service;

import dao.kweet.KweetDAO;
import dao.user.UserDAO;
import domain.Kweet;
import domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class KweetService {

    @Inject
    private KweetDAO kweetDAO;

    @Inject
    private UserDAO userDAO;

    /**
     * Used for testing only!
     *
     * @param dao
     */
    public void setKweetDAO(KweetDAO dao) {
        kweetDAO = dao;
    }

    /**
     * Used for testing only!
     *
     * @param dao
     */
    public void setUserDAO(UserDAO dao) {
        userDAO = dao;
    }

    /**
     * saves a kweet and add it to the user
     * @param kweet
     */
    public void createKweet(Kweet kweet){
        User user = kweet.getUser();
        user.addKweet(kweet);
        kweetDAO.create(kweet);
        userDAO.editUser(user);
    }

    /**
     * Updates a kweet
     * @param kweet
     */
    public void editKweet(Kweet kweet){
        kweetDAO.edit(kweet);
    }

    /**
     * Removes kweet
     * @param kweet
     */
    public void removeKweet(Kweet kweet){
        User user = kweet.getUser();
        user.removeKweet(kweet);
        userDAO.editUser(user);
        kweetDAO.removeKweet(kweet);
    }

    /**
     * retrieves all kweets
     * @return
     */
    public List<Kweet> getAllKweets(){
        return kweetDAO.findAll();
    }

    /**
     * Retrieves a kweet by id
     * @param id
     * @return
     */
    public Kweet getKweetById(long id){
        return kweetDAO.get(id);
    }

    /**
     * Retrieves all kweets from an email
     * @param email
     * @return
     */
    public List<Kweet> getKweetsByEmail(String email){
        return kweetDAO.getKweetsByEmail(email);
    }
}
