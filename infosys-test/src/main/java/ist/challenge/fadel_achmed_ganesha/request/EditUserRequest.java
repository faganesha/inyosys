package ist.challenge.fadel_achmed_ganesha.request;

import lombok.Data;

@Data
public class EditUserRequest {
    private String oldUsername;
    private String oldPassword;
    private String newUsername;
    private String newPassword;
}
