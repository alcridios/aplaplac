package com.duoc.aplaplac.springaplaplac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duoc.aplaplac.springaplaplac.model.Carrera;


public interface CarreraRepository extends JpaRepository<Carrera, Long> {
	
	 List<Carrera> findAllByOrderByNombreAsc();
	 
}
