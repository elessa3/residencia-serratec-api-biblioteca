package br.com.residencia.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.entity.Editora;
import br.com.residencia.biblioteca.repository.EditoraRepository;

@Service
public class EditoraService {

	@Autowired
	EditoraRepository editoraRepository;
	
	public List<Editora> getAlleditora() {
		return editoraRepository.findAll();
	}
	
	public Editora getEditoraById(Integer id) {
		return editoraRepository.findById(id).get();
	}
	
	public Editora saveEditora(Editora editora) {
		return editoraRepository.save(editora);
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
