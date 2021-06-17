package com.duoc.aplaplac.springaplaplac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duoc.aplaplac.springaplaplac.model.Docente;

import java.util.List;

public interface DocenteRepository extends JpaRepository<Docente, Long> {

    List<Docente> findAllByOrderByNombreAsc();

}