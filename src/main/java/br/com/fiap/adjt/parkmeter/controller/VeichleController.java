package br.com.fiap.adjt.parkmeter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.adjt.parkmeter.model.Veichle;
import br.com.fiap.adjt.parkmeter.repository.VeichleRepository;

@RestController
@RequestMapping("/parkingmeter/veichle")
public class VeichleController {

    private final VeichleRepository veichleRepository;

    public VeichleController(final VeichleRepository veichleRepository) {
        this.veichleRepository = veichleRepository;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody final Veichle veichle) {
        return ResponseEntity.ok(veichleRepository.save(veichle));
    }
    
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable final String id, @RequestBody final Veichle veichle) {
        var response = veichleRepository.findById(id);
        if(response.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var responseUpdated = veichleRepository.save(veichle);
        return ResponseEntity.ok(responseUpdated);
    }

    @GetMapping
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(veichleRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable final String id) {
        return ResponseEntity.ok(veichleRepository.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable final String id) {
    	veichleRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
}
