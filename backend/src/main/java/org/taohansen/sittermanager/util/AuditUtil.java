package org.taohansen.sittermanager.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.taohansen.sittermanager.entities.audit.AuditAction;
import org.taohansen.sittermanager.entities.audit.AuditInterface;
import org.taohansen.sittermanager.entities.audit.BabysitterAudit;
import org.taohansen.sittermanager.entities.audit.UserAudit;
import org.taohansen.sittermanager.repositories.audit.BabysitterAuditRepository;
import org.taohansen.sittermanager.repositories.audit.UserAuditRepository;
import org.taohansen.sittermanager.util.factory.AuditFactory;

@Component
public class AuditUtil {

    @Autowired
    private UserAuditRepository userAuditRepository;

    @Autowired
    private BabysitterAuditRepository babysitterAuditRepository;

    @Autowired
    private AuditFactory auditFactory;

    public void auditChange(AuditInterface audit) {
        if (audit instanceof UserAudit) {
            userAuditRepository.save((UserAudit) audit);
        } else if (audit instanceof BabysitterAudit) {
            babysitterAuditRepository.save((BabysitterAudit) audit);
        }
    }

    public <T extends AuditInterface> void auditChange(Class<T> auditClass, Long entityId, AuditAction action, String executedBy, String description, String oldValue, String newValue) {
        try {
            T audit = auditClass.getDeclaredConstructor().newInstance();
            audit = auditFactory.createAudit(audit, entityId, action, executedBy, description, oldValue, newValue);
            auditChange(audit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}