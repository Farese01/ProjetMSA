package msa.postgresql.controller;

import msa.postgresql.entity.UserEntity;
import msa.postgresql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/postgres/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public List<UserEntity> createMultipleUsers(@RequestBody List<UserEntity> userEntities) {
        return userService.saveUser(userEntities);
    }

    @PutMapping("/{id}/email")
    public ResponseEntity<UserEntity> updateUserById(@PathVariable Long id, @RequestBody String email) {
        return ResponseEntity.ok(userService.updateUserById(id, email));
    }

    @PutMapping("/name/{name}/email")
    public ResponseEntity<UserEntity> updateUserByName(@PathVariable String name, @RequestBody String email) {
        return ResponseEntity.ok(userService.updateUserByName(name, email));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
