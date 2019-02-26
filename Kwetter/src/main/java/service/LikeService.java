package service;

import domain.Like;

import java.util.List;

public interface LikeService {

    /**
     * Gets all likes from the database
     * @return List<Like> List of all likes in the database
     */
    List<Like> findAll();

    /**
     * Stores a like in the database
     * @param like The like to be stored
     */
    void save(Like like);

    /**
     * Gets a like by a specific id
     * @param id The id of the like you want
     * @return The found like
     */
    Like findOneById(Long id);

    /**
     * Deletes a like from the database
     * @param id Id of the like you want to remove
     */
    void delete(long id);
}
