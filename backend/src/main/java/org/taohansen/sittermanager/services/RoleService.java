package org.taohansen.sittermanager.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.taohansen.sittermanager.dtos.RoleDTO;
import org.taohansen.sittermanager.entities.Role;
import org.taohansen.sittermanager.repositories.RoleRepository;
import org.taohansen.sittermanager.services.exceptions.DatabaseException;
import org.taohansen.sittermanager.services.exceptions.ResourceNotFoundException;

import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository repository;

    @Transactional(readOnly = true)
    public Page<RoleDTO> findAllPaged(Pageable pageable) {
        Page<Role> list = repository.findAll(pageable);
        return list.map(RoleDTO::new);
    }

    @Transactional(readOnly = true)
    public RoleDTO findById(Long id) {
        Optional<Role> obj = repository.findById(id);
        Role entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity Role not found."));
        return new RoleDTO(entity);
    }

    @Transactional
    public RoleDTO insert(RoleDTO dto) {
        Role entity = new Role(dto.getAuthority());
        entity = repository.save(entity);
        return new RoleDTO(entity);
    }

    @Transactional
    public RoleDTO update(Long id, RoleDTO dto) {
        try {
            Role entity = repository.getReferenceById(id);
            entity.setAuthority(dto.getAuthority());
            entity = repository.save(entity);
            return new RoleDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Role ID " + id + " not found");
        }
    }

    @Transactional(propagation = Propagation.NEVER)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Role id (" + id + ") not found.");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Database Integrity Violation");
        }
    }
}
