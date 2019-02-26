package service;

import domain.user.User;
import repository.UserRepository;

import java.util.List;

public interface UserService {

    /**
     * Gets all users from database
     * @return List<User> List of all users
     */
    List<User> findAll();

    /**
     * Saves an user into the database
     * @param user the user to be save
     */
    void save(User user);

    /**
     * Gets an user by id
     * @param id ID of the user that you want
     * @return the found user
     */
    User findOne(Long id);

    /**
     * Deletes an user from the database
     * @param id Id of the user to be deleted
     */
    void delete(Long id);

    /**
     * Finds the users by an username
     * @param username Username of the user you want
     * @return List<User> Users with the username
     */
    List<User> findByUsername(String username);

}
