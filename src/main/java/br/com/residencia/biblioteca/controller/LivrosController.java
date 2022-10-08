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

import br.com.residencia.biblioteca.entity.Livros;
import br.com.residencia.biblioteca.service.LivrosService;


@RestController
@RequestMapping("/livros")
public class LivrosController {
	
		@Autowired
		LivrosService livrosService;
		
		@GetMapping
		public ResponseEntity<List<Livros>> getAllLivros(){
			return new ResponseEntity<> (livrosService.getAllLivros(), HttpStatus.OK);
		}
		
		@GetMapping("/{id}")
		public ResponseEntity<Livros> getLivrosById(@PathVariable Integer id){
			return new ResponseEntity<> (livrosService.getLivrosById(id), 
					HttpStatus.OK);
		}
		
		@PostMapping
		public ResponseEntity<Livros> saveLivros(@RequestBody Livros livros) {
			return new ResponseEntity<>(livrosService.saveLivros(livros), HttpStatus.OK);
			
		}
		
		@PutMapping("/{id}")
		public ResponseEntity<Livros> updateLivros(@RequestBody Livros livros, @PathVariable Integer id) {
			return new ResponseEntity<> (livrosService.updateLivros(livros, id), HttpStatus.OK);
		}
		
		@DeleteMapping("/{id}")
		public ResponseEntity<Livros> deleteLivros (@PathVariable Integer id) {
			return new ResponseEntity<> (livrosService.deleteLivros(id), HttpStatus.OK);
		}
				
}
