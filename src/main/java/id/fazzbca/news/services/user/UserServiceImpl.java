package id.fazzbca.news.services.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import id.fazzbca.news.configs.JwtUtil;
import id.fazzbca.news.models.Role;
import id.fazzbca.news.models.User;
import id.fazzbca.news.payloads.req.UserRequest;
import id.fazzbca.news.payloads.res.ResponseHandler;
import id.fazzbca.news.repositories.RoleRepository;
import id.fazzbca.news.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

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

        // dapatin role nya
        Role role = roleRepository.findByName(request.getRole());
        
        // cek role
        if (role == null) {
            throw new IllegalArgumentException("Role tidak ditemukan");
        }

        // simpan user baru
        User newUser = new User(request.getUsername(), passwordEncoder.encode(request.getPassword()), role, request.getEmail());
        userRepository.save(newUser);
        return ResponseHandler.responseData(201, "User berhasil ditambahkan", newUser);
        //return ResponseHandler.responseMessage(201, "User berhasil didaftarkan", true);
    }

    @Override
    public ResponseEntity<?> addUserServiceNoHash(UserRequest request) {
        // cek inputan
        if (request.getUsername() == null || request.getUsername() == "") {
            throw new IllegalArgumentException("username dibutuhkan");
        }

        // cek apakah username sudah ada
        User existingUser = userRepository.findByUsername(request.getUsername());
        if (existingUser != null) {
            throw new IllegalArgumentException("Username sudah ada");
        }

        // dapatin role nya
        Role role = roleRepository.findByName(request.getRole());
        
        // cek role
        if (role == null) {
            throw new IllegalArgumentException("Role tidak ditemukan");
        }

        // simpan user baru
        User newUser = new User(request.getUsername(), request.getPassword(), role, request.getEmail());
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
        // String name = request.getUsername();
        // User username = userRepository.findByUsername(name);
        // String password = request.getPassword();

        // if (username == null) {
        //     throw new NoSuchElementException("user tidak ditemukan");
        // }
        // if (!password.equals(username.getPassword())) {
        //     throw new NoSuchElementException("password salah");
        // }
        // buat username dan password token
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        // autentikasi usernya
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // buatkansecurity ceontext holdernya
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // generate token jwt
        // token dengan 3 bagian, header, payload dan signature
        String token = jwtUtil.createToken(request.getUsername());

        Map<String, Object> data = new HashMap<>();
        data.put("username", request.getUsername());
        data.put("token", token);

        return ResponseHandler.responseData(200, "Success Login", data);

        //return ResponseHandler.responseMessage(200, "Login Berhasil", true);
    }

    @Override
    public ResponseEntity<?> getLoginByUsernameNoHashService(UserRequest request) {
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

    @Override
    public ResponseEntity<?> updatePasswordService(String id, UserRequest request) {
        // find data by id
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("id tidak ditemukan");
        });
        // update pass
        if (request.getUsername() != "") {
            user.setUsername(request.getUsername());
        }
        if (request.getPassword() != "") {
            user.setPassword(request.getPassword());
        }

        // save ke db
        userRepository.save(user);

        // return response
        return ResponseHandler.responseMessage(200, "Ganti password berhasil", true);
    }
}
