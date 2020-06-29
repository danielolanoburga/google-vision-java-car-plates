package com.upc.ia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upc.ia.entity.CarPlate;

@Repository
public interface CarPlateRepository extends JpaRepository<CarPlate, Long>{
	List<CarPlate> findByDescription(String description);
}
