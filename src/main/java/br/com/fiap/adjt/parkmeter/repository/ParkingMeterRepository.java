package br.com.fiap.adjt.parkmeter.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.adjt.parkmeter.model.ParkingMeter;

@Repository
public interface ParkingMeterRepository extends MongoRepository<ParkingMeter, String> {

}
