package br.com.residencia.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.entity.Alunos;
import br.com.residencia.biblioteca.repository.AlunosRepository;

@Service
public class AlunosService {
	
	@Autowired
	AlunosRepository alunosRepository;
	
	public List<Alunos> getAllAlunos() {
		return alunosRepository.findAll();
		//para listar
	}
	
	public Alunos getAlunosById(Integer id) {
		//return alunosRepository.findById(id) .get();
		return alunosRepository.findById(id).orElse(null);
		// para localizar
	}
	
	public Alunos saveAluno(Alunos alunos) {
		return alunosRepository.save(alunos);
		//para salvar
	}
	
	public Alunos updateAlunos(Alunos alunos, Integer id) {
		//Aluno alunoExistenteNoBanco = alunosRepository.findById(id).get(); - outro metodo
		Alunos alunoExistenteNoBanco = getAlunosById(id);
		
		alunoExistenteNoBanco.setBairro(alunos.getBairro());
		alunoExistenteNoBanco.setCidade(alunos.getCidade());
		alunoExistenteNoBanco.setComplemento(alunos.getComplemento());
		alunoExistenteNoBanco.setCpf(alunos.getCpf());
		alunoExistenteNoBanco.setDataNascimento(alunos.getDataNascimento());
		alunoExistenteNoBanco.setLogradouro(alunos.getLogradouro());
		alunoExistenteNoBanco.setNome(alunos.getNome());
		alunoExistenteNoBanco.setNumeroLogradouro(alunos.getNumeroLogradouro());
		
		
		return alunosRepository.save(alunoExistenteNoBanco);
		//para atualizar os dados
	}
	
	public Alunos deleteAluno(Integer id) {
		alunosRepository.deleteById(id);
		return getAlunosById(id);
		//para deletar os dados
	}
	
/*	public Boolean deleteAlunosBool(Integer id) {
		alunosRepository.deleteById(id);
		if(null != getAlunosById(id))
			return 0;
		else
			return 1;
			metodo possivel
	}*/
}
