package br.com.residencia.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.residencia.biblioteca.entity.Emprestimo;
import br.com.residencia.biblioteca.service.EmprestimoService;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {
	
	@Autowired
	EmprestimoService emprestimoService;
	
	public ResponseEntity<List<Emprestimo>> getAllEmprestimo() {
		return new ResponseEntity<> (emprestimoService.getAllEmprestimo(), HttpStatus.OK);
	}
	
	public ResponseEntity<Emprestimo> getEmprestimoById(@PathVariable Integer id) {
		return new ResponseEntity<> (emprestimoService.getEmprestimoById(id), HttpStatus.OK);
	}

	public ResponseEntity<Emprestimo> saveEmprestimo(@RequestBody Emprestimo emprestimo) {
		return new ResponseEntity<> (emprestimoService.saveEmprestimo(emprestimo), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Emprestimo> updateEmprestimo(@RequestBody Emprestimo emprestimo, @PathVariable Integer id) {
		return new ResponseEntity<> (emprestimoService.updateEmprestimo(emprestimo, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Emprestimo> deleteEmprestimo (@PathVariable Integer id) {
		return new ResponseEntity<> (emprestimoService.deletarEmprestimo(id), HttpStatus.OK);
	}
}
