package br.com.residencia.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.repository.AlunosRepository;

@Service
public class AlunoService {
	
	@Autowired
	AlunosRepository alunosRepository;
	
	private List<Alunos> getAllAlunos() {
		return alunosRepository.findAll();
	}
}
