package com.maids.librarymgmtsys.controller;

import com.maids.librarymgmtsys.dto.PatronDTO;
import com.maids.librarymgmtsys.model.Patron;
import com.maids.librarymgmtsys.service.PatronService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/patrons")
public class PatronController {
    @Autowired
    private PatronService patronService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("")
    public ResponseEntity<List<PatronDTO>> getPatrons() {
        List<PatronDTO> patrons = patronService.getAll().stream()
                .map(patron -> modelMapper.map(patron, PatronDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(patrons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatronDTO> getPatronById(@PathVariable Integer id) {
        Patron patron = patronService.getById(id);
        if (patron == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(modelMapper.map(patron, PatronDTO.class));
    }

    @PostMapping("")
    public ResponseEntity<PatronDTO> savePatron(@RequestBody PatronDTO patron) {
        Patron savedPatron = patronService.save(modelMapper.map(patron, Patron.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(savedPatron, PatronDTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatronDTO> updatePatron(@PathVariable Integer id, @RequestBody PatronDTO patron) {
        Patron updatedPatron = patronService.update(id, modelMapper.map(patron, Patron.class));
        return ResponseEntity.ok(modelMapper.map(updatedPatron, PatronDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Integer id) {
        try {
            patronService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
