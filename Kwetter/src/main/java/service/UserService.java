package service;

import dao.group.GroupDAO;
import dao.user.UserDAO;
import domain.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class UserService {

    @Inject
    private UserDAO userDAO;

    @Inject
    private GroupDAO groupDAO;

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
    public void createUser(User user) throws NullPointerException {

        System.out.println(userDAO.findUserByEmail(user.getEmail()));

       if(userDAO.findUserByEmail(user.getEmail()) == null){
            groupDAO.create(new Group(user.getEmail(), "ROLE_USER"));
            userDAO.createUser(user);

       }
    }

    /**
     * Updates an user
     * @param user
     */
    public void editUser(User user){
        if(userDAO.findUserByEmail(user.getEmail()) != null){
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
     * Retrieves a user by email
     * @param email
     * @return
     */
    public User findByEmail(String email){
        return userDAO.findUserByEmail(email);
    }

    /**
     * Follow another user
     * @param follower person following someone
     * @param following person being followed
     */
    public void followUser(User follower, User following){
        userDAO.addFollower(follower.getEmail(), following.getEmail());
    }

    /**
     * Stop following someone
     * @param follower
     * @param following
     */
    public void removeFollowing(Follower follower, Following following){
        userDAO.removeFollower(follower, following);
    }

    /**
     * Remove a follower
     * @param follower
     * @param following
     */
    public void removeFollower(Follower follower, Following following){
        userDAO.removeFollowing(following, follower);
    }

    /**
     * Get follower by email
     * @param email
     * @return
     */
    public Follower getFollowerByEmail(String email){
        return userDAO.getFollowerByEmail(email);
    }

    /**
     * Get following by email
     * @param email
     * @return
     */
    public Following getFollowingByEmail(String email){
        return userDAO.getFollowingByEmail(email);
    }

    /**
     * Change the bio
     * @param email
     * @param details
     */
    public void editDetails(String email, Details details){
        User user = findByEmail(email);
        user.setDetails(details);
        userDAO.editUser(user);
    }

    /**
     * Change location
     * @param email
     * @param location
     */
    public void editLocation(String email, Location location){
        User user = findByEmail(email);
        user.getDetails().setLocation(location);
        userDAO.editUser(user);
    }

}
