package br.com.fiap.adjt.parkmeter.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.adjt.parkmeter.model.Veichle;

@Repository
public interface VeichleRepository extends MongoRepository<Veichle, String> {

}
