package br.com.fiap.adjt.parkmeter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "br.com.fiap.adjt.parkmeter.repository")
@SpringBootApplication
public class ParkingMeterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingMeterApplication.class, args);
	}

}
