package br.com.residencia.biblioteca.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.EditoraDTO;
import br.com.residencia.biblioteca.entity.Editora;
import br.com.residencia.biblioteca.repository.EditoraRepository;


@Service
public class EditoraService {

	@Autowired
	EditoraRepository editoraRepository;
	
	@Autowired
	public ModelMapper modelMapper;
	
	public List<Editora> getAllEditora() {
		return editoraRepository.findAll();
	}
	
	public List<EditoraDTO> getAllEditoraDTO() {
		return editoraRepository.findAll().stream()
			.map(entity -> new EditoraDTO(entity.getCodigoEditora(),
										  entity.getNome()))
			.collect(Collectors.toList());		
	}	
		
	/*private Editora converteDTOParaEntidade (EditoraDTO editoraDTO) {
		return new Editora(editoraDTO); 		
	}	
	private EditoraDTO converteEntidadeParaDTO (Editora editora) {
		return new EditoraDTO(editora);
	}*/
	
	public Editora getEditoraById(Integer id) {
		//return editoraRepository.findById(id).get();
		return editoraRepository.findById(id).orElse(null);
	}
	
	public Editora saveEditora(Editora editora) {
		return editoraRepository.save(editora);
	}
	
	/*public EditoraDTO saveEditoraDTO(EditoraDTO editoraDTO) {
		Editora editora = new Editora(editoraDTO);	
		return new EditoraDTO(editoraRepository.save(editora);		
	}*/
	
	public EditoraDTO saveEditoraDTO(EditoraDTO editoraDTO) {
		Editora editora = new Editora(editoraDTO);
		editora.setNome(editoraDTO.getNome());
		
		Editora novaEditora = editoraRepository.save(editora);
		
		EditoraDTO novaEditoraDTO = new EditoraDTO();
		
		novaEditoraDTO.setCodigoEditora(novaEditora.getCodigoEditora());
		novaEditoraDTO.setNome(novaEditora.getNome());		
		return novaEditoraDTO;
	}
	
	public Editora updateEditora(Editora editora, Integer id) {
		
		Editora editoraExistenteNoBanco = getEditoraById(id);
		
		editoraExistenteNoBanco.setNome(editora.getNome());
		editoraExistenteNoBanco.setLivros(editora.getLivros());
		
		return editoraRepository.save(editoraExistenteNoBanco);
	}
	
	public Editora deleteEditora(Integer id) {		
		editoraRepository.deleteById(id);		
		return getEditoraById(id);
	}
	
	
}
