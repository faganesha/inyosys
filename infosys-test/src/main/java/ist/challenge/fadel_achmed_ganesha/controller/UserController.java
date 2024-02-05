package ist.challenge.fadel_achmed_ganesha.controller;

import ist.challenge.fadel_achmed_ganesha.entity.UserEntity;
import ist.challenge.fadel_achmed_ganesha.request.EditUserRequest;
import ist.challenge.fadel_achmed_ganesha.service.EditUserService;
import ist.challenge.fadel_achmed_ganesha.service.LoginService;
import ist.challenge.fadel_achmed_ganesha.service.RegistrasiService;
import ist.challenge.fadel_achmed_ganesha.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static ist.challenge.fadel_achmed_ganesha.constant.Constant.*;
import static ist.challenge.fadel_achmed_ganesha.util.UserUtil.checkUsernameAndPasswordNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final LoginService loginService;
    private final RegistrasiService registrasiService;
    private final UserService userService;
    private final EditUserService editUserService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserEntity user) {
        String isEmpty = checkUsernameAndPasswordNull(user);

        if (isEmpty != null) {
            return new ResponseEntity<>(isEmpty, HttpStatus.BAD_REQUEST);
        }

        String response = loginService.login(user);

        if (response.equals(SUKSES_LOGIN)) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/registrasi")
    public ResponseEntity<String> registrasi(@RequestBody UserEntity user) {
        String isEmpty = checkUsernameAndPasswordNull(user);

        if (isEmpty != null) {
            return new ResponseEntity<>(isEmpty, HttpStatus.BAD_REQUEST);
        }

        String response = registrasiService.registrasi(user);

        if (response.equals(REGISTRASI_BERHASIL)){
            return new ResponseEntity<>(response, HttpStatus.CREATED) ;
        } else if (response.equals(USERNAME_TERPAKAI)){
            return new ResponseEntity<>(response, HttpStatus.CONFLICT) ;
        } else {
            return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<UserEntity>> users() {
        List<UserEntity> allUsers =  userService.findAllUser();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);

    }

    @GetMapping("/search/")
    public ResponseEntity<UserEntity> users(@RequestParam("username") String username) {
        return userService.findUser(username);
    }

    @PutMapping("/edit")
    public ResponseEntity<String> editUser(@RequestBody EditUserRequest request) {
        String response = editUserService.edit(request);

        if(response.equals(USERNAME_TERPAKAI)){
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } else if (response.equals(USER_PASSWORD_SAMA)){
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else if (response.equals(EDIT_BERHASIL)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
