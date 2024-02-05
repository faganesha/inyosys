package ist.challenge.fadel_achmed_ganesha.service;

import ist.challenge.fadel_achmed_ganesha.entity.UserEntity;
import ist.challenge.fadel_achmed_ganesha.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static ist.challenge.fadel_achmed_ganesha.constant.Constant.GAGAL_LOGIN;
import static ist.challenge.fadel_achmed_ganesha.constant.Constant.SUKSES_LOGIN;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;

    public String login(UserEntity user) {
        UserEntity userResponse = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (userResponse != null) {
            return SUKSES_LOGIN;
        } else {
            return GAGAL_LOGIN;
        }
    }
}
