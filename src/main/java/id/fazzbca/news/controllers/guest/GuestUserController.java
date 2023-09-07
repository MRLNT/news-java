package id.fazzbca.news.controllers.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.fazzbca.news.payloads.req.UserRequest;
import id.fazzbca.news.services.user.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/guest/user")
public class GuestUserController {
    @Autowired
    UserService userService;

    // register user
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserRequest request, @RequestParam(value = "role", defaultValue = "") String role){
        ResponseEntity<?> responseEntity = userService.addUserService(request, role);
        return responseEntity;
    }

    // login
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserRequest request){
        return userService.getLoginByUsernameService(request);
    }

    // forgot password dan sekalian ganti
    @PutMapping("/forgotpass/{id}")
    public ResponseEntity<?> updatePassword(@PathVariable(value = "id") String id, @RequestBody UserRequest request){
        return userService.updatePasswordService(id, request);
    }
}