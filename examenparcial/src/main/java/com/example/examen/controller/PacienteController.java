package com.example.examen.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.examen.model.Paciente;
import com.example.examen.service.PacienteService;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

	@Autowired
	private PacienteService pacienteService;
	@PostMapping("/create")
	public ResponseEntity<Paciente> save(@RequestBody Paciente paciente){
	
		try {
			Paciente pac = pacienteService.create(new Paciente(paciente.getId(),paciente.getNombres(),
					paciente.getApellidos(),paciente.getDireccion(),paciente.getTelefono()));
			return new ResponseEntity<>(pac, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/listar")
	public ResponseEntity<List<Paciente>> getPacientes(){
		try {
			List<Paciente> list = new ArrayList<>();
			list = pacienteService.readAll();
			if (list.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/read/{id}")
	public ResponseEntity<Paciente> getPaciente(@PathVariable("id") long id){
		Paciente paciente = pacienteService.read(id);
			if(paciente.getId()>0) {
				return new ResponseEntity<>(paciente, HttpStatus.OK);
			}else {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} 
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id){
		try {
			pacienteService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<Paciente> update(@RequestBody Paciente paciente, @PathVariable("id") long id){
		try {
			Paciente pp = pacienteService.read(id);
			if(pp.getId()>0) {
				pp.setNombres(paciente.getNombres());
				pp.setApellidos(paciente.getApellidos());
				pp.setDireccion(paciente.getDireccion());
				pp.setTelefono(paciente.getTelefono());
				
				return new ResponseEntity<>(pacienteService.create(pp),HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}			

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
