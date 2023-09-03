package id.fazzbca.news.services.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import id.fazzbca.news.models.Role;
import id.fazzbca.news.payloads.req.RoleRequest;
import id.fazzbca.news.payloads.res.ResponseHandler;
import id.fazzbca.news.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleRepository roleRepository;
    
    @Override
    public ResponseEntity<?> addRoleService(RoleRequest request) {
        // cek inputan
        if (request.getName() == null || request.getName() == "") {
            throw new IllegalArgumentException("role dibutuhkan");
        }
        // convert request menjadi model entity
        Role role = new Role(request.getName());
        // simpan role
        roleRepository.save(role);

        return ResponseHandler.responseMessage(201, "role berhasil ditambahkan", true);
    }

    @Override
    public ResponseEntity<?> getAllRolesService() {
        List<Role> roles = roleRepository.findAll();

        return ResponseHandler.responseData(200, "success", roles);
    }
}