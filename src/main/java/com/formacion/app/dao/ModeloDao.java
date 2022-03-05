package com.formacion.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacion.app.entity.Modelo;

@Repository
public interface ModeloDao extends CrudRepository<Modelo, Long>{

}
