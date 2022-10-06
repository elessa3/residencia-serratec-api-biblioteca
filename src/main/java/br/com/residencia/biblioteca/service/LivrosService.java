package br.com.residencia.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.entity.Livros;
import br.com.residencia.biblioteca.repository.LivrosRepository;

@Service
public class LivrosService {
	
	@Autowired
	LivrosRepository livrosRepository;
	
	public List<Livros> getAllLivros() {
		return livrosRepository.findAll();
	}
	
	public Livros getLivrosById(Integer id) {
		return livrosRepository.findById(id).get();
	}
	
	public Livros saveLivros(Livros livros) {
		return livrosRepository.save(livros);
	}
	
	public Livros updateLivros(Livros livros, Integer id) {
		
		Livros livrosExistentesNoBanco = getLivrosById(id);
		
		livrosExistentesNoBanco.setCodigoIsbn(livros.getCodigoIsbn());
		livrosExistentesNoBanco.setDataLancamento(livros.getDataLancamento());
		livrosExistentesNoBanco.setNomeAutor(livros.getNomeAutor());
		livrosExistentesNoBanco.setNomeLivro(livros.getNomeLivro());
		
		return livrosRepository.save(livrosExistentesNoBanco);
	}
	
	public Livros deleteLivros(Integer id) {
		livrosRepository.deleteById(id);
		return getLivrosById(id);
	}
}
