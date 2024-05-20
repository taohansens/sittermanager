package org.taohansen.sittermanager.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.taohansen.sittermanager.dtos.BabySitterDTO;
import org.taohansen.sittermanager.entities.Babysitter;
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
        return list.map(BabySitterDTO::new);
    }

    @Transactional(readOnly = true)
    public BabySitterDTO findById(Long id) {
        Optional<Babysitter> obj = repository.findById(id);
        Babysitter entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity Babysitter" + id + "not found."));
        return new BabySitterDTO(entity);
    }


    @Transactional
    public BabySitterDTO insert(BabySitterDTO dto) {
        Babysitter entity = new Babysitter();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new BabySitterDTO(entity);
    }

    @Transactional
    public BabySitterDTO update(Long id, BabySitterDTO dto) {
        try {
            Babysitter entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new BabySitterDTO(entity);
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

    private void copyDtoToEntity(BabySitterDTO dto, Babysitter entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setDateOfBirth(dto.getDateOfBirth());
        entity.setAddress(dto.getAddress());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setEmail(dto.getEmail());
        entity.setBonus(dto.getBonus());
        entity.setWeeklyHours(dto.getWeeklyHours());
        entity.setMonthlySalary(dto.getMonthlySalary());
        entity.setYearsOfExperience(dto.getYearsOfExperience());
        entity.setMaxTravelDistance(dto.getMaxTravelDistance());
    }
}
