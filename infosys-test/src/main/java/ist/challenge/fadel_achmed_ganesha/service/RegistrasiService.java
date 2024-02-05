package ist.challenge.fadel_achmed_ganesha.service;

import ist.challenge.fadel_achmed_ganesha.entity.UserEntity;
import ist.challenge.fadel_achmed_ganesha.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static ist.challenge.fadel_achmed_ganesha.constant.Constant.REGISTRASI_BERHASIL;
import static ist.challenge.fadel_achmed_ganesha.constant.Constant.USERNAME_TERPAKAI;


@Service
@RequiredArgsConstructor
public class RegistrasiService {
    private final UserRepository userRepository;

    public String registrasi(UserEntity user) {
        boolean isUserExists = userRepository.findByUsername(user.getUsername()).isPresent();

        if (isUserExists){
           return USERNAME_TERPAKAI;
        }

        UserEntity response = userRepository.save(user);

        if (response != null){
            return REGISTRASI_BERHASIL;
        }

        return "";
    }
}
