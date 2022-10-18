package br.com.residencia.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.LivrosDTO;
import br.com.residencia.biblioteca.entity.Livros;
import br.com.residencia.biblioteca.repository.LivrosRepository;

@Service
public class LivrosService {
	
	@Autowired
	LivrosRepository livrosRepository;
	
	public List<Livros> getAllLivros() {
		return livrosRepository.findAll();
	}
	
	public List<LivrosDTO> getAllLivrosDTO() {
		List<Livros> listaLivros = livrosRepository.findAll();
		List<LivrosDTO> listaLivrosDTO = new ArrayList<>();
		
		for(Livros livros : listaLivros) {
			LivrosDTO livrosDTO = toDTO(livros);					
			listaLivrosDTO.add(livrosDTO);
		}	
		return listaLivrosDTO;
	}
		
	public Livros getLivrosById(Integer id) {
		return livrosRepository.findById(id).get();
	}
	
	public Livros saveLivros(Livros livros) {
		return livrosRepository.save(livros);
	}
	
	public LivrosDTO saveLivrosDTO(LivrosDTO livrosDTO) {
		Livros livros = toEntidade(livrosDTO);			
		Livros novoLivros = livrosRepository.save(livros);
		
		LivrosDTO livrosAtualizadaDTO = toDTO(novoLivros); 
		return livrosAtualizadaDTO;				
	}
	
	public Livros updateLivros(Livros livros, Integer id) {
		
		Livros livrosExistentesNoBanco = getLivrosById(id);
		
		livrosExistentesNoBanco.setCodigoIsbn(livros.getCodigoIsbn());
		livrosExistentesNoBanco.setDataLancamento(livros.getDataLancamento());
		livrosExistentesNoBanco.setNomeAutor(livros.getNomeAutor());
		livrosExistentesNoBanco.setNomeLivro(livros.getNomeLivro());
		
		return livrosRepository.save(livrosExistentesNoBanco);
	}
		
	public LivrosDTO updateLivrosDTO(LivrosDTO livrosDTO, Integer id) {
		
		Livros livrosExistenteNoBanco = getLivrosById(id);
		LivrosDTO livrosAtualizadoDTO = new LivrosDTO();
		
		if(livrosExistenteNoBanco != null) {
			
			livrosExistenteNoBanco = toEntidade(livrosDTO);			
			Livros livrosAtualizado = livrosRepository.save(livrosExistenteNoBanco);			
			livrosAtualizadoDTO = toDTO(livrosAtualizado);					
		}
		return livrosAtualizadoDTO;				
	}
	
	public Livros toEntidade(LivrosDTO livrosDTO) {
		Livros livros = new Livros();
		
		livros.setNomeLivro(livrosDTO.getNomeLivro());
		livros.setNomeAutor(livrosDTO.getNomeAutor());
		livros.setDataLancamento(livrosDTO.getDataLancamento());
		livros.setCodigoIsbn(livrosDTO.getCodigoIsbn());
		
		return livros;
	}
	
	public LivrosDTO toDTO(Livros livros) {
		LivrosDTO livrosDTO = new LivrosDTO();
		
		livrosDTO.setCodigoLivro(livros.getCodigoLivro());
		livrosDTO.setNomeLivro(livros.getNomeAutor());
		livrosDTO.setNomeAutor(livros.getNomeAutor());
		livrosDTO.setDataLancamento(livros.getDataLancamento());
		livrosDTO.setCodigoIsbn(livros.getCodigoIsbn());
		
		return livrosDTO;
	}
	
	public Livros deleteLivros(Integer id) {
		livrosRepository.deleteById(id);
		return getLivrosById(id);
	}
}
