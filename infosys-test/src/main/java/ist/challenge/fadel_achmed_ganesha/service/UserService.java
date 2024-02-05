package ist.challenge.fadel_achmed_ganesha.service;

import ist.challenge.fadel_achmed_ganesha.entity.UserEntity;
import ist.challenge.fadel_achmed_ganesha.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public ResponseEntity<UserEntity> findUser(String username) {
        String likeUserName = "%"+username+"%";
        UserEntity user = userRepository.findByUsernameLike(likeUserName).orElse(null);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public List<UserEntity> findAllUser() {
        return userRepository.findAll();
    }
}
