package br.com.residencia.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.residencia.biblioteca.entity.Alunos;
import br.com.residencia.biblioteca.entity.Emprestimo;

public interface EmprestimoRepository 
		extends	JpaRepository<Emprestimo, Integer>
{
	public List<Emprestimo>findByAluno(Alunos alunos);
}
