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

import br.com.fiap.adjt.parkmeter.dto.request.ParkingDTO;
import br.com.fiap.adjt.parkmeter.model.Parking;
import br.com.fiap.adjt.parkmeter.repository.ParkingRepository;
import br.com.fiap.adjt.parkmeter.service.ParkingService;

@RestController
@RequestMapping("/parkingmeter")
public class ParkingController {

    private final ParkingRepository parkingRepository;
    private final ParkingService parkingService;

    public ParkingController(final ParkingRepository parkingRepository, ParkingService parkingService) {
        this.parkingRepository = parkingRepository;
        this.parkingService = parkingService;
    }

    @PostMapping("/parking")
    public ResponseEntity<?> create(@RequestBody final ParkingDTO parkingDTO) {
    	
    	Parking parkingSave = parkingService.save(parkingDTO);
    	
        return ResponseEntity.ok(parkingRepository.save(parkingSave));
    }
    
    
    @PutMapping("/unparking/{plate}")
    public ResponseEntity<?> unparking(@PathVariable final String plate) {
        var response = parkingRepository.findByVeichle_Plate(plate);
        if(response.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Parking parking = parkingService.unparking(response);
        return ResponseEntity.ok(parking);
    }
    
    @PutMapping("/parking/{id}")
    public ResponseEntity<?> update(@PathVariable final String id, @RequestBody final Parking parking) {
        var response = parkingRepository.findById(id);
        if(response.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var responseUpdated = parkingRepository.save(parking);
        return ResponseEntity.ok(responseUpdated);
    }

    @GetMapping("/parking")
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(parkingRepository.findAll());
    }

    @GetMapping("/parking/{id}")
    public ResponseEntity<?> findById(@PathVariable final String id) {
        return ResponseEntity.ok(parkingRepository.findById(id));
    }

    @DeleteMapping("/parking/{id}")
    public ResponseEntity<?> deleteById(@PathVariable final String id) {
    	parkingRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
