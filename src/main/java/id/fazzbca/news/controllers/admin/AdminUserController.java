package id.fazzbca.news.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.fazzbca.news.services.user.UserService;

@RestController
@RequestMapping("/admin/user")
public class AdminUserController {
    @Autowired
    UserService userService;

    // get all users
    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        return userService.getAllUsers();
    }
}
