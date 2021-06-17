package com.duoc.aplaplac.springaplaplac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duoc.aplaplac.springaplaplac.model.Alumno;

import java.util.List;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    List<Alumno> findAllByOrderByNombreAsc();

}

