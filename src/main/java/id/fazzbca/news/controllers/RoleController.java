package id.fazzbca.news.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.fazzbca.news.payloads.req.RoleRequest;
import id.fazzbca.news.services.role.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    // register role
    @PostMapping("/register")
    public ResponseEntity<?> createRole(@RequestBody RoleRequest request){
        ResponseEntity<?> responseEntity = roleService.addRoleService(request);
        return responseEntity;
    }

    // get all role
    @GetMapping
    public ResponseEntity<?> getAllRoles(){
        return roleService.getAllRolesService();
    }
}