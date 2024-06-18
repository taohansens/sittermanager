package org.taohansen.sittermanager.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.taohansen.sittermanager.dtos.BabySitterDTO;
import org.taohansen.sittermanager.entities.Babysitter;
import org.taohansen.sittermanager.entities.interfaces.BabysitterMapper;
import org.taohansen.sittermanager.repositories.BabysitterRepository;
import org.taohansen.sittermanager.services.exceptions.DatabaseException;
import org.taohansen.sittermanager.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BabysitterService {

    @Autowired
    private BabysitterRepository repository;

    @Transactional(readOnly = true)
    public Page<BabySitterDTO> findAllPaged(Pageable pageable) {
        Page<Babysitter> list = repository.findAll(pageable);
        return list.map(BabysitterMapper.INSTANCE::toDto);
    }

    @Transactional(readOnly = true)
    public BabySitterDTO findById(Long id) {
        Optional<Babysitter> obj = repository.findById(id);
        Babysitter entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity Babysitter" + id + "not found."));
        return BabysitterMapper.INSTANCE.toDto(entity);
    }


    @Transactional
    public BabySitterDTO insert(BabySitterDTO dto) {
        Babysitter entity = BabysitterMapper.INSTANCE.toEntity(dto);
        entity = repository.save(entity);
        return BabysitterMapper.INSTANCE.toDto(entity);
    }

    @Transactional
    public BabySitterDTO update(Long id, BabySitterDTO dto) {
        try {
            Babysitter entity = BabysitterMapper.INSTANCE.toEntity(dto);
            entity = repository.save(entity);
            return BabysitterMapper.INSTANCE.toDto(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Babysitter ID " + id + " not found");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Babysitter id (" + id + ") not found.");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Database Integrity Violation");
        }
    }
}