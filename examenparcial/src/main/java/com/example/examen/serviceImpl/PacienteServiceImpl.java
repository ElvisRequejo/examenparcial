package com.example.examen.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.examen.model.Paciente;
import com.example.examen.repository.PacienteRepository;
import com.example.examen.service.PacienteService;
@Service
public class PacienteServiceImpl implements PacienteService{

	@Autowired
	private PacienteRepository pacienteRepository;
	@Override
	public Paciente create(Paciente pacient) {
		// TODO Auto-generated method stub
		return pacienteRepository.save(pacient);
	}
	
	@Override
	public List<Paciente> readAll(){
		// TODO Auto-generated method stub
		return pacienteRepository.findAll();
	}
	
	@Override
	public Paciente read(Long id) {
		// TODO Auto-generated method stub
		return pacienteRepository.findById(id).get();
	}
	
	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		pacienteRepository.deleteById(id);
	}
	
	@Override
	public Paciente update(Paciente pac) {
		// TODO Auto-generated method stub
		return pacienteRepository.save(pac);
	}
	
}
