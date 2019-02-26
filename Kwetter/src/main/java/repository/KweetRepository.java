package repository;

import domain.Kweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KweetRepository extends JpaRepository<Kweet, Long> {

    List<Kweet> findByUserUsername(@Param("username")String username);

}
