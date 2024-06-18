package org.taohansen.sittermanager.util.factory;

import org.springframework.stereotype.Component;
import org.taohansen.sittermanager.entities.audit.AuditAction;
import org.taohansen.sittermanager.entities.audit.AuditInterface;

import java.time.LocalDateTime;

@Component
public class AuditFactory {
    public <T extends AuditInterface> T createAudit(T audit, Long entityId, AuditAction action, String executedBy, String description, String oldValue, String newValue) {
        audit.setEntityId(entityId);
        audit.setAction(action);
        audit.setExecutedBy(executedBy);
        audit.setDescription(description);
        audit.setTimestamp(LocalDateTime.now());
        audit.setOldValue(oldValue);
        audit.setNewValue(newValue);
        return audit;
    }
}
