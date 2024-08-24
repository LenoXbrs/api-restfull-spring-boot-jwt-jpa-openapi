package com.packt.cardatabase;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import com.packt.cardatabase.domain.AppUser;
import com.packt.cardatabase.domain.AppUserRepository;
import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;
import com.packt.cardatabase.domain.Owner;
import com.packt.cardatabase.domain.OwnerRepository;
//CommandLineRunner: interface que permite executarmos codigo antes da aplicação subir 
//@SpringBootApplication conjunto de tres anotações responsaveis por scanear classes e mais.
@SpringBootApplication
@EnableMethodSecurity
public class CardatabaseApplication implements CommandLineRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(
	        CardatabaseApplication.class);

	private final CarRepository carRepository;
	private final OwnerRepository ownerRepository;
	private final AppUserRepository urepository;

	
    public CardatabaseApplication(CarRepository repository,
    		OwnerRepository ownerRepository,
    		AppUserRepository urepository) {
        this.carRepository = repository;
        this.ownerRepository = ownerRepository;
		this.urepository = urepository;
    }

	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);
		logger.info("Aplication started");
	}
	


	@Override
	public void run(String... args) throws Exception {
		Owner owner1 = new Owner("John" , "Johnson");
	    Owner owner2 = new Owner("Mary" , "Robinson");
	    ownerRepository.saveAll(Arrays.asList(owner1, owner2));


		carRepository.save(new Car("Ford", "Mustang", "Red",
                "ADF-1121", 2023, 59000, owner1));
		carRepository.save(new Car("Nissan", "Leaf", "White",
                "SSJ-3002", 2020, 29000, owner2));
		carRepository.save(new Car("Toyota", "Prius",
                "Silver", "KKO-0212", 2022, 39000, owner2));
      
		// Username: user, password: user
        urepository.save(new AppUser("user",
            "$2a$10$NVM0n8ElaRgg7zWO1CxUdei7vWoPg91Lz2aYavh9.f9q0e4bRadue","USER"));
        // Username: admin, password: admin
        urepository.save(new AppUser("admin",
        		 "$2a$10$9JmTw/PrQk7nAsFl/Kl4w.vBKSRyeoH0H4CSZ7FdE3.rz7radB.Sm", "ADMIN"));
        
       


		
		// Fetch all cars and log to console
		
      for (Car car : carRepository.findAll()) {
          logger.info("brand: {}, model: {}",
              car.getBrand(), car.getModel());
      }



	}

}
