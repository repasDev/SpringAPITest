package demo.dao;

import demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT s FROM User s WHERE s.userName = ?1")
    Optional<User> findUserByUserName(String userName);

    @Modifying
    @Query("UPDATE User u set u.timesPlayed = u.timesPlayed + 1 WHERE u.id = userId")
    Integer incrementUserPlayTime(Long userId);
}

