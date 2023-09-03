package id.fazzbca.news.services.role;

import org.springframework.http.ResponseEntity;

import id.fazzbca.news.payloads.req.RoleRequest;

public interface RoleService {
    // create role
    ResponseEntity<?> addRoleService(RoleRequest request);

    // get all role
    ResponseEntity<?> getAllRolesService();
}