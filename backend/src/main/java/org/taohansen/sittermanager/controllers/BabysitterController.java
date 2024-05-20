
package org.taohansen.sittermanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.taohansen.sittermanager.dtos.BabySitterDTO;
import org.taohansen.sittermanager.services.BabysitterService;
import java.net.URI;

@RestController
@RequestMapping(value = "/babysitters")
public class BabysitterController {

    @Autowired
    private BabysitterService service;
    @GetMapping
    public ResponseEntity<Page<BabySitterDTO>> findAll(Pageable pageable){
        Page<BabySitterDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BabySitterDTO> findById(@PathVariable Long id) {
        BabySitterDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<BabySitterDTO> insert(@RequestBody BabySitterDTO dto) {
        BabySitterDTO newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BabySitterDTO> update(@PathVariable Long id, @RequestBody BabySitterDTO dto) {
        BabySitterDTO newDto = service.update(id, dto);
        return ResponseEntity.ok().body(newDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
