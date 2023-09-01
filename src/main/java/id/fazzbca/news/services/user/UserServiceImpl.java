package id.fazzbca.news.services.user;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import id.fazzbca.news.models.User;
import id.fazzbca.news.payloads.req.UserRequest;
import id.fazzbca.news.payloads.res.ResponseHandler;
import id.fazzbca.news.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<?> addUserService(UserRequest request) {
        // cek inputan
        if (request.getUsername() == null || request.getUsername() == "") {
            throw new IllegalArgumentException("username dibutuhkan");
        }

        // cek apakah username sudah ada
        User existingUser = userRepository.findByUsername(request.getUsername());
        if (existingUser != null) {
            throw new IllegalArgumentException("Username sudah ada");
        }

        // simpan user baru
        User newUser = new User(request.getUsername(), request.getPassword(), request.getRole(), request.getEmail());
        userRepository.save(newUser);
        return ResponseHandler.responseMessage(201, "User berhasil didaftarkan", true);
    }

    @Override
    public ResponseEntity<?> getAllUsers() {
        // get all
        List<User> users = userRepository.findAll();
        
        return ResponseHandler.responseData(200, "success", users);
    }

    @Override
    public ResponseEntity<?> getLoginByUsernameService(UserRequest request) {
        String name = request.getUsername();
        User username = userRepository.findByUsername(name);
        String password = request.getPassword();

        if (username == null) {
            throw new NoSuchElementException("user tidak ditemukan");
        }
        if (!password.equals(username.getPassword())) {
            throw new NoSuchElementException("password salah");
        }
        return ResponseHandler.responseMessage(200, "Login Berhasil", true);
    }
}
