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

import br.com.residencia.biblioteca.entity.Editora;
import br.com.residencia.biblioteca.service.EditoraService;


@RestController
@RequestMapping("/editora")
public class EditoraController {

	@Autowired
	EditoraService editoraService;
	
	@GetMapping
	public ResponseEntity<List<Editora>> getAllEditora(){
		return new ResponseEntity<> (editoraService.getAlleditora(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Editora> getEditoraById(@PathVariable Integer id){
		return new ResponseEntity<> (editoraService.getEditoraById(id), 
				HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity<Editora> saveEditora(@RequestBody Editora editora) {
		return new ResponseEntity<>(editoraService.saveEditora(editora), HttpStatus.OK);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Editora> updateEditora(@RequestBody Editora editora, @PathVariable Integer id) {
		return new ResponseEntity<> (editoraService.updateEditora(editora, id), HttpStatus.OK);
	}
	
	/*@DeleteMapping("/{id}")
	public ResponseEntity<Editora> deleteEditora (@PathVariable Integer id) {
		return new ResponseEntity<> (editoraService.deleteEditora(id), HttpStatus.OK);
	}*/
	
	/*public Editora getEditoraById(Integer id) {
		return editoraRepository.findById(id).orElse(null);
	}*/
	/*public Editora getEditoraById(Integer id) {
		return EditoraRepository.findById(id).orElse(null);
	}*/
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Editora> deleteEditora(@PathVariable Integer id) {
		Editora editora = editoraService.getEditoraById(id);
		if(null == editora)
			return new ResponseEntity<>(editora,
					HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(editoraService.deleteEditora(id),
					HttpStatus.OK);
	}
}
