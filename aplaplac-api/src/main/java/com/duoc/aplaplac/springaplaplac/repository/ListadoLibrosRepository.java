package com.duoc.aplaplac.springaplaplac.repository;

import com.duoc.aplaplac.springaplaplac.model.ListadoLibros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ListadoLibrosRepository extends JpaRepository<ListadoLibros, Long> {
	
	List<ListadoLibros> findAllByOrderByNombreAsc();

}
