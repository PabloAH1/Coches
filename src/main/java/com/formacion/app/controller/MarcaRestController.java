package com.formacion.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacion.app.entity.Marca;
import com.formacion.app.service.MarcaService;

@RestController
@RequestMapping("/api")
public class MarcaRestController {
	@Autowired
	private MarcaService servicio;
	
	@GetMapping({"/marcas","/todos"})
	public List<Marca> index(){
		return servicio.findAll();
	}
	@GetMapping("/marcas/{id}")
	public ResponseEntity<?> findCocheById(@PathVariable Long id){

		Marca marca=null;
		Map<String,Object> response= new HashMap<>();

		try {

			marca=servicio.findById(id);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if(marca==null) {

			response.put("mensaje", "La marca ID: ".concat(id.toString().concat("no existe en la base de datos")));

			return 	new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Marca>(marca,HttpStatus.OK);
	}
	@PostMapping("/marca")
	public ResponseEntity<?> saveClienteExcepciones(@RequestBody Marca marca){
		Marca marcaNew=null;
		Map<String,Object> response= new HashMap<>();
		try {
			marca=servicio.save(marca);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar un insert a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La marca ha sido creada con éxito");
		response.put("coche", marcaNew);

		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}

	@PutMapping("/marca/{id}")
	public ResponseEntity<?> updateMarca(@RequestBody Marca marca, @PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		Marca marcaActual = servicio.findById(id);
		if (marcaActual == null) {
			response.put("mensaje",
					"Error: no se pudo editar, la marca con ID: " + id.toString() + "no existe en la BBDD");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			marcaActual.setNombre(marca.getNombre());
			servicio.save(marcaActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar un update a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La marca ha sido actualizada con éxito");
		response.put("marca", marcaActual);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/marca/{id}")
	public ResponseEntity<?> deleteCoche(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		Marca marcaActual = servicio.findById(id);
		if (marcaActual == null) {
			response.put("mensaje",
					"Error: no se pudo editar, la marca con ID: " + id.toString() + "no existe en la BBDD");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			servicio.delete(id);
		} catch (DataAccessException e) {			
			response.put("mensaje", "Error al realizar un delete a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("marca", marcaActual);
		response.put("mensaje", "Se ha borrado con exito la marca");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
