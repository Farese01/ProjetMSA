package msa.postgresql.repository;

import msa.postgresql.entity.UserPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPlanRepository extends JpaRepository<UserPlanEntity,Long> {
        List<UserPlanEntity> findByUserId(Long planId);
        List<UserPlanEntity> findByEventId(Long eventId);
}
