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

import br.com.residencia.biblioteca.dto.ConsultaCnpjDTO;
import br.com.residencia.biblioteca.dto.EditoraDTO;
import br.com.residencia.biblioteca.entity.Editora;
import br.com.residencia.biblioteca.service.EditoraService;


@RestController
@RequestMapping("/editora")
public class EditoraController {

	@Autowired
	EditoraService editoraService;
	
	@GetMapping
	public ResponseEntity<List<Editora>> getAllEditora(){
		return new ResponseEntity<> (editoraService.getAllEditora(),
				HttpStatus.OK);
	}
	
	@GetMapping("/dto")
	public ResponseEntity<List<EditoraDTO>> getAllEditoraDTO(){
		return new ResponseEntity<> (editoraService.getAllEditoraDTO(), 
					HttpStatus.CREATED);
	}
	
	@GetMapping("/editora-livros")
	public ResponseEntity<List<EditoraDTO>> getAllEditorasLivrosDTO(){
		return new ResponseEntity<> (editoraService.getAllEditorasLivrosDTO(), 
					HttpStatus.CREATED);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Editora> getEditoraById(@PathVariable Integer id){
		Editora editora = editoraService.getEditoraById(id);
		if(null != editora)
			return new ResponseEntity<> (editora, 
				HttpStatus.OK);
		else
			return new ResponseEntity<>(editora, 
				HttpStatus.NOT_FOUND);		
	}
	
	//rever esta parte
	@GetMapping("/consulta-cnpj/{cnpj}")
	public ResponseEntity<ConsultaCnpjDTO> consultaCnpjApiExterna(@PathVariable String cnpj){
		ConsultaCnpjDTO consultaCnpjDTO = editoraService.consultaCnpjApiExterna(cnpj);
		if(null != consultaCnpjDTO)
			return new ResponseEntity<> (consultaCnpjDTO, 
				HttpStatus.OK);
		else
			return new ResponseEntity<>(consultaCnpjDTO, 
				HttpStatus.NOT_FOUND);	
		//return new ResponseEntity<>(editoraService.consultaCnpjApiExterna(cnpj), HttpStatus.OK);
	}
	
	
	@PostMapping
	public ResponseEntity<Editora> saveEditora(@RequestBody Editora editora) {
		return new ResponseEntity<>(editoraService.saveEditora(editora), 
				HttpStatus.CREATED);		
	}
	
	@PostMapping("/dto")
	public ResponseEntity<EditoraDTO> saveEditoraDTO(@RequestBody EditoraDTO editoraDTO) {
		return new ResponseEntity<>(editoraService.saveEditoraDTO(editoraDTO), 
				HttpStatus.CREATED);		
	}	
	
	@PostMapping("/cnpj/{cnpj}")
	public ResponseEntity<Editora> saveEditoraFromApi(@PathVariable String cnpj){
		return new ResponseEntity<>(editoraService.saveEditoraFromApi(cnpj),
				HttpStatus.CREATED);
	}
	
	//******************************
	/*
	@PostMapping(value = "/cadastro-editora-com-foto", 
			consumes = { MediaType.APPLICATION_JSON_VALUE,
						 MediaType.MULTIPART_FORM_DATA_VALUE}
	)
	public ResponseEntity<EditoraDTO> saveEditoraFoto(
			@RequestPart("editora") String editoraTxt,
			@RequestPart("filename") MultipartFile file
			){
		//ResponseEntity<FreeImageHostDTO> freeImgDTO = editoraService.saveEditoraComFoto(editora, file);
				//ResponseEntity<String> freeImgDTO = editoraService.saveEditoraComFoto(editora, file);
				ResponseEntity<String> freeImgDTO = editoraService.saveFotoImgBB(editora, file);
				
				return new ResponseEntity<>(freeImgDTO.getBody(), HttpStatus.OK);
				
				/*
				Editora novaEditora = editoraService.saveEditoraComFoto(editora, file); 
				if (null == novaEditora)
					return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
				else
					return new ResponseEntity<>(novaEditora, HttpStatus.CREATED);
				//	
		
	}*/
	
	@PutMapping("/{id}")
	public ResponseEntity<Editora> updateEditora(@RequestBody Editora editora, @PathVariable Integer id) {
		return new ResponseEntity<> (editoraService.updateEditora(editora, id), 
				HttpStatus.OK);
	}
	
	@PutMapping("/dto/{id}")
	public ResponseEntity<EditoraDTO> updateEditoraDTO(@RequestBody EditoraDTO editoraDTO, @PathVariable Integer id) {
		return new ResponseEntity<> (editoraService.updateEditoraDTO(editoraDTO, id), 
				HttpStatus.OK);
	}
		
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
