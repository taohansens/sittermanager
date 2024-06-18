package org.taohansen.sittermanager.entities.audit;

import java.time.LocalDateTime;

public interface AuditInterface {
    Long getId();

    void setId(Long id);

    Long getEntityId();

    void setEntityId(Long entityId);

    AuditAction getAction();

    void setAction(AuditAction action);

    String getExecutedBy();

    void setExecutedBy(String executedBy);

    LocalDateTime getTimestamp();

    void setTimestamp(LocalDateTime timestamp);

    String getOldValue();

    void setOldValue(String oldValue);

    String getNewValue();

    void setNewValue(String newValue);
    String description();

    void setDescription(String description);
}
