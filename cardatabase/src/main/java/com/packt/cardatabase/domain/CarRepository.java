package com.packt.cardatabase.domain;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path="vehicles")
public interface CarRepository extends CrudRepository<Car, Long> {



    // Fetch cars by color
	
    List<Car> findByColor(@Param("color") String color);
    // Fetch cars by model year
    List<Car> findByModelYear(@Param("modelYear") int modelYear);

 // Fetch cars by brand and model
    List<Car> findByBrandAndModel(@Param("brand") String brand,@Param("model")String model);
    // Fetch cars by brand or color
    List<Car> findByBrandOrColor(@Param("brand") String brand,@Param("color")  String color);
    
 // Fetch cars by brand and sort by year
    List<Car> findByBrandOrderByModelYearAsc(@Param("brand") String brand);
    
 // Fetch cars by brand using SQL
    @Query("select c from Car c where c.brand = ?1")
    List<Car> findByBrand(@Param("brand") String brand);

 // Fetch cars by brand using SQL
    @Query("select c from Car c where c.brand like %?1")
    List<Car> findByBrandEndsWith(@Param("brand") String brand);



}
