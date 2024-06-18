package org.taohansen.sittermanager.repositories.audit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taohansen.sittermanager.entities.audit.UserAudit;

import java.util.Optional;

public interface UserAuditRepository extends JpaRepository<UserAudit, Long> {
    Optional<UserAudit> findById(Long userId);
}