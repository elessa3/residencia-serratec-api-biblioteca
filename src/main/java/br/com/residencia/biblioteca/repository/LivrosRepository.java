package br.com.residencia.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.residencia.biblioteca.entity.Editora;
import br.com.residencia.biblioteca.entity.Livros;

public interface LivrosRepository 
		extends	JpaRepository<Livros, Integer>
{
	public List<Livros> findByEditora(Editora editora);
	
	public Livros findByNomeLivro(String nome);
	
	public Livros findByNomeAutor(String nome);
}
