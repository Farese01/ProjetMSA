package msa.postgresql.controller;

import msa.postgresql.entity.UserPlanEntity;
import msa.postgresql.service.UserPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/postgres/userplans")
public class UserPlanController {

    @Autowired
    private UserPlanService userPlanService;

    @GetMapping
    public List<UserPlanEntity> getAllUserPlans() {
        return userPlanService.getAllUserPlans();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserPlanEntity> getUserPlanById(@PathVariable Long id) {
        return userPlanService.getUserPlanById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public List<UserPlanEntity> createMultipleUserPlans(@RequestBody List<UserPlanEntity> userPlanEntities) {
        return userPlanService.saveUserPlan(userPlanEntities);
    }

    @PutMapping("/{id}/event")
    public ResponseEntity<UserPlanEntity> updateUserPlanById(@PathVariable Long id, @RequestBody Long eventId) {
        return ResponseEntity.ok(userPlanService.updateUserPlanById(id, eventId));
    }

    @PutMapping("/user/{userId}/event")
    public ResponseEntity<List<UserPlanEntity>> updateUserPlanByUserId(@PathVariable Long userId, @RequestBody Long eventId) {
        return ResponseEntity.ok(userPlanService.updateUserPlanByUserId(userId, eventId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserPlan(@PathVariable Long id) {
        userPlanService.deleteUserPlan(id);
        return ResponseEntity.noContent().build();
    }
}
