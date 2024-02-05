package ist.challenge.fadel_achmed_ganesha.util;

import ist.challenge.fadel_achmed_ganesha.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserUtil {
    public static String checkUsernameAndPasswordNull(UserEntity user){
        Boolean isUsernameEmpty = false;
        Boolean isPasswordEmpty = false;

        if (user.getUsername() == null || user.getUsername().trim().isEmpty()){
            isUsernameEmpty = true;
        } else if (user.getPassword() == null || user.getPassword().trim().isEmpty()){
            isPasswordEmpty = true;
        }

        if (isUsernameEmpty && isPasswordEmpty){
            return "Username dan Password kosong";
        } else if (isPasswordEmpty){
            return "Password kosong";
        } else if (isUsernameEmpty){
            return "Username kosong";
        }

        return null;
    }
}
