package org.taohansen.sittermanager.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.taohansen.sittermanager.dtos.BabySitterDTO;
import org.taohansen.sittermanager.entities.Babysitter;
import org.taohansen.sittermanager.entities.audit.AuditAction;
import org.taohansen.sittermanager.entities.audit.BabysitterAudit;
import org.taohansen.sittermanager.entities.interfaces.BabysitterMapper;
import org.taohansen.sittermanager.repositories.BabysitterRepository;
import org.taohansen.sittermanager.services.exceptions.DatabaseException;
import org.taohansen.sittermanager.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.taohansen.sittermanager.util.AuditUtil;

import java.util.Optional;

@Service
public class BabysitterService {

    @Autowired
    private BabysitterRepository repository;

    @Autowired
    private AuthService authService;

    @Autowired
    private AuditUtil auditUtil;

    @Autowired
    private ObjectMapper objectMapper;

    @Transactional(readOnly = true)
    public Page<BabySitterDTO> findAllPaged(Pageable pageable) {
        Page<Babysitter> list = repository.findAll(pageable);

        String me = authService.authenticated().getId().toString();
        auditUtil.auditChange(BabysitterAudit.class, 0L,
                AuditAction.GET, me, "FindAllPaged", null, null);

        return list.map(BabysitterMapper.INSTANCE::toDto);
    }

    @Transactional(readOnly = true)
    public BabySitterDTO findById(Long id) {
        Optional<Babysitter> obj = repository.findById(id);
        Babysitter entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity Babysitter" + id + "not found."));

        String me = authService.authenticated().getId().toString();
        auditUtil.auditChange(BabysitterAudit.class, id,
                AuditAction.GET, me, "FindById", null, null);

        return BabysitterMapper.INSTANCE.toDto(entity);
    }

    @Transactional
    public BabySitterDTO insert(BabySitterDTO dto) {
        Babysitter entity = BabysitterMapper.INSTANCE.toEntity(dto);
        entity = repository.save(entity);

        String me = authService.authenticated().getId().toString();

        try {
            auditUtil.auditChange(BabysitterAudit.class, entity.getId(),
                    AuditAction.POST, me, "Insert", null, objectMapper.writeValueAsString(entity));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return BabysitterMapper.INSTANCE.toDto(entity);
    }

    @Transactional
    public BabySitterDTO update(Long id, BabySitterDTO dto) {
        String me = authService.authenticated().getId().toString();
        Babysitter entity = null;
        try {
            entity = repository.getReferenceById(id);
            String oldEntity = objectMapper.writeValueAsString(entity);
            entity = BabysitterMapper.INSTANCE.toEntity(dto);
            entity = repository.save(entity);

            auditUtil.auditChange(BabysitterAudit.class, entity.getId(),
                    AuditAction.PUT, me, "Update entity", oldEntity, objectMapper.writeValueAsString(entity));

            return BabysitterMapper.INSTANCE.toDto(entity);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id " + id + " not found");
        } catch (JsonProcessingException e) {
            auditUtil.auditChange(BabysitterAudit.class, id,
                    AuditAction.PUT, me, "Insert Ok, Error Json Serialize", null, null);
            return BabysitterMapper.INSTANCE.toDto(entity);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Babysitter id (" + id + ") not found.");
        }
        try {
            repository.deleteById(id);

            String me = authService.authenticated().getId().toString();
            auditUtil.auditChange(BabysitterAudit.class, id,
                    AuditAction.DELETE, me, "Delete Entity", null, null);

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Database Integrity Violation");
        }
    }
}