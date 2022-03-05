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

import com.formacion.app.entity.Coche;
import com.formacion.app.service.CocheService;

@RestController
@RequestMapping("/api")
public class CocheRestController {

	@Autowired
	private CocheService servicio;
	
	@GetMapping({"/coches","/todos"})
	public List<Coche> index(){
		return servicio.findAll();
	}
	@GetMapping("/coches/{id}")
	public ResponseEntity<?> findCocheById(@PathVariable Long id){

		Coche coche=null;
		Map<String,Object> response= new HashMap<>();

		try {

			coche=servicio.findById(id);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if(coche==null) {

			response.put("mensaje", "El coche ID: ".concat(id.toString().concat("no existe en la base de datos")));

			return 	new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Coche>(coche,HttpStatus.OK);
	}
	@PostMapping("/coche")
	public ResponseEntity<?> saveClienteExcepciones(@RequestBody Coche coche){
		Coche cocheNew=null;
		Map<String,Object> response= new HashMap<>();
		try {
			coche=servicio.save(coche);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar un insert a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El coche ha sido creado con éxito");
		response.put("coche", cocheNew);

		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}

	@PutMapping("/coche/{id}")
	public ResponseEntity<?> updateCoche(@RequestBody Coche coche, @PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		Coche cocheActual = servicio.findById(id);
		if (cocheActual == null) {
			response.put("mensaje",
					"Error: no se pudo editar, el coche con ID: " + id.toString() + "no existe en la BBDD");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			cocheActual.setModelo(coche.getModelo());
			cocheActual.setMatricula(coche.getMatricula());
			cocheActual.setCilindrada(coche.getCilindrada());
			cocheActual.setColor(coche.getColor());
			cocheActual.setVelocidad(coche.getVelocidad());

			servicio.save(cocheActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar un update a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El coche ha sido actualizado con éxito");
		response.put("coche", cocheActual);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/coche/{id}")
	public ResponseEntity<?> deleteCoche(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		Coche cocheActual = servicio.findById(id);
		if (cocheActual == null) {
			response.put("mensaje",
					"Error: no se pudo editar, el cliente con ID: " + id.toString() + "no existe en la BBDD");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			servicio.delete(id);
		} catch (DataAccessException e) {			
			response.put("mensaje", "Error al realizar un delete a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("coche", cocheActual);
		response.put("mensaje", "Se ha borrado con exito el coche");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
}
