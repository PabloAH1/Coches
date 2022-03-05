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

import com.formacion.app.entity.Modelo;
import com.formacion.app.service.ModeloService;

@RestController
@RequestMapping("/api")
public class ModeloRestController {
	@Autowired
	private ModeloService servicio;
	
	@GetMapping({"/modelos","/todos"})
	public List<Modelo> index(){
		return servicio.findAll();
	}
	@GetMapping("/modelos/{id}")
	public ResponseEntity<?> findModeloById(@PathVariable Long id){

		Modelo modelo=null;
		Map<String,Object> response= new HashMap<>();

		try {

			modelo=servicio.findById(id);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if(modelo==null) {

			response.put("mensaje", "El modelo ID: ".concat(id.toString().concat("no existe en la base de datos")));

			return 	new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Modelo>(modelo,HttpStatus.OK);
	}
	@PostMapping("/modelo")
	public ResponseEntity<?> saveClienteExcepciones(@RequestBody Modelo modelo){
		Modelo modeloNew=null;
		Map<String,Object> response= new HashMap<>();
		try {
			modelo=servicio.save(modelo);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar un insert a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El modelo ha sido creado con éxito");
		response.put("modelo", modeloNew);

		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}

	@PutMapping("/modelo/{id}")
	public ResponseEntity<?> updateMarca(@RequestBody Modelo modelo, @PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		Modelo modeloActual = servicio.findById(id);
		if (modeloActual == null) {
			response.put("mensaje",
					"Error: no se pudo editar, el modelo con ID: " + id.toString() + "no existe en la BBDD");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			modeloActual.setNombre(modelo.getNombre());
			modeloActual.setMarca(modelo.getMarca());
			
			servicio.save(modeloActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar un update a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La marca ha sido actualizada con éxito");
		response.put("modelo", modeloActual);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/modelo/{id}")
	public ResponseEntity<?> deleteCoche(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		Modelo modeloActual = servicio.findById(id);
		if (modeloActual == null) {
			response.put("mensaje",
					"Error: no se pudo editar, el modelo con ID: " + id.toString() + "no existe en la BBDD");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			servicio.delete(id);
		} catch (DataAccessException e) {			
			response.put("mensaje", "Error al realizar un delete a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("modelo", modeloActual);
		response.put("mensaje", "Se ha borrado con exito el modelo");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}

