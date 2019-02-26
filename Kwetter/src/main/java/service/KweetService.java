package service;

import domain.Kweet;

import java.util.List;

public interface KweetService {

    /**
     * Gets all kweets from the database
     * @return List<Kweet> List of all kweets in the database
     */
    List<Kweet> findAll();

    /**
     * Saves a kweet to the database
     * @param kweet The kweet that has to be saved
     */
    void save(Kweet kweet);

    /**
     * Gets a kweet by a specific id
     * @param id Id of the kweet that has to be found
     * @return The found kweet
     */
    Kweet findOneById(long id);

    /**
     * Deletes a kweet by  id
     * @param id Id of the to be deleted kweet
     */
    void delete (long id);

    /**
     * Gets all kweets by an username
     * @param username Username from the desired kweets
     * @return List<Kweet> Kweets by specific username
     */
    List<Kweet>  findByUserUsername(String username);
}
