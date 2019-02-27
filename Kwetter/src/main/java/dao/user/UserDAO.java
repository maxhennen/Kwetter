package dao.user;

import domain.user.User;

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
     * Return user object with given username
     * @param userName
     * @return
     */
    User findUserByUserName(String userName);

    /**
     * Retrieve a user by given id
     * @param id
     * @return
     */
    User findUserByID(long id);

    /**
     * Retrieves all the users that the given username follows!
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

}
