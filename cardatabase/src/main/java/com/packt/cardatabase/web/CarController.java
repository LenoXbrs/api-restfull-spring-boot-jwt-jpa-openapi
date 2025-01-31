package com.packt.cardatabase.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;

@RestController
public class CarController {

	private final CarRepository repository;
	
	public CarController(CarRepository repository) {
        this.repository = repository;
    }
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin/cars")
    public Iterable<Car> getCars() {
		
		
		return repository.findAll();
		//Fetch and return cars
	}
}
