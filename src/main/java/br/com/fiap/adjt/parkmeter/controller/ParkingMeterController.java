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

import br.com.fiap.adjt.parkmeter.model.ParkingMeter;
import br.com.fiap.adjt.parkmeter.repository.ParkingMeterRepository;

@RestController
@RequestMapping("/parkingmeter")
public class ParkingMeterController {

    private final ParkingMeterRepository parkingMeterRepository;

    public ParkingMeterController(final ParkingMeterRepository parkingMeterRepository) {
        this.parkingMeterRepository = parkingMeterRepository;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody final ParkingMeter parkingMeter) {
        return ResponseEntity.ok(parkingMeterRepository.save(parkingMeter));
    }
    
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable final String id, @RequestBody final ParkingMeter parkingMeter) {
        var response = parkingMeterRepository.findById(id);
        if(response.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var responseUpdated = parkingMeterRepository.save(parkingMeter);
        return ResponseEntity.ok(responseUpdated);
    }

    @GetMapping
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(parkingMeterRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable final String id) {
        return ResponseEntity.ok(parkingMeterRepository.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable final String id) {
    	parkingMeterRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
