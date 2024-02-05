package ist.challenge.fadel_achmed_ganesha.repository;


import ist.challenge.fadel_achmed_ganesha.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsernameAndPassword(String username, String password);
    Optional<UserEntity> findByUsernameLike(String username);
    Optional<UserEntity> findByUsername(String username);
}
