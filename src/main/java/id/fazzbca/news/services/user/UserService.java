package id.fazzbca.news.services.user;

import org.springframework.http.ResponseEntity;

import id.fazzbca.news.payloads.req.UserRequest;

public interface UserService {
    // create user
    ResponseEntity<?> addUserService(UserRequest request);

    // get all user
    ResponseEntity<?> getAllUsers();

    // login
    ResponseEntity<?> getLoginByUsernameService(UserRequest request);

    // update password
    ResponseEntity<?> updatePasswordService(String id, UserRequest request);
}
