package service;

import dao.kweet.KweetDAOImpl;
import dao.user.UserDAO;
import dao.user.UserDAOImpl;
import domain.user.Details;
import domain.user.Location;
import domain.user.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class UserService {

    @Inject
    private UserDAO userDAO;


    /**
     * Used for testing only!
     *
     * @param dao
     */
    public void setUserDAO(UserDAO dao) {
        userDAO = dao;
    }

    /**
     * Creates a new user
     * @param user
     */
    public void createUser(User user){

        if(userDAO.findUserByUserName(user.getUsername()) == null){
            userDAO.createUser(user);
        }
    }

    /**
     * Updates an user
     * @param user
     */
    public void editUser(User user){
        if(userDAO.findUserByID(user.getId()) != null){
            userDAO.editUser(user);
        }
    }

    /**
     * Retrieves the users who the given user follows
     * @param user
     * @return List<User>
     */
    public List<User> getFollowing(User user){
        return userDAO.getAllFollowing(user);
    }

    /**
     * Retrieves the users who follows the given user
     * @param user
     * @return
     */
    public List<User> getFollowers(User user){
        return userDAO.getAllFollowers(user);
    }

    /**
     * Removes the given user
     * @param user
     */
    public void removeUser(User user){
        userDAO.removeUser(user);
    }

    /**
     * Retrieves all users
     * @return
     */
    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }

    /**
     * Retrieves a user by username
     * @param username
     * @return
     */
    public User findByUsername(String username){
        return userDAO.findUserByUserName(username);
    }

    /**
     * Retrieves a user by id
     * @param id
     * @return
     */
    public User findByID(long id){
        return userDAO.findUserByID(id);
    }

    /**
     * Follow another user
     * @param follower person following someone
     * @param following person being followed
     */
    public void followUser(User follower, User following){
        follower.addFollowing(following);
        userDAO.editUser(follower);
        userDAO.editUser(following);
    }

    /**
     * Stop following someone
     * @param follower
     * @param following
     */
    public void removeFollowing(User follower, User following){
        follower.removeFollowing(following);
        userDAO.editUser(follower);
        userDAO.editUser(following);
    }

    /**
     * Remove a follower
     * @param follower
     * @param following
     */
    public void removeFollower(User follower, User following){
        follower.removeFollower(following);
        userDAO.editUser(follower);
        userDAO.editUser(following);
    }

    /**
     * Change the bio
     * @param username
     * @param details
     */
    public void editDetails(String username, Details details){
        User user = findByUsername(username);
        user.setDetails(details);
        userDAO.editUser(user);
    }

    /**
     * Change location
     * @param username
     * @param location
     */
    public void editLocation(String username, Location location){
        User user = findByUsername(username);
        user.getDetails().setLocation(location);
        userDAO.editUser(user);
    }


}
