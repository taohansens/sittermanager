package org.taohansen.sittermanager.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.taohansen.sittermanager.dtos.UserDTO;
import org.taohansen.sittermanager.dtos.RoleDTO;
import org.taohansen.sittermanager.entities.Role;
import org.taohansen.sittermanager.entities.User;
import org.taohansen.sittermanager.repositories.RoleRepository;
import org.taohansen.sittermanager.repositories.UserRepository;
import org.taohansen.sittermanager.services.exceptions.DatabaseException;
import org.taohansen.sittermanager.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public Page<UserDTO> findAllPaged(Pageable pageable) {
        Page<User> list = repository.findAll(pageable);
        return list.map(UserDTO::new);
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        Optional<User> obj = repository.findById(id);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity User not found."));
        return new UserDTO(entity);
    }


    @Transactional
    public UserDTO insert(UserDTO dto) {
        User entity = new User();
        copyDtoToEntity(dto, entity);

        for (RoleDTO roleDTO : dto.getRoles()) {
            Role role = roleRepository.findByAuthority(roleDTO.getAuthority());
            entity.getRoles().add(role);
        }

        entity = repository.save(entity);
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO update(Long id, UserDTO dto) {
        try {
            User entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new UserDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("User ID " + id + " not found");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("User id (" + id + ") not found.");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Database Integrity Violation");
        }
    }

    private void copyDtoToEntity(UserDTO dto, User entity) {
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());

        entity.getRoles().clear();
        for(RoleDTO roleDTO : dto.getRoles()){
            Role role = roleRepository.getReferenceById(roleDTO.getId());
            entity.getRoles().add(role);
        }
    }

}
