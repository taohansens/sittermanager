package org.taohansen.sittermanager.repositories.audit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taohansen.sittermanager.entities.audit.BabysitterAudit;

import java.util.Optional;

public interface BabysitterAuditRepository extends JpaRepository<BabysitterAudit, Long> {
    Optional<BabysitterAudit> findById(Long id);
}