package org.taohansen.sittermanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.taohansen.sittermanager.dtos.RoleDTO;
import org.taohansen.sittermanager.services.RoleService;

import java.net.URI;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {

    @Autowired
    private RoleService service;
    @GetMapping
    public ResponseEntity<Page<RoleDTO>> findAll(Pageable pageable){
        Page<RoleDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RoleDTO> findById(@PathVariable Long id) {
        RoleDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }
    @PostMapping
    public ResponseEntity<RoleDTO> insert(@RequestBody RoleDTO dto) {
        RoleDTO newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<RoleDTO> update(@PathVariable Long id, @RequestBody RoleDTO dto) {
        RoleDTO newDto = service.update(id, dto);
        return ResponseEntity.ok().body(newDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
