package com.formacion.app.service;

import java.util.List;

import com.formacion.app.entity.Modelo;


public interface ModeloService {

	public List<Modelo> findAll();
	
	public Modelo findById(Long id);
	
	public Modelo save(Modelo modelo);
		
	public void delete(Long id);	
		
}
