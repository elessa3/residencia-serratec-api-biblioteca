package br.com.residencia.biblioteca.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.residencia.biblioteca.dto.ConsultaCnpjDTO;
import br.com.residencia.biblioteca.dto.EditoraDTO;
import br.com.residencia.biblioteca.dto.LivrosDTO;
import br.com.residencia.biblioteca.entity.Editora;
import br.com.residencia.biblioteca.entity.Livros;
import br.com.residencia.biblioteca.repository.EditoraRepository;
import br.com.residencia.biblioteca.repository.LivrosRepository;


@Service
public class EditoraService {

	@Autowired
	EditoraRepository editoraRepository;
	
	@Autowired
	LivrosRepository livrosRepository;
	
	@Autowired
	LivrosService livrosService;
	
	
	public List<Editora> getAllEditora() {
		return editoraRepository.findAll();
	}	
	
	public List<EditoraDTO> getAllEditoraDTO() {
		List<Editora> listaEditora = editoraRepository.findAll();
		List<EditoraDTO> listaEditoraDTO = new ArrayList<>();
		

		//1. Percorrer a lista de entidades Editora (chamada listaEditora)
		//2. Na lista de entidade, pegar cada entidade existente nela
		for(Editora editora : listaEditora) {

			//3. Transformar cada entidade existente na lista em um DTO
			EditoraDTO editoraDTO = toDTO(editora);
			
			//OBS: para converter a entidade no DTO, usar o metodo toDTO			
			//4. Adicionar cada DTO (que foi transformado a partir da entidade) na lista de DTO
			listaEditoraDTO.add(editoraDTO);
		}		
		//5. Retornar/devolver a lista de DTO preenchida
		return listaEditoraDTO;					
	}
	
	public Editora getEditoraById(Integer id) {		
		return editoraRepository.findById(id).orElse(null);
	}
	
	public Editora saveEditora(Editora editora) {
		return editoraRepository.save(editora);
	}	
		
	public EditoraDTO saveEditoraDTO(EditoraDTO editoraDTO) {
		Editora editora = toEntidade(editoraDTO);			
		Editora novaEditora = editoraRepository.save(editora);
		
		EditoraDTO editoraAtualizadaDTO = toDTO(novaEditora); 
		return editoraAtualizadaDTO;			
	}
	
	//*******************REVER********************
	
	public Editora saveEditoraFromApi(String cnpj) {
		ConsultaCnpjDTO consultaCnpjDTO = consultaCnpjApiExterna(cnpj);
				
		Editora editora = new Editora();
		editora.setNome(consultaCnpjDTO.getNome());				
		
		return editoraRepository.save(editora);
	}
	
	//*********************************************
	
	public Editora updateEditora(Editora editora, Integer id) {
		
		Editora editoraExistenteNoBanco = getEditoraById(id);		
		editoraExistenteNoBanco.setNome(editora.getNome());			
		return editoraRepository.save(editoraExistenteNoBanco);
	}
	
	private Editora toEntidade(EditoraDTO editoraDTO) {
		Editora editora = new Editora();
		
		editora.setNome(editoraDTO.getNome());
		return editora;
	}
	
	public EditoraDTO toDTO(Editora editora) {
		EditoraDTO editoraDTO = new EditoraDTO();
		
		editoraDTO.setCodigoEditora(editora.getCodigoEditora());
		editoraDTO.setNome(editora.getNome());	
		return editoraDTO;
	}
	
	public EditoraDTO updateEditoraDTO(EditoraDTO editoraDTO, Integer id) {
		Editora editoraExistenteNoBanco = getEditoraById(id);
		EditoraDTO editoraAtualizadaDTO = new EditoraDTO();
		
		if(editoraExistenteNoBanco != null) {
			editoraDTO.setCodigoEditora(editoraExistenteNoBanco.getCodigoEditora());
			editoraExistenteNoBanco = toEntidade(editoraDTO);
			
			Editora editoraAtualizada = editoraRepository.save(editoraExistenteNoBanco);
			
			editoraAtualizadaDTO = toDTO(editoraAtualizada);			
		}
		return editoraAtualizadaDTO;
	}
	
	//*********************************REVER
	
	public ConsultaCnpjDTO consultaCnpjApiExterna(String cnpj) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "https://receitaws.com.br/v1/cnpj/{cnpj}";
		
		Map<String,String> params = new HashMap<String, String>();
		params.put("cnpj", cnpj);
		
		ConsultaCnpjDTO consultaCnpjDTO = restTemplate.getForObject(uri, ConsultaCnpjDTO.class, params);
		
		return consultaCnpjDTO;
	}
	
	//****************************************	
	
	public Editora deleteEditora(Integer id) {		
		editoraRepository.deleteById(id);		
		return getEditoraById(id);
	}
	
	//***************************
	public List<EditoraDTO> getAllEditorasLivrosDTO() {
		
		List<Editora> listaEditora = editoraRepository.findAll();
		List<EditoraDTO> listaEditoraDTO = new ArrayList<>();		
	
		
		for(Editora editora : listaEditora) {			
			EditoraDTO editoraDTO = toDTO(editora);
			List<Livros> listaLivros = new ArrayList<>();
			List<LivrosDTO> listaLivrosDTO = new ArrayList<>();
						
			listaLivros = livrosRepository.findByEditora(editora);
			
			for(Livros livros : listaLivros) {
				LivrosDTO livrosDTO = livrosService.toDTO(livros);
				listaLivrosDTO.add(livrosDTO);
			}			
			
			editoraDTO.setListaLivrosDTO(listaLivrosDTO);	
			
			listaEditoraDTO.add(editoraDTO);
		}			
		return listaEditoraDTO;				
	}
		
}
