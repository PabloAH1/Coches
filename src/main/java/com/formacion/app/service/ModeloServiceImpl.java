package com.formacion.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacion.app.dao.ModeloDao;
import com.formacion.app.entity.Modelo;

@Service
public class ModeloServiceImpl implements ModeloService{

	@Autowired
	private ModeloDao modeloDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Modelo> findAll() {
		return (List<Modelo>)modeloDao.findAll();
	}

	@Override
	public Modelo findById(Long id) {
		return modeloDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Modelo save(Modelo modelo) {
		return modeloDao.save(modelo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		modeloDao.deleteById(id);
	}

}