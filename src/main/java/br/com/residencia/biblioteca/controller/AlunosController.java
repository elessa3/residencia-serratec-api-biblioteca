package br.com.residencia.biblioteca.controller;

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

import br.com.residencia.biblioteca.entity.Alunos;
import br.com.residencia.biblioteca.service.AlunosService;

@RestController
@RequestMapping("/alunos")
public class AlunosController {
	@Autowired
	AlunosService alunosService;
	
	@GetMapping
	public ResponseEntity<List<Alunos>> getAllAlunos(){
		return new ResponseEntity<> (alunosService.getAllAlunos(), HttpStatus.OK);
	}

	/*@GetMapping("/hello")
	public ResponseEntity<String> helloWorld() {
		return new ResponseEntity<> ("A api esta funcionando", HttpStatus.OK);
	}*/
	
	/*@GetMapping("/{id}")
	public ResponseEntity<Alunos> getAlunosById (@PathVariable Integer id) {
		return new ResponseEntity<> (alunosService.getAlunosById(id), 
				HttpStatus.OK);
	}*/ 
	//substitui pelo de baixo, mas este tbm serve
	
	@GetMapping("/{id}")
	public ResponseEntity<Alunos> getAlunosById (@PathVariable Integer id) {
		Alunos alunos = alunosService.getAlunosById(id);
		if(null != alunos)			
			return new ResponseEntity<> (alunos, HttpStatus.OK);
		else
			return new ResponseEntity<> (alunos, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<Alunos> saveAluno(@RequestBody Alunos alunos) {
		return new ResponseEntity<>(alunosService.saveAluno(alunos), HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Alunos> updateAlunos(@RequestBody Alunos alunos, @PathVariable Integer id) {
		return new ResponseEntity<> (alunosService.updateAlunos(alunos, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Alunos> deleteAlunos (@PathVariable Integer id) {
		return new ResponseEntity<> (alunosService.deleteAluno(id), HttpStatus.OK);
	}
	
	
}//http://localhost:8080/swagger-ui/index.html
