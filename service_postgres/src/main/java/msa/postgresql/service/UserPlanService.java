package msa.postgresql.service;

import lombok.AllArgsConstructor;
import msa.postgresql.entity.UserPlanEntity;
import msa.postgresql.repository.UserPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserPlanService {

    @Autowired
    private UserPlanRepository userPlanRepository;

    public List<UserPlanEntity> getAllUserPlans() {
        return userPlanRepository.findAll();
    }

    public Optional<UserPlanEntity> getUserPlanById(Long id) {
        return userPlanRepository.findById(id);
    }

    public List<UserPlanEntity> saveUserPlan(List<UserPlanEntity> userPlanEntities) {
        return userPlanRepository.saveAll(userPlanEntities);
    }

    public UserPlanEntity updateUserPlanById(Long id, Long eventId) {
        return userPlanRepository.findById(id).map(userPlanEntity -> {
            userPlanEntity.setEventId(eventId);
            return userPlanRepository.save(userPlanEntity);
        }).orElseThrow(() -> new RuntimeException("UserPlan not found with id " + id));
    }

    public List<UserPlanEntity> updateUserPlanByUserId(Long userId, Long eventId) {
        List<UserPlanEntity> userPlanEntities = userPlanRepository.findByUserId(userId);
        if (!userPlanEntities.isEmpty()) {
            for (UserPlanEntity userPlanEntity : userPlanEntities) {
                userPlanEntity.setEventId(eventId);
            }
            return userPlanRepository.saveAll(userPlanEntities);
        } else {
            throw new RuntimeException("UserPlans not found with userId " + userId);
        }
    }

    public void deleteUserPlan(Long id) {
        userPlanRepository.deleteById(id);
    }
}

