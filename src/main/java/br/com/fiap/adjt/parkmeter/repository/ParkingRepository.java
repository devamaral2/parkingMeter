package br.com.fiap.adjt.parkmeter.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.adjt.parkmeter.model.Parking;

@Repository
public interface ParkingRepository extends MongoRepository<Parking, String> {

	Optional<Parking> findByVeichle_Plate(String plate);

}
