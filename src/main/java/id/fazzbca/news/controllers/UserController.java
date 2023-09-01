package id.fazzbca.news.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.fazzbca.news.payloads.req.UserRequest;
import id.fazzbca.news.services.user.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    // register user
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserRequest request){
        ResponseEntity<?> responseEntity = userService.addUserService(request);
        return responseEntity;
    }

    // get all users
    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        return userService.getAllUsers();
    }

    // login
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserRequest request){
        return userService.getLoginByUsernameService(request);
    }

    // forgot password

}
