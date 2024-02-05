package ist.challenge.fadel_achmed_ganesha.service;

import ist.challenge.fadel_achmed_ganesha.entity.UserEntity;
import ist.challenge.fadel_achmed_ganesha.repository.UserRepository;
import ist.challenge.fadel_achmed_ganesha.request.EditUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static ist.challenge.fadel_achmed_ganesha.constant.Constant.*;

@Service
@RequiredArgsConstructor
public class EditUserService {
    private final UserRepository userRepository;

    public String edit(EditUserRequest user){
        boolean checkUser = userRepository.findByUsername(user.getNewUsername()).isPresent();
        if(checkUser){
            return USERNAME_TERPAKAI;
        }

        if(user.getNewPassword().equals(user.getOldPassword())){
            return USER_PASSWORD_SAMA;
        }

        UserEntity userDatum = userRepository.findByUsername(user.getOldUsername()).orElse(null);

        if (userDatum != null){
            userDatum.setUsername(user.getNewUsername());
            userDatum.setPassword(user.getNewPassword());
            userRepository.save(userDatum);
            return EDIT_BERHASIL;
        }
      return "";
    }
}
