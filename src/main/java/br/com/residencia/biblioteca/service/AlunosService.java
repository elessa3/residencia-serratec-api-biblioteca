package br.com.residencia.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.AlunosDTO;
import br.com.residencia.biblioteca.entity.Alunos;
import br.com.residencia.biblioteca.repository.AlunosRepository;

@Service
public class AlunosService {
	
	@Autowired
	AlunosRepository alunosRepository;
	
	public List<Alunos> getAllAlunos() {
		return alunosRepository.findAll();		
	}
	
	public List<AlunosDTO> getAllAlunosDTO() {
		List<Alunos> listaAlunos = alunosRepository.findAll();
		List<AlunosDTO> listaAlunosDTO = new ArrayList<>();
		
		for(Alunos alunos : listaAlunos) {
			AlunosDTO alunosDTO = toDTO(alunos);					
			listaAlunosDTO.add(alunosDTO);
		}	
		return listaAlunosDTO;
	}
	
	public Alunos getAlunosById(Integer id) {		
		return alunosRepository.findById(id).orElse(null);	
	}
	
	public Alunos saveAluno(Alunos alunos) {
		return alunosRepository.save(alunos);		
	}
	
	public AlunosDTO saveAlunosDTO(AlunosDTO alunosDTO) {
		Alunos alunos = toEntidade(alunosDTO);			
		Alunos novoAlunos = alunosRepository.save(alunos);
		
		AlunosDTO AlunosAtualizadaDTO = toDTO(novoAlunos); 
		return AlunosAtualizadaDTO;				
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
	
public AlunosDTO updateAlunosDTO(AlunosDTO alunosDTO, Integer id) {
		
		Alunos AlunosExistenteNoBanco = getAlunosById(id);
		AlunosDTO AlunosAtualizadoDTO = new AlunosDTO();
		
		if(AlunosExistenteNoBanco != null) {
			
			AlunosExistenteNoBanco = toEntidade(alunosDTO);			
			Alunos alunosAtualizado = alunosRepository.save(AlunosExistenteNoBanco);			
			AlunosAtualizadoDTO = toDTO(alunosAtualizado);					
		}
		return AlunosAtualizadoDTO;				
	}
	
	private Alunos toEntidade(AlunosDTO alunosDTO) {
		Alunos alunos = new Alunos();
		
		alunos.setBairro(alunosDTO.getBairro());		
		alunos.setCidade(alunosDTO.getCidade());
		alunos.setComplemento(alunosDTO.getComplemento());
		alunos.setDataNascimento(alunosDTO.getDataNascimento());
		alunos.setLogradouro(alunosDTO.getLogradouro());
		alunos.setNumeroLogradouro(alunosDTO.getNumeroLogradouro());
		alunos.setNome(alunosDTO.getNome());
		
		return alunos;
	}
	
	private AlunosDTO toDTO(Alunos Alunos) {
		AlunosDTO alunosDTO = new AlunosDTO();
		
		alunosDTO.setBairro(alunosDTO.getBairro());		
		alunosDTO.setCidade(alunosDTO.getCidade());
		alunosDTO.setComplemento(alunosDTO.getComplemento());
		alunosDTO.setDataNascimento(alunosDTO.getDataNascimento());
		alunosDTO.setLogradouro(alunosDTO.getLogradouro());
		alunosDTO.setNumeroLogradouro(alunosDTO.getNumeroLogradouro());
		alunosDTO.setNome(alunosDTO.getNome());		
		
		return alunosDTO;
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
