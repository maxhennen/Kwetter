package dao.user;

import domain.Follower;
import domain.Following;
import domain.User;

import java.util.List;


public interface UserDAO {

    /**
     * Creates a new User object
     *
     * @param u
     */
    void createUser(User u);

    /**
     * Edits a User object
     *
     * @param u
     */
    void editUser(User u);

    /**
     * Removes a User object
     *
     * @param u
     */
    void removeUser(User u);

    /**
     * Retrieves all users from the kwetter application
     *
     * @return
     */
    List<User> getAllUsers();


    /**
     * Return user object with given email
     * @param email
     * @return
     */
    User findUserByEmail(String email);

    /**
     * Retrieves all the users that the given email follows!
     * @param u
     * @return
     */
    List<User> getAllFollowing(User u);


    /**
     * Retrieve all the users who follow the given user
     * @param u
     * @return
     */
    List<User> getAllFollowers(User u);

    /**
     * Add follower
     * @param emailFollower
     * @param emailFollowing
     */
    void addFollower(String emailFollower, String emailFollowing);

    /**
     * Remove follower
     * @param follower
     * @param following
     */
    void removeFollower(Follower follower, Following following);

    /**
     * Remove following
     * @param following
     * @param follower
     */
    void removeFollowing(Following following, Follower follower);

    /**
     * Get a follower by email
     * @param email
     * @return
     */
    Follower getFollowerByEmail(String email);

    /**
     * Get a following by email
     * @param email
     * @return
     */
    Following getFollowingByEmail(String email);

}
