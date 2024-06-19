package org.taohansen.sittermanager.entities.audit;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_babysitter")
public class BabysitterAudit implements AuditInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long entityId;
    @Enumerated(EnumType.STRING)
    private AuditAction action;
    private String executedBy;
    private LocalDateTime timestamp;
    @Lob
    private String oldValue;
    @Lob
    private String newValue;
    private String description;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getEntityId() {
        return entityId;
    }

    @Override
    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    @Override
    public AuditAction getAction() {
        return action;
    }

    @Override
    public void setAction(AuditAction action) {
        this.action = action;
    }

    @Override
    public String getExecutedBy() {
        return executedBy;
    }

    @Override
    public void setExecutedBy(String executedBy) {
        this.executedBy = executedBy;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String getOldValue() {
        return oldValue;
    }

    @Override
    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    @Override
    public String getNewValue() {
        return newValue;
    }

    @Override
    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
}
