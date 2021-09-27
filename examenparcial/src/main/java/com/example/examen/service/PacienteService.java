package com.example.examen.service;

import java.util.List;

import com.example.examen.model.Paciente;

public interface PacienteService {

	Paciente create(Paciente pacient);

	List<Paciente> readAll();

	Paciente read(Long id);

	void delete(Long id);

	Paciente update(Paciente pac);
}
