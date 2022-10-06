package br.com.residencia.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.entity.Emprestimo;
import br.com.residencia.biblioteca.repository.EmprestimoRepository;

@Service
public class EmprestimoService {
	
	@Autowired
	EmprestimoRepository emprestimoRepository;
	
	public List<Emprestimo> getAllEmprestimo() {
		return emprestimoRepository.findAll();
	}
	
	public Emprestimo getEmprestimoById(Integer id) {
		return emprestimoRepository.findById(id).get();
	}
	
	public Emprestimo saveEmprestimo(Emprestimo emprestimo) {
		return emprestimoRepository.save(emprestimo);
	}
	
	public Emprestimo updateEmprestimo(Emprestimo emprestimo, Integer id) {
		
		Emprestimo emprestimoExistenteNoBanco = getEmprestimoById(id);
		
		emprestimoExistenteNoBanco.setDataEmprestimo(emprestimo.getDataEmprestimo());
		emprestimoExistenteNoBanco.setDataEntrega(emprestimo.getDataEntrega());
		emprestimoExistenteNoBanco.setValorEmprestimo(emprestimo.getValorEmprestimo());
		
		return emprestimoRepository.save(emprestimoExistenteNoBanco);
	}
	
	public Emprestimo deletarEmprestimo(Integer id) {
		emprestimoRepository.deleteById(id);
		return deletarEmprestimo(id);
	}
	

}
